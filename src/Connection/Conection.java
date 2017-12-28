package Connection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author YU7
 */
public class Conection {

    public ResultSet rs;
    public Statement stm;
    public Connection conn = null;

    private String _DRIVER, _URL, _USER, _PASS, _BANCO;

    public Conection(String connection) {

        if (!connection.equals("")) {

            try {
                Properties prop = new Properties();
                File arquivo = new File(connection + ".properties");

                try (FileInputStream file = new FileInputStream(arquivo)) {
                    prop.load(file);
                    _DRIVER = prop.getProperty("prop.server.driver");
                    _USER = prop.getProperty("prop.server.usuario");
                    _PASS = prop.getProperty("prop.server.senha");
                    _URL = prop.getProperty("prop.server.url");
                    _BANCO = prop.getProperty("prop.server.database");
                    file.close();
                }

            } catch (FileNotFoundException ex) {
                Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String Conecta() {

        try {
            Class.forName(this._DRIVER);
            conn = DriverManager.getConnection(this._URL, this._USER, this._PASS);
            return "true";
        } catch (SQLException | ClassNotFoundException ex) {
            return "ERRO Conecta >> " + ex;
        }
    }

    public String Desconecta() {

        try {
            conn.close();
            return "true";
        } catch (SQLException ex) {
            return "ERRO Desconecta >> " + ex;
        }
    }

    public void executaSQL(String sql) {

        try {
            stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            rs = stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<String> listTabelas() {

        String sql, nametbla;

        if (_DRIVER.contains("mysql")) {
            sql = "SELECT table_name FROM information_schema.tables "
                    + "WHERE table_schema = '" + _BANCO + "'";
            nametbla = "table_name";
        } else {
            sql = "SELECT tablename FROM pg_catalog.pg_tables WHERE schemaname "
                    + "NOT IN ('pg_catalog', 'information_schema', 'pg_toast') "
                    + "ORDER BY tablename";
            nametbla = "tablename";
        }

        executaSQL(sql);
        ArrayList<String> lista = new ArrayList<>();

        try {
            if (rs.first()) {
                do {
                    lista.add(rs.getString(nametbla));
                } while (rs.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public String exportarTabela(String tabela, String path) {

        try {
            String sql;
            if (_DRIVER.contains("mysql")) {
                sql = "SELECT * FROM " + tabela + "\n"
                        + "INTO OUTFILE '" + path + "'\n"
                        + "FIELDS TERMINATED BY ','\n"
                        + "ENCLOSED BY '\"'\n"
                        + "LINES TERMINATED BY '\\n'";
            }else{
                sql = "COPY (select * from " + tabela + ") "
                    + "TO '" + path + "' DELIMITER ';' CSV HEADER";
            }

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            return "TABELA " + tabela + " EXPORTADA COM SUCESSO, EM: "+path;
            
        } catch (SQLException ex) {
            return "ERRO Função exportaTbls :" + ex;
        }

    }
    
    public void exportarSQLDump(){
        
        String sql = "mysqldump -u "+_USER+" -p "+_PASS+" "+_BANCO+" > "+_BANCO+".sql";
        
    }
    
}

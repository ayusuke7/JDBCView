package Connection;

import Utils.LogsEventos;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author YU7
 */
public class ConectionJDBC {

    public ResultSet rs;
    public Statement stm;
    public Connection conn = null;

    private final String _DRIVER, _URL, _USER, _PASS, _BANCO;

    public ConectionJDBC(Properties prop) {

        _DRIVER = prop.getProperty("prop.server.driver");
        _USER = prop.getProperty("prop.server.usuario");
        _PASS = prop.getProperty("prop.server.senha");
        _URL = prop.getProperty("prop.server.url");
        _BANCO = prop.getProperty("prop.server.database");

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

    public String executaSQL(String sql) {

        String resposta = "";
        rs = null;
        
        try {
            stm = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            rs = stm.executeQuery(sql);
        } catch (SQLException ex) {
           resposta = ex.getMessage();
        }
        
        return resposta;

    }

    public ArrayList<String> listTabelas() {

        String sql, table_name;

        if (_DRIVER.contains("mysql")) {
            sql = "SELECT table_name FROM information_schema.tables "
                + "WHERE table_schema = '" + _BANCO + "'";
            table_name = "table_name";
        } else if(_DRIVER.contains("postgres")) {
            sql = "SELECT tablename FROM pg_catalog.pg_tables WHERE schemaname "
                + "NOT IN ('pg_catalog', 'information_schema', 'pg_toast') "
                + "ORDER BY tablename";
            table_name = "tablename";
        }else{
            sql = "SELECT MSysObjects.Name FROM MSysObjects WHERE (MSysNameMap.Type = 1)";
            table_name = "Name";
        }

        executaSQL(sql);
        ArrayList<String> lista = new ArrayList<>();

        try {
            if (rs != null && rs.first()) {
                do {
                    lista.add(rs.getString(table_name));
                } while (rs.next());
            }
        } catch (SQLException ex) {
            LogsEventos.Gravar(ex.getMessage());
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
            } else {
                sql = "COPY (select * from " + tabela + ") "
                        + "TO '" + path + "' DELIMITER ';' CSV HEADER";
            }

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            return "TABELA " + tabela + " EXPORTADA COM SUCESSO, EM: " + path;

        } catch (SQLException ex) {
            return "ERRO Função exportaTbls :" + ex;
        }

    }
    

}

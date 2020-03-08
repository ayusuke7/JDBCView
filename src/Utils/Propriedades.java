package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Alexandre
 */
public class Propriedades {

    private final String path = System.getProperty("user.dir");
    
    public Propriedades() {        
    }

    public Properties loadProperties(String nome) {
        
        File arquivo = new File(path+"/"+nome);  
        Properties props = new Properties();

        try (FileInputStream input = new FileInputStream(arquivo)) {
            props.load(input);
        } catch (FileNotFoundException ex) {
            LogsEventos.Gravar(ex.getMessage());
        } catch (IOException ex) {
            LogsEventos.Gravar(ex.getMessage());
        }

        return props;

    }

    public boolean setProperties(String nome, Properties props) {

        File arquivo = new File(path+"/"+nome);  
        
        try (FileOutputStream output = new FileOutputStream(arquivo)) {
            props.store(output, "FILE PROPERTIES:");
            return true;

        } catch (FileNotFoundException ex) {
            LogsEventos.Gravar(ex.getMessage());
        } catch (IOException ex) {
            LogsEventos.Gravar(ex.getMessage());
        }

        return false;

    }

}

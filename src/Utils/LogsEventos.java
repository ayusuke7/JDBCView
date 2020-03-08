package Utils;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author AYU7-WN
 */
public class LogsEventos {

    public static void OpenLog() {

        String source = System.getProperty("user.dir");

        try {
            File file = new File(source + "/" + GetData() + ".txt");
            if (file.exists()) {
                Desktop.getDesktop().open(file);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,
                    "PROBLEMAS EM ABRIR ARQUIVO LOG\n" + ex.getMessage(),
                    "PROBLEMAS", JOptionPane.WARNING_MESSAGE);
        }
    }

    private static String GetData() {
        return new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    }

    private static String GetHora() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }

    public static void Gravar(String ref) {

        String source = System.getProperty("user.dir");
        File file = new File(source + "/" + GetData() + ".txt");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write(GetHora() + " - " + ref);
            bw.newLine();
            bw.flush();

            int op = JOptionPane.showConfirmDialog(null,
                    "OCORREU UM PROBLEMA!!\n"+
                    "VIZUALIZAR ARQUIVO DE LOG?",
                    "PROBLEMA",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            if (op == JOptionPane.YES_OPTION) {
                OpenLog();
            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,
                    "PROBLEMAS EM SALVAR LOG\n" + ex.getMessage(),
                    "PROBLEMAS", JOptionPane.WARNING_MESSAGE);
        }

    }
}

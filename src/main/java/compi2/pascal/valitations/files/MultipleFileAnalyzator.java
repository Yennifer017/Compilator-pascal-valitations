
package compi2.pascal.valitations.files;

import compi2.pascal.valitations.analyzator.Analyzator;
import compi2.pascal.valitations.exceptions.DirectoryException;
import compi2.pascal.valitations.files.model.OpenFile;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author blue-dragon
 */
public class MultipleFileAnalyzator extends Thread{
    private List<OpenFile> openFiles;
    private UtilForDirectories dirU;
    private UtilForFiles filesU;
    private Analyzator analyzator;
    private int index;
    
    private final static String SEPARATOR = "**********************************************";
    
    public MultipleFileAnalyzator(List<OpenFile> openFiles){
        this.openFiles = openFiles;
        dirU = new UtilForDirectories();
        filesU = new UtilForFiles();
        analyzator = new Analyzator();
        index = 0;
    }
    
    @Override
    public void run() {
        if(openFiles != null && !openFiles.isEmpty()){
            String mss = "Se termino el analisis";
            try {
                String rootFolder = dirU.createDirectory(".", "analysis");       
                for (OpenFile openFile : openFiles) {
                    try {
                        String directoryPath = dirU.createDirectory(
                                rootFolder, index + openFile.getFile().getName());
                        
                        StringBuilder builder = new StringBuilder(SEPARATOR);
                        builder.append("\n");
                        builder.append("Analisis del archivo: ");
                        builder.append(openFile.getFile().getAbsolutePath());
                        builder.append("\n");
                        builder.append(SEPARATOR);
                        builder.append("\n\n");
                        builder.append(analyzator.comprobate(
                                directoryPath + UtilForDirectories.getCarpetSeparatorStatic(), 
                                openFile.getOpenContent()
                        ));
                        File file = new File(rootFolder);
                        filesU.saveAs(
                                builder.toString(), 
                                ".txt", directoryPath, "analysis"
                        );
                    } catch (IOException | DirectoryException ex) {
                        System.out.println(ex);
                    } 
                    index++;
                }
            } catch (Exception e) {
                mss = e.toString();
            }
            JOptionPane.showMessageDialog(null, mss);
        } else {
            JOptionPane.showConfirmDialog(null, "No hay archivo abiertos");
        }
    }
    
}
    
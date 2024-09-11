
package compi2.pascal.valitations.files;

import compi2.pascal.valitations.analyzator.Analyzator;
import compi2.pascal.valitations.exceptions.DirectoryException;
import compi2.pascal.valitations.files.model.OpenFile;
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
            String rootFolder = "./analysis";
            
            for (OpenFile openFile : openFiles) {
                try {
                    String directoryPath = dirU.createDirectory(
                            rootFolder, openFile.getFile().getName());
                    StringBuilder builder = new StringBuilder(SEPARATOR);
                    builder.append("Analisis del archivo: ");
                    builder.append(openFile.getFile().getAbsolutePath());
                    builder.append("\n");
                    builder.append(SEPARATOR);
                    builder.append(analyzator.comprobate(
                            rootFolder + UtilForDirectories.getCarpetSeparatorStatic() + directoryPath, 
                            openFile.getOpenContent()
                    ));
                    filesU.saveAs(
                            builder.toString(), 
                            ".txt", rootFolder, directoryPath
                    );
                } catch (IOException | DirectoryException ex) {
                    Logger.getLogger(MultipleFileAnalyzator.class.getName()).log(Level.SEVERE, null, ex);
                } 
                index++;
            }
            JOptionPane.showConfirmDialog(null, "Se termino el analisis");
        } else {
            JOptionPane.showConfirmDialog(null, "No hay archivo abiertos");
        }
    }
    
}
    
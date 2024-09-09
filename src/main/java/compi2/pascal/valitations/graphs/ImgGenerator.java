
package compi2.pascal.valitations.graphs;

import compi2.pascal.valitations.files.UtilForFiles;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.parse.Parser;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author blue-dragon
 */
public class ImgGenerator {
     public static final String DOT_EXTENSION = ".dot";

    private final UtilForFiles filesUtil;

    public ImgGenerator() {
        filesUtil = new UtilForFiles();
    }

    
    public void generateImg(String finalPath, String nameFile, String code) throws IOException {
        String finalPathDotFile = finalPath + nameFile + DOT_EXTENSION;
        File file = new File(finalPathDotFile);
        filesUtil.saveFile(code, file);
        MutableGraph mutableGrap = new Parser().read(file);
        Graphviz.fromGraph(mutableGrap)
                .render(Format.PNG).toFile(new File(finalPath + nameFile + ".png"));
        //filesUtil.deleteFile(finalPathDotFile);
    }

    public static String getExecutComand() {
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().contains("windows")) {
            return "dot.exe";
        } else {
            return "dot";
        }
    }
}

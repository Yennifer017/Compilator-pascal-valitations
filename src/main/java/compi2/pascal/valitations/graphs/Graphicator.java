
package compi2.pascal.valitations.graphs;

/**
 *
 * @author blue-dragon
 */
public class Graphicator {
    
    public static final String OPEN_TD = "<td>";
    public static final String CLOSE_TD = "</td>";
    public static final String TABLE_ID = "t";
    
    protected String getInitCode(){
        return 
            """
            digraph G {
                rankdir=LR;
                node [shape=plaintext];
            """;
    }
    
    protected String getFinalCode(String title){
        StringBuilder builder =  new StringBuilder(
            """
            labelloc="t";
                label="
            """);
        builder.append(title)
                .append("""
                        ";
                        }
                        """);
        return builder.toString();
    }
    
    protected StringBuilder getFinalCodeForST(){
        return new StringBuilder("""
                                 </table>
                                     >];
                                 """);
    }
    
    //FOR ALL USE
    public StringBuilder getDataGraphCode(String data){
        StringBuilder builder = new StringBuilder(Graphicator.OPEN_TD)
                .append(data)
                .append(Graphicator.CLOSE_TD);
        return builder;
    }
    
    public StringBuilder getNoDataGraphCode(){
        StringBuilder builder = new StringBuilder(Graphicator.OPEN_TD)
                .append(Graphicator.CLOSE_TD);
        return builder;
    }
}

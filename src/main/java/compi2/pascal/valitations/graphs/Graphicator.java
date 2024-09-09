
package compi2.pascal.valitations.graphs;

import compi2.pascal.valitations.analysis.symbolt.RowST;
import compi2.pascal.valitations.analysis.symbolt.SymbolTable;

/**
 *
 * @author blue-dragon
 */
public class Graphicator {
    
    public static final String OPEN_TD = "<td>";
    public static final String CLOSE_TD = "</td>";
    
    private String getInitCode(){
        return 
            """
            digraph G {
                rankdir=LR;
                node [shape=plaintext];
            """;
    }
    
    private String getFinalCode(){
        return 
            """
            labelloc="t";
                label="Tabla de simbolos";
            }
            """;
    }
    
    private StringBuilder getInitCodeForST(String name){
        StringBuilder builder = new StringBuilder(name).append(
            """
                [label=<
                    <table border="1" cellborder="1" cellspacing="0" cellpadding="10">
                        <tr>
                            <td><b>Nombre</b></td>
                            <td><b>Categoria</b></td>
                            <td><b>Tipo</b></td>
                            <td><b>Direccion de <BR/> Memoria relativa</b></td>
                            <td><b>Numero de <BR/> total de elementos</b></td>
                            <td><b>Numero de <BR/> parametros</b></td>
                            <td><b>Limites</b></td>
                        </tr>
            """);
        return builder;
    }
    
    private StringBuilder getFinalCodeForST(){
        return new StringBuilder("""
                                 </table>
                                     >];
                                 """);
    }
    
    private String getFinalCodeForST(){
        return """
               </table>
                   >];
               """;
    }
    
    private CodeRecolector getInternalCodeForST(SymbolTable symbolTable, Index index, 
            int fatherTabId){
        StringBuilder builder = new StringBuilder();
        StringBuilder externalCode = new StringBuilder();
        if(!symbolTable.isEmpty()){
            for (RowST row : symbolTable.values()) {
                builder.append("<tr>");
                builder.append(row.getGraphRowCode("t" + index.getNumber()));
                builder.append("</tr>");
                if(row.isLinked()){
                    externalCode.append(row.getGraphInternalTab());
                    index.increment();
                }
            }
        }
        return new CodeRecolector(builder, externalCode);
    }
    
    //generate final code
    public String getCodeST(SymbolTable symbolTable){
        Index index = new Index();
        StringBuilder code = new StringBuilder(getInitCode());
        code.append(getCodeST(symbolTable, "t" + index.getNumber(), index));
        code.append(getFinalCode());
        return code.toString();
    }
    
    public String getCodeST(SymbolTable symbolTable, String tableName, Index index){
        StringBuilder code = new StringBuilder(getInitCodeForST(tableName));
        code.append(getInternalCodeForST(symbolTable, index, 0));
        return code.toString();
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

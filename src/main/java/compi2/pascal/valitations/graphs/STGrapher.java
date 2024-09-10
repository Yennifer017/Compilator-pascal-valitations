
package compi2.pascal.valitations.graphs;

import compi2.pascal.valitations.analysis.symbolt.RowST;
import compi2.pascal.valitations.analysis.symbolt.SymbolTable;
import static compi2.pascal.valitations.graphs.Graphicator.TABLE_ID;

/**
 *
 * @author blue-dragon
 */
public class STGrapher extends Graphicator{
    
    private StringBuilder getHeaderCodeForST(int id){
        StringBuilder builder = new StringBuilder(TABLE_ID + id).append(
            """
                [label=<
                    <table border="1" cellborder="1" cellspacing="0" cellpadding="5">
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
    
    /**
     * Devuelve el codigo de las filas de una tabla de simbolos,asi como el codigo 
     * extra que surge de tablas de simbolos internas
     * @param symbolTable
     * @param index
     * @return 
     */
    private CodeRecolector getInternalCodeForST(SymbolTable symbolTable, Index index){
        StringBuilder builder = new StringBuilder();
        StringBuilder externalCode = new StringBuilder();
        
        int fatherId = index.getNumber();
        if(!symbolTable.isEmpty()){
            for (RowST row : symbolTable.values()) {
                if(row.isLinked()){
                    index.increment();
                    externalCode.append(row.getGraphInternalTab(fatherId, index));
                }
                builder.append("<tr>\n");
                builder.append("    ");
                builder.append(row.getGraphRowCode(TABLE_ID + index.getNumber()));
                builder.append("\n");
                builder.append("</tr>\n");
            }
            
        }
        return new CodeRecolector(builder, externalCode);
    }
    
    /**
     * Genera el codigo para graficar una tabla de simbolos
     * @param symbolTable
     * @return codigo de graphviz final
     */
    public String getCodeST(SymbolTable symbolTable){
        Index index = new Index();
        StringBuilder code = new StringBuilder(getInitCode());
        code.append(getCodeST(symbolTable, index));
        code.append(getFinalCode("Tabla de Simbolos"));
        return code.toString();
    }
    
    /**
     * Genera el codigo para graficar una tabla de simbolos, permite especificar 
     * varios parametros
     * @param symbolTable la tabla de simbolos a graficar
     * @param index el indice actual de esta tabla
     * @return el codigo parcial de graphviz para una sola tabla
     */
    public String getCodeST(SymbolTable symbolTable, Index index){
        int actualIndex = index.getNumber();
        StringBuilder code = new StringBuilder(getHeaderCodeForST(index.getNumber()));
        CodeRecolector codeRecolector = getInternalCodeForST(symbolTable, index);
        code.append(codeRecolector.getInternalCode().toString());
        code.append(getFinalCodeForST());
        code.append(codeRecolector.getExternalCode().toString());
        return code.toString();
    }
    
}

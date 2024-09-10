
package compi2.pascal.valitations.graphs;

import compi2.pascal.valitations.analysis.typet.RecordType;
import compi2.pascal.valitations.analysis.typet.Type;
import compi2.pascal.valitations.analysis.typet.TypeTable;

/**
 *
 * @author blue-dragon
 */
public class TTGrapher extends Graphicator{
    
    private StringBuilder getHeaderCodeForTT(int id){
        StringBuilder builder = new StringBuilder(TABLE_ID + id).append(
            """
                [label=<
                    <table border="1" cellborder="1" cellspacing="0" cellpadding="5">
                        <tr>
                            <td><b>Nombre</b></td>
                            <td><b>Dimension <BR/> en memoria</b></td>
                            <td><b>Tipo Base</b></td>
                            <td><b>Limites</b></td>
                        </tr>
            """);
        return builder;
    }

    /**
     * Devuelve el codigo de las filas de una tabla de tipos,asi como el codigo 
     * extra que surge de tablas de simbolos internas
     * @param typeTable
     * @param index
     * @return 
     */
    private CodeRecolector getInternalCodeForST(TypeTable typeTable, Index index){
        StringBuilder builder = new StringBuilder();
        StringBuilder externalCode = new StringBuilder();
        
        int fatherId = index.getNumber();
        if(!typeTable.isEmpty()){
            for (Type type : typeTable.values()) {
                if(type.isLinked() && (type instanceof RecordType)){
                    index.increment();
                    RecordType recType =  (RecordType) type;
                    externalCode.append(recType.getGraphInternalTab(fatherId, index));
                }
                builder.append("<tr>\n");
                builder.append("    ");
                builder.append(type.getGraphRowCode(TABLE_ID + index.getNumber()));
                builder.append("\n");
                builder.append("</tr>\n");
            }
            
        }
        return new CodeRecolector(builder, externalCode);
    }
    
    /**
     * Genera el codigo para graficar una tabla de tipos
     * @param typeTable
     * @return codigo de graphviz final
     */
    public String getCodeTT(TypeTable typeTable){
        Index index = new Index();
        StringBuilder code = new StringBuilder(getInitCode());
        code.append(getCodeTT(typeTable, index));
        code.append(getFinalCode("Tabla de Tipos"));
        return code.toString();
    }
    
    /**
     * Genera el codigo para graficar una tabla de simbolos, permite especificar 
     * varios parametros
     * @param typeTable la tabla de simbolos a graficar
     * @param index el indice actual de esta tabla
     * @return el codigo parcial de graphviz para una sola tabla
     */
    public String getCodeTT(TypeTable typeTable, Index index){
        int actualIndex = index.getNumber();
        StringBuilder code = new StringBuilder(getHeaderCodeForTT(index.getNumber()));
        CodeRecolector codeRecolector = getInternalCodeForST(typeTable, index);
        code.append(codeRecolector.getInternalCode().toString());
        code.append(getFinalCodeForST());
        code.append(codeRecolector.getExternalCode().toString());
        return code.toString();
    }
}

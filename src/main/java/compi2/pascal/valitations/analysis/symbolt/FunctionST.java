
package compi2.pascal.valitations.analysis.symbolt;

import compi2.pascal.valitations.graphs.Graphicator;
import compi2.pascal.valitations.util.Index;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class FunctionST extends RowST{
    private SymbolTable internalST;
    private List<String> typesParams; 

    public FunctionST(String name, SymbolTable internalST, List<String> typesParams) {
        super(name, Category.Procedure, null);
        this.internalST = internalST;
        this.typesParams = typesParams;
    }
    
    public FunctionST(String name, String type, SymbolTable internalST, List<String> typesParams){
        super(name, Category.Function, type);
        this.internalST = internalST;
        this.typesParams = typesParams;
    }

    @Override
    public StringBuilder getGraphRowCode(String codeRelated) {
        StringBuilder builder = new StringBuilder(
                stGraphicator.getDataGraphCode(super.name));
        builder.append(stGraphicator.getDataGraphCode(category.getName()));
        if(type == null){
            builder.append(stGraphicator.getNoDataGraphCode());
        } else {
            builder.append(stGraphicator.getDataGraphCode(type));
        }
        builder.append(stGraphicator.getNoDataGraphCode());
        builder.append(stGraphicator.getDataGraphCode(
            internalST != null ?  String.valueOf(internalST.size()) : "0"
        ));
        builder.append(stGraphicator.getDataGraphCode(
                String.valueOf(typesParams != null ? typesParams.size() : 0 )
        ));        
        builder.append("<td port=\"");
        builder.append(codeRelated);
        builder.append("\"></td>");
        return builder;
    }

    @Override
    public boolean isLinked() {
        return true;
    }
    
    @Override
    public StringBuilder getGraphInternalTab(int fatherId, Index currentIndex) {
        int tableId = currentIndex.getNumber();
        StringBuilder code = new StringBuilder(
                stGraphicator.getCodeST(internalST, currentIndex)
        );
        code.append(Graphicator.TABLE_ID)
                .append(fatherId)
                .append(":")
                .append(Graphicator.TABLE_ID)
                .append(tableId)
                .append(" -> ")
                .append(Graphicator.TABLE_ID)
                .append(tableId)
                .append(";\n");
        return code;
    }
    
    
}

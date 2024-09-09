
package compi2.pascal.valitations.analysis.symbolt;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class FunctionST extends RowST{
    private SymbolTable internalST;
    private int numParams;

    public FunctionST(String name, SymbolTable internalST) {
        super(name, Category.Procedure, null);
        this.internalST = internalST;
        
    }
    
    public FunctionST(String name, String type, SymbolTable internalST){
        super(name, Category.Function, type);
        this.internalST = internalST;
    }

    @Override
    public StringBuilder getGraphRowCode(String codeRelated) {
        StringBuilder builder = new StringBuilder(
                graphicator.getDataGraphCode(super.name));
        builder.append(graphicator.getDataGraphCode(category.getName()));
        if(type == null){
            builder.append(graphicator.getNoDataGraphCode());
        } else {
            builder.append(graphicator.getDataGraphCode(type));
        }
        builder.append(graphicator.getNoDataGraphCode());
        builder.append(graphicator.getDataGraphCode(
            internalST != null ?  String.valueOf(internalST.size()) : "0"
        ));
        builder.append(graphicator.getDataGraphCode(String.valueOf(numParams)));
        
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
    public StringBuilder getGraphInternalTab() {
        return graphicator.
    }
    
    
}

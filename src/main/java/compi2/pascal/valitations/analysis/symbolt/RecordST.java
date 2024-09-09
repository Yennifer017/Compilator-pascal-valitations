
package compi2.pascal.valitations.analysis.symbolt;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class RecordST extends RowST{
    private int totalElements;
    private SymbolTable internalST;

    public RecordST(String name, SymbolTable internalST) {
        super(name, Category.Record, null);
        this.internalST = internalST;
    }

    @Override
    public StringBuilder getGraphRowCode(String codeRelated) {
        StringBuilder builder = super.getInitialGraphCodeData();
        builder.append(graphicator.getNoDataGraphCode());
        builder.append(graphicator.getDataGraphCode(String.valueOf(totalElements)));
        builder.append(graphicator.getNoDataGraphCode());
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
        throw new RuntimeException();
    }
}

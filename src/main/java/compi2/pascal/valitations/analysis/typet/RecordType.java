
package compi2.pascal.valitations.analysis.typet;

import compi2.pascal.valitations.graphs.Graphicator;
import compi2.pascal.valitations.graphs.Index;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class RecordType extends Type {
    private TypeTable internalTypeTable;
    
    public RecordType(String name, int dimention, TypeTable internalTypeTable) {
        super(name, dimention);
        this.internalTypeTable = internalTypeTable;
    }
    
    @Override
    public StringBuilder getGraphRowCode(String codeRelated){
        StringBuilder builder = getInitialGraphData();
        builder.append(ttGrapher.getDataGraphCode("Record"));
        builder.append(ttGrapher.getNoDataGraphCode());
        builder.append(ttGrapher.getNoDataGraphCode());
        builder.append("<td port=\"");
        builder.append(codeRelated);
        builder.append("\"></td>");
        return builder;
    }
    
    @Override
    public boolean isLinked(){
        return true;
    };
    
    public StringBuilder getGraphInternalTab(int fatherId, Index currentIndex) {
        int tableId = currentIndex.getNumber();
        StringBuilder code = new StringBuilder(
                ttGrapher.getCodeTT(internalTypeTable, currentIndex)
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

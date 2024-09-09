
package compi2.pascal.valitations.analysis.symbolt;

import compi2.pascal.valitations.analysis.typet.Limits;
import compi2.pascal.valitations.exceptions.SemanticException;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class ArrayST extends RowST {
    private int totalIndex;
    private int relativeDir;
    private Limits limits;

    public ArrayST(String name, String type, Limits limits, int relativeDir) 
            throws SemanticException {
        super(name, Category.Array, type);
        if(limits.containsNegative()){
            throw new SemanticException();
        }
        this.totalIndex = limits.calculateDimension();
        this.relativeDir = relativeDir;
    }

    @Override
    public StringBuilder getGraphRowCode(String codeRelated) {
        StringBuilder builder = super.getInitialGraphCodeData()
                .append(graphicator.getDataGraphCode(String.valueOf(relativeDir)));
        builder.append(graphicator.getDataGraphCode(String.valueOf(totalIndex)));
        builder.append(graphicator.getNoDataGraphCode());
        builder.append(graphicator.getDataGraphCode(limits.toString()));
        return builder;
    }

    @Override
    public boolean isLinked() {
        return false;
    }

}

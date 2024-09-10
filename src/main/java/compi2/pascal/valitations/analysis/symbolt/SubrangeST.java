
package compi2.pascal.valitations.analysis.symbolt;

import compi2.pascal.valitations.analysis.typet.Limits;
import compi2.pascal.valitations.analysis.typet.PrimitiveType;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class SubrangeST extends RowST {
    private int relativeDir;
    private Limits limits;

    public SubrangeST(String name, Limits limits, int relativeDir) {
        super(name, Category.Subrange, PrimitiveType.IntegerPT.getName());
        this.limits = limits;
        this.relativeDir = relativeDir;
    }

    @Override
    public StringBuilder getGraphRowCode(String codeRelated) {
        StringBuilder builder = super.getInitialGraphCodeData()
                .append(stGraphicator.getDataGraphCode(String.valueOf(relativeDir)));
        builder.append(stGraphicator.getNoDataGraphCode());
        builder.append(stGraphicator.getNoDataGraphCode());
        builder.append(stGraphicator.getDataGraphCode(limits.toString()));
        return builder;
    }

    @Override
    public boolean isLinked() {
        return false;
    }
    
}

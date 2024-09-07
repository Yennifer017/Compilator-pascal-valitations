
package compi2.pascal.valitations.analysis.symbolt;

import compi2.pascal.valitations.analysis.typet.Limits;
import compi2.pascal.valitations.analysis.typet.PrimitiveType;

/**
 *
 * @author blue-dragon
 */
public class SubrangeST extends RowST {
    private int relativeDir;
    private Limits limits;

    public SubrangeST(String name, Limits limits, int relativeDir) {
        super(name, Category.Subrange, PrimitiveType.IntegerPT.getName());
        this.limits = limits;
        this.relativeDir = relativeDir;
    }
}


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
}

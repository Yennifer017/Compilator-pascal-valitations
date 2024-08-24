
package compi2.pascal.valitations.semantic.expr;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public abstract class Expression {
    
    private Expression rightExp;
    private Expression leftExp;
    
    public abstract boolean isLeaf();
    public abstract boolean isComplex();
    
}

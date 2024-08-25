
package compi2.pascal.valitations.semantic.expr;

import compi2.pascal.valitations.analysis.typet.PrimitiveType;
import compi2.pascal.valitations.util.Position;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public abstract class Expression {
    
    protected Position pos;
    protected PrimitiveType type;
    
}

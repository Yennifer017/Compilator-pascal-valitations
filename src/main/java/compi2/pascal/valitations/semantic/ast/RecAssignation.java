
package compi2.pascal.valitations.semantic.ast;

import compi2.pascal.valitations.semantic.expr.Expression;
import compi2.pascal.valitations.semantic.obj.Label;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class RecAssignation extends Statement{
    private List<Label> variable;
    private Expression expToAsign;

    public RecAssignation(List<Label> variable, Expression expToAsign) {
        this.variable = variable;
        this.expToAsign = expToAsign;
    }
    
    
}

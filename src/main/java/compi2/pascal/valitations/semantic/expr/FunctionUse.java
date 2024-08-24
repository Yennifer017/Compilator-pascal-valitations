
package compi2.pascal.valitations.semantic.expr;

import compi2.pascal.valitations.semantic.obj.Label;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class FunctionUse extends Expression{
    private Label function;
    private List<Expression> params;

    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public boolean isComplex() {
        return true;
    }
}

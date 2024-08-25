
package compi2.pascal.valitations.semantic.expr;

import compi2.pascal.valitations.util.Position;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class FunctionUse extends Expression{
    private String functionName;
    private List<Expression> params;

    public FunctionUse(String functionName, List<Expression> params, Position pos) {
        this.functionName = functionName;
        this.params = params;
        super.pos = pos;
    }
    
    
}

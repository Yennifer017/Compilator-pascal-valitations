
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
public class FunctionUseStmt extends Statement{
    private boolean isSpecialFun;
    private Label nameFun;
    private List<Expression> args;

    public FunctionUseStmt(Label nameFun, List<Expression> arguments) {
        this.nameFun = nameFun;
        this.args = arguments;
        isSpecialFun = false;
    }
    
    public FunctionUseStmt(List<Expression> arguments){
        this.args = arguments;
        this.isSpecialFun = true;
    }
    
}


package compi2.pascal.valitations.semantic.obj;

import compi2.pascal.valitations.analysis.typet.Limits;
import compi2.pascal.valitations.semantic.expr.Expression;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class Range {
    Expression init;
    Expression end;

    public Range(Expression init, Expression end) {
        this.init = init;
        this.end = end;
    }
    
    public Limits validate(List<String> semanticErrors, DefAst ast){
        int lowLimit = Integer.MIN_VALUE;
        int maxLimit = Integer.MAX_VALUE;
        if (init.canRecoveryIntValue()) {
            lowLimit = init.recoveryIntegerData();
        } else {
            ast.validateNumericIntegerType(init, semanticErrors);
        }
        if (end.canRecoveryIntValue()) {
            maxLimit = end.recoveryIntegerData();
        } else {
            ast.validateNumericIntegerType(end, semanticErrors);
        }
        return new Limits(lowLimit, maxLimit);
    }
}

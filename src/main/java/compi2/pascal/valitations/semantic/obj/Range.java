package compi2.pascal.valitations.semantic.obj;

import compi2.pascal.valitations.analysis.symbolt.SymbolTable;
import compi2.pascal.valitations.analysis.typet.Limits;
import compi2.pascal.valitations.semantic.expr.Expression;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter
@Setter
public class Range {

    private Expression init;
    private Expression end;

    public Range(Expression init, Expression end) {
        this.init = init;
        this.end = end;
    }

    /**
     * Valida y retorna el rango en forma de limites
     *
     * @param semanticErrors
     * @param ast
     * @return la representacion del rango en limites
     */
    public Limits validate(SymbolTable symbolTable, List<String> semanticErrors, DefAst ast) {
        int lowLimit = 0;
        int maxLimit = Integer.MAX_VALUE;
        if (init.canRecoveryIntValue()) {
            lowLimit = init.recoveryIntegerData();
        } else {
            ast.validateNumericIntegerType(symbolTable, init, semanticErrors);
        }
        if (end.canRecoveryIntValue()) {
            maxLimit = end.recoveryIntegerData();
        } else {
            ast.validateNumericIntegerType(symbolTable, end, semanticErrors);
        }
        return new Limits(lowLimit, maxLimit);
    }
}

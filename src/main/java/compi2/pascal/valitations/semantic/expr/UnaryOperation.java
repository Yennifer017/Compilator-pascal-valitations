
package compi2.pascal.valitations.semantic.expr;

import compi2.pascal.valitations.analyzator.Analyzator;
import compi2.pascal.valitations.exceptions.ConvPrimitiveException;
import compi2.pascal.valitations.semantic.obj.Label;
import compi2.pascal.valitations.util.Position;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Setter @Getter
public class UnaryOperation extends Expression{
    private DefiniteOperation operation;
    private Expression expression;

    public UnaryOperation(DefiniteOperation operation, Expression expression, Position pos) {
        this.operation = operation;
        this.expression = expression;
        super.pos = pos;
    }

    @Override
    public boolean canRecoveryIntValue() {
        if((operation == DefiniteOperation.Addition || operation == DefiniteOperation.Substraction) 
                && expression.canRecoveryIntValue() && (expression instanceof SingleExp)){
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public int recoveryIntegerData(){
        if(expression instanceof SingleExp){
            if(operation == DefiniteOperation.Substraction){
                return expression.recoveryIntegerData() * -1;
            }
            return expression.recoveryIntegerData();
        } else {
            throw new RuntimeException("Can't recovery value");
        }
    }

    @Override
    public Label validateSimpleData(List<String> semanticErrors) {
        Label typeLabel = expression.validateSimpleData(semanticErrors);
        try {
            return new Label(
                    super.tConvert.simpleConvert(operation, typeLabel, semanticErrors),
                    pos);
        } catch (ConvPrimitiveException ex) {
            return new Label(Analyzator.ERROR_TYPE, pos);
        }
    }

}

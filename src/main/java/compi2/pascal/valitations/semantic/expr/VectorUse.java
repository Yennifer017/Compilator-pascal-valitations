
package compi2.pascal.valitations.semantic.expr;

import compi2.pascal.valitations.analyzator.Analyzator;
import compi2.pascal.valitations.semantic.obj.Label;
import compi2.pascal.valitations.util.Position;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class VectorUse extends Expression{
    private String idVector;
    private Expression expression;

    public VectorUse(String idVector, Expression expression, Position pos) {
        this.idVector = idVector;
        this.expression = expression;
        super.pos = pos;
    }

    @Override
    public Label validateSimpleData(List<String> semanticErrors) {
        semanticErrors.add(errorsRep.ilegalUseError(idVector, pos));
        expression.validateSimpleData(semanticErrors);
        return new Label(Analyzator.ERROR_TYPE, pos);
    }
    
}

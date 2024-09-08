
package compi2.pascal.valitations.semantic.expr;

import compi2.pascal.valitations.analysis.symbolt.SymbolTable;
import compi2.pascal.valitations.analysis.typet.TypeTable;
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
public class FunctionUse extends Expression{
    private String functionName;
    private List<Expression> params;

    public FunctionUse(String functionName, List<Expression> params, Position pos) {
        super();
        this.functionName = functionName;
        this.params = params;
        super.pos = pos;
    }

    @Override
    public Label validateSimpleData(List<String> semanticErrors) {
        semanticErrors.add(errorsRep.ilegalUseError(functionName, pos));
        for (int i = 0; i < params.size(); i++) {
            Expression param = params.get(i);
            param.validateSimpleData(semanticErrors);
        }
        return new Label(Analyzator.ERROR_TYPE, pos);
    }

    @Override
    public Label validateComplexData(SymbolTable symbolTable, TypeTable typeTable, 
            List<String> semanticErrors) {
        StringBuilder typeAddition = new StringBuilder("");
        if(params !=  null && !params.isEmpty()){
            for (Expression param : params) {
                Label paramLabel = param.validateComplexData(symbolTable, typeTable, semanticErrors);
                typeAddition.append(Analyzator.FUNCTION_SEPARATOR);
                typeAddition.append(paramLabel.getName());
            }
        }
        String nameFunInST = functionName + typeAddition.toString();
        if(refAnalyzator.existReference(symbolTable, semanticErrors, nameFunInST, pos)){
            return new Label(
                    refAnalyzator.getFromST(symbolTable, nameFunInST).getName(), 
                    pos
            );
        }
        return new Label(Analyzator.ERROR_TYPE, pos);
    }
    
}

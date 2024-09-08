
package compi2.pascal.valitations.semantic.expr;

import compi2.pascal.valitations.analysis.symbolt.SymbolTable;
import compi2.pascal.valitations.analysis.typet.TypeTable;
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
@Getter @Setter
public class Operation extends Expression{
    private DefiniteOperation operation;
    private Expression leftExp;
    private Expression rightExp;

    public Operation(DefiniteOperation operation, Expression leftExp, Expression rightExp, Position pos) {
        super();
        this.operation = operation;
        this.leftExp = leftExp;
        this.rightExp = rightExp;
        super.pos = pos;
    }

    @Override
    public Label validateSimpleData(List<String> semanticErrors) {
        Label leftType = leftExp.validateSimpleData(semanticErrors);
        Label rightType = rightExp.validateSimpleData(semanticErrors);
        try {
            return new Label(
                    super.tConvert.simpleConvert(operation, leftType, rightType, semanticErrors),
                    pos);
        } catch (ConvPrimitiveException ex) {
            return new Label(Analyzator.ERROR_TYPE, pos);
        }
    }

    @Override
    public Label validateComplexData(SymbolTable symbolTable, TypeTable typeTable, 
            List<String> semanticErrors) {
        Label leftType = leftExp.validateComplexData(symbolTable, typeTable, semanticErrors);
        Label rightType = rightExp.validateComplexData(symbolTable, typeTable, semanticErrors);
        return new Label(
                super.tConvert.complexConvert(operation, leftType, rightType, semanticErrors), 
                pos
        );
    }
    
}

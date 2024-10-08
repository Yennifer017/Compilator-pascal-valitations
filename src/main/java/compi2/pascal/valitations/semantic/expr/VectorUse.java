
package compi2.pascal.valitations.semantic.expr;

import compi2.pascal.valitations.analysis.actree.PassActTree;
import compi2.pascal.valitations.analysis.symbolt.Category;
import compi2.pascal.valitations.analysis.symbolt.RowST;
import compi2.pascal.valitations.analysis.symbolt.SymbolTable;
import compi2.pascal.valitations.analysis.typet.PrimitiveType;
import compi2.pascal.valitations.analysis.typet.TypeTable;
import compi2.pascal.valitations.analyzator.Analyzator;
import compi2.pascal.valitations.semantic.obj.Label;
import compi2.pascal.valitations.util.Index;
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
    public Label validateSimpleData(SymbolTable symbolTable, List<String> semanticErrors) {
        semanticErrors.add(errorsRep.ilegalUseError(idVector, pos));
        expression.validateSimpleData(symbolTable, semanticErrors);
        return new Label(Analyzator.ERROR_TYPE, pos);
    }

    @Override
    public Label validateComplexData(SymbolTable symbolTable, TypeTable typeTable, 
            List<String> semanticErrors) {
        //validar el index
        Label typeIndex = expression.validateComplexData(symbolTable, typeTable, semanticErrors);
        if(!super.tConvert.isNumericIntegerType(typeIndex.getName())){
            semanticErrors.add(errorsRep.incorrectTypeError(
                    typeIndex.getName(), 
                    PrimitiveType.IntegerPT.getName(), 
                    typeIndex.getPosition())
            );
        }
        if(refAnalyzator.existReference(symbolTable, semanticErrors, new Label(idVector, pos))){
            RowST row = refAnalyzator.getFromST(symbolTable, idVector);
            if(row.getCategory() == Category.Array){
                return new Label(row.getType(), pos);
            } else {
                semanticErrors.add(super.errorsRep.invalidCategoryAccessError(
                        row.getName(), 
                        row.getCategory().getName(), 
                        Category.Array.getName(), 
                        pos
                ));
            }
        }
        return new Label(Analyzator.ERROR_TYPE, pos);
    }

    @Override
    public PassActTree getActivationNodeTree(Index index) {
        return null;
    }
    
}

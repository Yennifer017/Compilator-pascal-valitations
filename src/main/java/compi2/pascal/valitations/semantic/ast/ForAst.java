
package compi2.pascal.valitations.semantic.ast;

import compi2.pascal.valitations.analysis.symbolt.RowST;
import compi2.pascal.valitations.analysis.symbolt.SymbolTable;
import compi2.pascal.valitations.analysis.typet.PrimitiveType;
import compi2.pascal.valitations.analysis.typet.TypeTable;
import compi2.pascal.valitations.semantic.expr.Expression;
import compi2.pascal.valitations.semantic.obj.Label;
import compi2.pascal.valitations.semantic.obj.Range;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class ForAst extends ControlStruct{
    private Label variable;
    private Range range;

    public ForAst(Label variable, Range range, List<Statement> internalStmts) {
        this.variable = variable;
        this.range = range;
        this.internalStmts = internalStmts;
    }

    @Override
    public void validate(SymbolTable symbolTable, TypeTable typeTable, 
            List<String> semanticErrors, SemanticRestrictions restrictions) {
        if(refAnalyzator.existReference(symbolTable, semanticErrors, 
                variable.getName(), variable.getPosition())
                ){
            RowST row = refAnalyzator.getFromST(symbolTable, variable.getName());
            if(!super.tConvert.isNumericIntegerType(row.getName())){
                semanticErrors.add(errorsRep.incorrectVarTypeError(
                        variable.getName(), 
                        PrimitiveType.IntegerPT.getName(),
                        variable.getPosition())
                );
            }
        }
        validateIntData(range.getInit(), symbolTable, typeTable, semanticErrors);
        validateIntData(range.getEnd(), symbolTable, typeTable, semanticErrors);
        super.validateInternalStmts(symbolTable, typeTable, semanticErrors, 
                new SemanticRestrictions(
                        true, 
                        true, 
                        restrictions.needReturnVal(),
                        restrictions.getReturnType()
                )
        );
        
    }
    
    private void validateIntData(Expression exp, SymbolTable symbolTable, 
            TypeTable typeTable, List<String> semanticErrors){
        Label typeLabel = exp.validateComplexData(symbolTable, typeTable, semanticErrors);
        if(!tConvert.isNumericIntegerType(typeLabel.getName())){
            semanticErrors.add(errorsRep.incorrectTypeError(
                    typeLabel.getName(),
                    PrimitiveType.IntegerPT.getName(), 
                    typeLabel.getPosition())
            );
        }
    }
    
}

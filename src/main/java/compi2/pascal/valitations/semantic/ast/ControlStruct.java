
package compi2.pascal.valitations.semantic.ast;

import compi2.pascal.valitations.analysis.symbolt.SymbolTable;
import compi2.pascal.valitations.analysis.typet.PrimitiveType;
import compi2.pascal.valitations.analysis.typet.TypeTable;
import compi2.pascal.valitations.analyzator.StmtsAnalizator;
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
public abstract class ControlStruct extends Statement{
    protected List<Statement> internalStmts;
    protected StmtsAnalizator stmtsAnalizator;
    
    public ControlStruct(){
        super();
        stmtsAnalizator = new StmtsAnalizator();
    }
    
    protected void validateInternalStmts(SymbolTable symbolTable, TypeTable typeTable, 
            List<String> semanticErrors, SemanticRestrictions restrictions){
        stmtsAnalizator.validateInternalStmts(symbolTable, typeTable, semanticErrors, 
                restrictions, internalStmts);
    }
    
    protected void validateCondition(Expression condition, SymbolTable symbolTable, 
            TypeTable typeTable, List<String> semanticErrors){
        Label typeCond = condition.validateComplexData(symbolTable, typeTable, semanticErrors);
        if(!tConvert.isNumericIntegerType(typeCond.getName())){
            semanticErrors.add(errorsRep.incorrectTypeError(
                    typeCond.getName(), 
                    PrimitiveType.BooleanPT.getName(), 
                    typeCond.getPosition())
            );
        }
    }
    
}


package compi2.pascal.valitations.semantic.ast;

import compi2.pascal.valitations.analysis.symbolt.SymbolTable;
import compi2.pascal.valitations.analysis.typet.TypeTable;
import compi2.pascal.valitations.analysis.typet.convert.TConvertidor;
import compi2.pascal.valitations.analyzator.StmtsAnalizator;
import compi2.pascal.valitations.semantic.ReturnCase;
import compi2.pascal.valitations.semantic.SemanticRestrictions;
import compi2.pascal.valitations.semantic.expr.Expression;
import compi2.pascal.valitations.semantic.obj.Label;
import compi2.pascal.valitations.util.ErrorsRep;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class SimpleCase {
    private List<Expression> labels;
    private List<Statement> statements;
    
    private StmtsAnalizator stmtsAnalizator;
    private TConvertidor tConvertidor;
    private ErrorsRep errorsRep;
    
    public SimpleCase(){
        stmtsAnalizator = new StmtsAnalizator();
        tConvertidor = new TConvertidor();
        errorsRep = new ErrorsRep();
    }

    public SimpleCase(List<Expression> labels, List<Statement> statements) {
        this.labels = labels;
        this.statements = statements;
        stmtsAnalizator = new StmtsAnalizator();
        tConvertidor = new TConvertidor();
        errorsRep = new ErrorsRep();
    }
    
    public ReturnCase validate(SymbolTable symbolTable, TypeTable typeTable, 
            List<String> semanticErrors, SemanticRestrictions restrictions, String typeLabel) {
        validateLabels(symbolTable, typeTable, semanticErrors, typeLabel);
        return stmtsAnalizator.validateInternalStmts(symbolTable, typeTable, semanticErrors, 
                restrictions, statements);
    }
    
    private void validateLabels(SymbolTable symbolTable, TypeTable typeTable, 
            List<String> semanticErrors, String typeLabel){
        if(labels != null && !labels.isEmpty()){
            for (Expression expLabel : labels) {
                Label type = expLabel.validateComplexData(symbolTable, typeTable, semanticErrors);
                if(!type.getName().equals(typeLabel)
                        && tConvertidor.canUpgradeType(typeLabel, type.getName())
                        ){
                    semanticErrors.add(errorsRep.incorrectTypeError(
                            type.getName(),
                            typeLabel,
                            type.getPosition()
                    ));
                }
            }
        }
    }
    
}

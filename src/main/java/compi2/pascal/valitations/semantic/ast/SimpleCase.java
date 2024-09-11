
package compi2.pascal.valitations.semantic.ast;

import compi2.pascal.valitations.analysis.actree.ActTreeGen;
import compi2.pascal.valitations.analysis.actree.PassActTree;
import compi2.pascal.valitations.analysis.symbolt.SymbolTable;
import compi2.pascal.valitations.analysis.typet.TypeTable;
import compi2.pascal.valitations.analysis.typet.convert.TConvertidor;
import compi2.pascal.valitations.analyzator.StmtsAnalizator;
import compi2.pascal.valitations.analysis.actree.ActivableInTree;
import compi2.pascal.valitations.semantic.ReturnCase;
import compi2.pascal.valitations.semantic.SemanticRestrictions;
import compi2.pascal.valitations.semantic.expr.Expression;
import compi2.pascal.valitations.semantic.obj.Label;
import compi2.pascal.valitations.util.ErrorsRep;
import compi2.pascal.valitations.util.Index;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class SimpleCase implements ActivableInTree{
    private List<Expression> labels;
    private List<Statement> statements;
    
    private StmtsAnalizator stmtsAnalizator;
    private TConvertidor tConvertidor;
    private ErrorsRep errorsRep;
    
    private ActTreeGen actTreeGen;
    
    public SimpleCase(){
        stmtsAnalizator = new StmtsAnalizator();
        tConvertidor = new TConvertidor();
        errorsRep = new ErrorsRep();
        actTreeGen = new ActTreeGen();
    }

    public SimpleCase(List<Expression> labels, List<Statement> statements) {
        this.labels = labels;
        this.statements = statements;
        stmtsAnalizator = new StmtsAnalizator();
        tConvertidor = new TConvertidor();
        errorsRep = new ErrorsRep();
        actTreeGen = new ActTreeGen();
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
                Label type = expLabel.validateSimpleData(symbolTable, semanticErrors);
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

    @Override
    public PassActTree getActivationNodeTree(Index index) {
        return actTreeGen.generatePass(statements, index);
    }
    
}


package compi2.pascal.valitations.semantic.ast;

import compi2.pascal.valitations.analysis.symbolt.RowST;
import compi2.pascal.valitations.analysis.symbolt.SymbolTable;
import compi2.pascal.valitations.analysis.typet.TypeTable;
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
public class Assignation extends Statement{
    private Label variable;
    private Expression expToAsign;
    
    public Assignation(Label variable, Expression expToAsign){
        super();
        this.variable = variable;
        this.expToAsign = expToAsign;
    }

    @Override
    public void validate(SymbolTable symbolTable, TypeTable typeTable, 
            List<String> semanticErrors, SemanticRestrictions restrictions) {
        //validar de que la variable exista 
        if(symbolTable.containsKey(variable.getName())){ 
            RowST row = symbolTable.get(variable.getName());
            
            //validar que la variable sea de la categoria adecuada
            switch (row.getCategory()) { 
                case Variable, Param_ref, Param_val -> {
                    Label type = expToAsign.validateComplexData(symbolTable, typeTable, semanticErrors);
                    
                    //validar el tipo de la expresion
                    if(!row.getType().equals(type.getName())){
                        semanticErrors.add(errorsRep.incorrectTypeError(
                                type.getName(), 
                                row.getType(), 
                                type.getPosition())
                        );
                    }
                }
                default -> {
                    semanticErrors.add(errorsRep.ilegalAssignation(
                            variable.getName(), 
                            row.getCategory().getName(), 
                            variable.getPosition()
                    ));
                }
            }
        } else {
            semanticErrors.add(errorsRep.undefiniteVarUseError(
                    variable.getName(), variable.getPosition())
            );
        }
    }
}

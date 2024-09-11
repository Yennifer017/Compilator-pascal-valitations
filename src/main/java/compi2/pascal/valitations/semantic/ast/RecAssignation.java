
package compi2.pascal.valitations.semantic.ast;

import compi2.pascal.valitations.analysis.actree.PassActTree;
import compi2.pascal.valitations.analysis.symbolt.RecordST;
import compi2.pascal.valitations.analysis.symbolt.RowST;
import compi2.pascal.valitations.analysis.symbolt.SymbolTable;
import compi2.pascal.valitations.analysis.typet.RecordType;
import compi2.pascal.valitations.analysis.typet.Type;
import compi2.pascal.valitations.analysis.typet.TypeTable;
import compi2.pascal.valitations.semantic.ReturnCase;
import compi2.pascal.valitations.semantic.SemanticRestrictions;
import compi2.pascal.valitations.semantic.expr.Expression;
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
public class RecAssignation extends Statement{
    private List<Label> variable;
    private Expression expToAsign;

    public RecAssignation(List<Label> variable, Expression expToAsign, Position initPos) {
        super(initPos);
        this.variable = variable;
        this.expToAsign = expToAsign;
    }

    @Override
    public ReturnCase validate(SymbolTable symbolTable, TypeTable typeTable, 
            List<String> semanticErrors, SemanticRestrictions restrictions) {
        if(variable != null && !variable.isEmpty() 
                && refAnalyzator.existReference(
                        symbolTable, 
                        semanticErrors, 
                        variable.get(0)
                )){
            RowST rowST = refAnalyzator.getFromST(symbolTable, variable.get(0).getName());
            if(rowST instanceof RecordST){
                RecordST recordST = (RecordST) rowST;
                SymbolTable currentST = recordST.getInternalST();
                TypeTable currentTT = typeTable;
                boolean accecesTT = false;
                
                //acceder al record y obtener su tipo
                String typeRecovery = recoveryType(currentTT, accecesTT, currentST, semanticErrors);
                if(typeRecovery == null){
                    semanticErrors.add(errorsRep.undefiniteVarRecordError(
                        variable.get(0).getName(),
                        variable.get(0).getPosition())
                    );
                } else {
                    //validar la asignacion
                    Label assignType = expToAsign.validateComplexData(
                            symbolTable, typeTable, semanticErrors);
                    if(!typeRecovery.equals(assignType.getName()) && 
                            !tConvert.canUpgradeType(typeRecovery, assignType.getName())){
                        semanticErrors.add(errorsRep.incorrectTypeError(
                                assignType.getName(), 
                                typeRecovery, 
                                assignType.getPosition()
                        ));
                    }
                }
            } else {
                semanticErrors.add(errorsRep.undefiniteVarRecordError(
                        variable.get(0).getName(),
                        variable.get(0).getPosition())
                );
            }            
        }
        return new ReturnCase(false);
    }

    @Override
    public PassActTree getActivationNodeTree(Index index) {
        return expToAsign.getActivationNodeTree(index);
    }
    
    private String recoveryType(TypeTable currentTT, boolean accecesTT, 
            SymbolTable currentST, List<String> semanticErrors){
        String typeRecovery = null;
        int i = 1;
        while (i < variable.size()) {
            Label currentAccess = variable.get(i);
            if (!accecesTT && currentST.containsKey(currentAccess.getName())) {
                RowST internalRow = currentST.get(currentAccess.getName());
                if (internalRow instanceof RecordST) {
                    currentST = ((RecordST) internalRow).getInternalST();
                } else if (i == variable.size() && internalRow.getType() != null) {
                    typeRecovery = internalRow.getType();
                    break;
                } else {
                    break;
                }
            } else if (currentTT.containsKey(currentAccess.getName())) {
                Type internalType = currentTT.get(currentAccess.getName());
                if (internalType instanceof RecordType) {
                    currentTT = ((RecordType) internalType).getInternalTypeTable();
                } else if (i == variable.size()) {
                    typeRecovery = internalType.getName();
                } else {
                    break;
                }
                accecesTT = true;
            } else {
                semanticErrors.add(errorsRep.undefiniteVarRecordError(
                        currentAccess.getName(),
                        currentAccess.getPosition())
                );
                break;
            }

            i++;
        }
        return typeRecovery;
    }
    
    
}

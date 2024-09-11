
package compi2.pascal.valitations.semantic.expr;

import compi2.pascal.valitations.analysis.actree.PassActTree;
import compi2.pascal.valitations.analysis.symbolt.RecordST;
import compi2.pascal.valitations.analysis.symbolt.RowST;
import compi2.pascal.valitations.analysis.symbolt.SymbolTable;
import compi2.pascal.valitations.analysis.typet.RecordType;
import compi2.pascal.valitations.analysis.typet.Type;
import compi2.pascal.valitations.analysis.typet.TypeTable;
import compi2.pascal.valitations.analyzator.Analyzator;
import compi2.pascal.valitations.semantic.obj.Label;
import compi2.pascal.valitations.util.Index;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class RecordUse extends Expression{
    private List<Label> access;

    public RecordUse(List<Label> access) {
        this.access = access;
    }

    @Override
    public Label validateSimpleData(SymbolTable symbolTable, List<String> semanticErrors) {
        semanticErrors.add(errorsRep.ilegalUseError(access.get(0).getName(), pos));
        return new Label(Analyzator.ERROR_TYPE, pos);
    }

    @Override
    public Label validateComplexData(SymbolTable symbolTable, TypeTable typeTable, 
            List<String> semanticErrors) {
        if(access != null && !access.isEmpty() 
                && refAnalyzator.existReference(
                        symbolTable, 
                        semanticErrors, 
                        new Label(access.get(0).getName(), pos))
                ){
            RowST rowST = refAnalyzator.getFromST(symbolTable, access.get(0).getName());
            if(rowST instanceof RecordST){
                RecordST recordST = (RecordST) rowST;
                SymbolTable currentST = recordST.getInternalST();
                TypeTable currentTT = typeTable;
                boolean accecesTT = false;
                
                int i = 1;
                while(i < access.size()) {
                    Label currentAccess = access.get(i);
                    if(!accecesTT && currentST.containsKey(currentAccess.getName())){
                        RowST internalRow = currentST.get(currentAccess.getName());
                        if(internalRow instanceof RecordST){
                            currentST = ((RecordST) internalRow).getInternalST();
                        } else if(i == access.size() -1
                                && internalRow.getType() != null){
                            return new Label(internalRow.getName(), pos);
                        } else {
                            break;
                        }
                    } else if (currentTT.containsKey(currentAccess.getName())){
                        Type internalType = currentTT.get(currentAccess.getName());
                        if(internalType instanceof RecordType){
                            currentTT = ((RecordType) internalType).getInternalTypeTable();
                        } else if(i == access.size() - 1){
                            return new Label(internalType.getName(), pos);
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

            } else {
                semanticErrors.add(errorsRep.undefiniteVarRecordError(
                        access.get(0).getName(),
                        access.get(0).getPosition())
                );
            }            
        }
        return new Label(Analyzator.ERROR_TYPE, pos);
    }

    @Override
    public PassActTree getActivationNodeTree(Index index) {
        return null;
    }

}

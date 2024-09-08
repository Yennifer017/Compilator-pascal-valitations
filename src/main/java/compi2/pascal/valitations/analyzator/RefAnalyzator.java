
package compi2.pascal.valitations.analyzator;

import compi2.pascal.valitations.analysis.symbolt.RowST;
import compi2.pascal.valitations.analysis.symbolt.SymbolTable;
import compi2.pascal.valitations.analysis.typet.convert.TConvertidor;
import compi2.pascal.valitations.util.ErrorsRep;
import compi2.pascal.valitations.util.Position;
import java.util.List;

/**
 *
 * @author blue-dragon
 */
public class RefAnalyzator {
    protected ErrorsRep errorsRep;
    protected TConvertidor tConvert;
    
    public RefAnalyzator(){
        errorsRep = new ErrorsRep();
        tConvert = new TConvertidor();
    }
    
    public boolean existReference(SymbolTable symbolTable, 
            List<String> semanticErrors, String id, Position position){
        SymbolTable currentTab = symbolTable;
        while (currentTab != null) {            
            if (currentTab.containsKey(id)) {
                return true;
            } else {
                currentTab = symbolTable.getFather();
            }
        }
        semanticErrors.add(errorsRep.undefiniteTypeError(
                id,
                position)
        );
        return false;
    }
    
    public RowST getFromST(SymbolTable symbolTable, String var){
        SymbolTable currentTab = symbolTable;
        while (currentTab != null) {            
            if (currentTab.containsKey(var)) {
                return currentTab.get(var);
            } else {
                currentTab = symbolTable.getFather();
            }
        }
        return null;
    }
    
}

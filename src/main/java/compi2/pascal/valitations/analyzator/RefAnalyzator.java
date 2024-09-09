
package compi2.pascal.valitations.analyzator;

import compi2.pascal.valitations.analysis.symbolt.RowST;
import compi2.pascal.valitations.analysis.symbolt.SymbolTable;
import compi2.pascal.valitations.analysis.typet.TypeTable;
import compi2.pascal.valitations.analysis.typet.convert.TConvertidor;
import compi2.pascal.valitations.semantic.obj.Label;
import compi2.pascal.valitations.util.ErrorsRep;
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
            List<String> semanticErrors, Label variable){
        SymbolTable currentTab = symbolTable;
        while (currentTab != null) {            
            if (currentTab.containsKey(variable.getName())) {
                return true;
            } else {
                currentTab = symbolTable.getFather();
            }
        }
        semanticErrors.add(errorsRep.undefiniteTypeError(
                variable.getName(),
                variable.getPosition())
        );
        return false;
    }
    
    /**
     * Retorna si en la tabla de tipos existe una referencia de tipos
     * @param typeTable
     * @param semanticErrors
     * @param type
     * @return verdadero si existe, falso de lo contrario
     */
    public boolean existReference(TypeTable typeTable, List<String> semanticErrors, Label type){
        TypeTable currentTypeTab = typeTable;
        while (currentTypeTab != null) {            
            if (currentTypeTab.containsKey(type.getName())) {
                return true;
            } else {
                currentTypeTab = currentTypeTab.getFather();
            }
        }
        semanticErrors.add(errorsRep.undefiniteTypeError(
                type.getName(),
                type.getPosition())
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
    
    public boolean canInsert(Label name, TypeTable typeTable, List<String> semanticErrors){
        if(typeTable.containsKey(name.getName())){
            semanticErrors.add(errorsRep.repeatedTypeError(
                    name.getName(), 
                    name.getPosition())
            );
            return false;
        } else {
            return true;
        }
    }
    
    public boolean canInsert(Label name, SymbolTable symbolTable, List<String> semanticErrors){
        if(symbolTable.containsKey(name.getName())){
            semanticErrors.add(errorsRep.repeatedDeclarationError(
                    name.getName(), 
                    name.getPosition())
            );
            return false;
        } else {
            return true;
        }
    }
    
}

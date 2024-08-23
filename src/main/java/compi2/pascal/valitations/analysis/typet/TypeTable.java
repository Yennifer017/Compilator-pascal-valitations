
package compi2.pascal.valitations.analysis.typet;

import compi2.pascal.valitations.exceptions.SemanticException;
import compi2.pascal.valitations.util.ErrorsRep;
import compi2.pascal.valitations.util.Position;
import java.util.HashMap;

/**
 *
 * @author blue-dragon
 */
public class TypeTable {
    private ErrorsRep errorsRep;
    
    private HashMap<Integer, Type> absTypeTab;
    private HashMap<String, Integer> searcher;
    
    public TypeTable(){
        this.absTypeTab = new HashMap<>();
        this.searcher = new HashMap<>();
    }
    
    public boolean canDefine(String type){
        return !searcher.containsKey(type);
    }
    
    public boolean define(String typeName, Type typeDefinition, Position position) throws SemanticException{
        if(canDefine(typeName)){
            searcher.put(typeName, absTypeTab.size());
            absTypeTab.put(absTypeTab.size(), typeDefinition);
        }
        throw new SemanticException(errorsRep.typeError(typeName, position));
    }
}


package compi2.pascal.valitations.analysis.typet;

import compi2.pascal.valitations.exceptions.SemanticException;
import compi2.pascal.valitations.util.ErrorsRep;
import compi2.pascal.valitations.util.Position;
import java.util.HashMap;

/**
 *
 * @author blue-dragon
 */
public final class TypeTable {
    private ErrorsRep errorsRep;
    
    private HashMap<Integer, Type> absTypeTab;
    private HashMap<String, Integer> searcher;
    
    public TypeTable(){
        this.absTypeTab = new HashMap<>();
        this.searcher = new HashMap<>();
        insertPrimData();
    }
    
    private void insertPrimData(){
        for (PrimitiveType primType : PrimitiveType.values()) {
            define(primType.getName(), primType.getType(), primType.getIdType());
        }
    }
    
    public boolean canDefine(String type){
        return !searcher.containsKey(type);
    }
    
    /**
     * Convierte a un tipo en uno que pueda ser ingresado en la tabla de tipos
     * @param typeBase
     * @param typeFather
     * @param incompletType
     * @param position
     * @return el tipo convertido
     */
    public Type convertData(String typeBase, String typeFather, Type incompletType, Position position) 
            throws SemanticException {
        if(typeBase != null && !searcher.containsKey(typeBase)){
            throw new SemanticException(errorsRep.typeUndefiniteError(typeBase + "(base)", position));
        } else if(typeBase != null){
            incompletType.setTypeBaseId(searcher.get(typeBase));
        }
        
        if(typeFather != null && !searcher.containsKey(typeFather)){
            throw new SemanticException(errorsRep.typeUndefiniteError(typeBase + "(base)", position));
        } else if (typeFather != null){
            incompletType.setTypeFatherId(searcher.get(typeFather));
        }
        return incompletType;
    }
    
    /**
     * Define un nuevo tipo en la tabla
     * @param typeName
     * @param typeDefinition
     * @param position
     * @throws compi2.pascal.valitations.exceptions.SemanticException
     */
    public void define(String typeName, Type typeDefinition, Position position) throws SemanticException{
        if(canDefine(typeName)){
            define(typeName, typeDefinition, absTypeTab.size());
        }
        throw new SemanticException(errorsRep.typeRepeatedError(typeName, position));
    }
    
    /**
     * Define en la tabla directamente un tipo
     */
    private void define(String typeName, Type typeDefinition, int numberId){
        searcher.put(typeName, numberId);
        absTypeTab.put(numberId, typeDefinition);
    }
}

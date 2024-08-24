
package compi2.pascal.valitations.analyzator;

import compi2.pascal.valitations.analysis.typet.TypeTable;
import compi2.pascal.valitations.semantic.obj.Label;
import compi2.pascal.valitations.semantic.obj.TypeDefinition;
import java.util.LinkedList;
import java.util.List;



/**
 *
 * @author blue-dragon
 */
public class GenTypeTab {
    
    public TypeTable generateTable(boolean insertPrimitive, List<TypeDefinition> typeDefinitions){
        TypeTable typeTable = new TypeTable(insertPrimitive);
        if(typeDefinitions != null){
            for (TypeDefinition typeDefinition : typeDefinitions) {
                System.out.print("id: ");
                System.out.print(typeDefinition.getNewType().getName());
                System.out.print(" - type: ");
                System.out.println(typeDefinition.getBaseType().getName());
            }
        }
        return typeTable;
    }
    
    public List<TypeDefinition> userDef(List<Label> ids, Label type){
        List<TypeDefinition> definitions = new LinkedList<>();
        for (Label id : ids) {
            definitions.add(new TypeDefinition(id, type));
        }
        return definitions; 
    }
    
}

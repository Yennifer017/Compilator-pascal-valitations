
package compi2.pascal.valitations.analyzator;

import compi2.pascal.valitations.analysis.typet.TypeTable;
import compi2.pascal.valitations.semantic.obj.DefAst;
import java.util.List;

/**
 *
 * @author blue-dragon
 */
public class GenTypeTab extends Generator {
    
    public TypeTable generateTable(boolean insertPrimitive, List<DefAst> typeDefinitions){
        TypeTable typeTable = new TypeTable(insertPrimitive);
        if(typeDefinitions != null){
            for (DefAst typeDefinition : typeDefinitions) {
                System.out.print("id: ");
            }
        }
        return typeTable;
    }
    
}

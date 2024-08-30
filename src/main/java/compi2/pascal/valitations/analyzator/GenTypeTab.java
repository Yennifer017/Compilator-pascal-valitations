
package compi2.pascal.valitations.analyzator;

import compi2.pascal.valitations.analysis.typet.Type;
import compi2.pascal.valitations.analysis.typet.TypeTable;
import compi2.pascal.valitations.semantic.obj.DefAst;
import java.util.List;

/**
 *
 * @author blue-dragon
 */
public class GenTypeTab extends Generator {
    
    public TypeTable generateTable(List<DefAst> typeDefinitions, 
            List<String> semanticErrors){
        TypeTable typeTable = new TypeTable(true);
        if(typeDefinitions != null && !typeDefinitions.isEmpty()){
            for (DefAst typeDefinition : typeDefinitions) {
                Type typeConverted = typeDefinition.generateType(typeTable, semanticErrors);
                typeTable.put(typeConverted.getName(), typeConverted);
            }
        } 
        return typeTable;
    }
    
}

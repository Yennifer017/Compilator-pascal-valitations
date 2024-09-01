
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
        insertData(typeDefinitions, semanticErrors, typeTable);
        return typeTable;
    }
    
    public TypeTable generateInternalTable(List<DefAst> typeDefinitions, 
            List<String> semanticErrors, TypeTable fatherTT){
        TypeTable typeTable = new TypeTable(false);
        typeTable.setFather(fatherTT);
        insertData(typeDefinitions, semanticErrors, typeTable);
        return typeTable;
    }
    
    private void insertData(List<DefAst> typeDefinitions, 
            List<String> semanticErrors, TypeTable typeTable){
        if(typeDefinitions != null && !typeDefinitions.isEmpty()){
            for (DefAst typeDefinition : typeDefinitions) {
                try {
                    Type typeConverted = typeDefinition.generateType(typeTable, semanticErrors);
                    if(typeConverted != null){
                        typeTable.put(typeConverted.getName(), typeConverted);
                    }
                } catch (NullPointerException e) {
                    System.out.println(e);
                }
            }
        } 
    }
    
    
}

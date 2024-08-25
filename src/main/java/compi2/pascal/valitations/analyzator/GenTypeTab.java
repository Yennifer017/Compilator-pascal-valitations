
package compi2.pascal.valitations.analyzator;

import compi2.pascal.valitations.analysis.typet.TypeTable;
import compi2.pascal.valitations.semantic.obj.Label;
import compi2.pascal.valitations.semantic.obj.Range;
import compi2.pascal.valitations.semantic.obj.SubRangeDef;
import compi2.pascal.valitations.semantic.obj.TypeDefAst;
import compi2.pascal.valitations.semantic.obj.TypeDefinition;
import java.util.LinkedList;
import java.util.List;



/**
 *
 * @author blue-dragon
 */
public class GenTypeTab {
    
    public TypeTable generateTable(boolean insertPrimitive, List<TypeDefAst> typeDefinitions){
        TypeTable typeTable = new TypeTable(insertPrimitive);
        if(typeDefinitions != null){
            for (TypeDefAst typeDefinition : typeDefinitions) {
                System.out.print("id: ");
                System.out.print(typeDefinition.getNewType().getName());
                System.out.print(" - type: ");
                System.out.println(typeDefinition.getBaseType().getName());
            }
        }
        return typeTable;
    }
    
    public List<TypeDefAst> userDef(List<Label> ids, Label type){
        List<TypeDefAst> definitions = new LinkedList<>();
        for (Label id : ids) {
            definitions.add(new TypeDefinition(id, type));
        }
        return definitions; 
    }

    public List<TypeDefAst> rangeDef(List<Label> ids, Range range){
        List<TypeDefAst> definitions = new LinkedList<>();
        for (Label id : ids) {
            definitions.add(new SubRangeDef(id, range));
        }
        return definitions;
    }
    
}

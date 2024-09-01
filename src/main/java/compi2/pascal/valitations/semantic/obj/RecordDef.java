
package compi2.pascal.valitations.semantic.obj;

import compi2.pascal.valitations.analysis.typet.RecordType;
import compi2.pascal.valitations.analysis.typet.Type;
import compi2.pascal.valitations.analysis.typet.TypeTable;
import compi2.pascal.valitations.analyzator.GenTypeTab;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class RecordDef extends DefAst{
    private List<DefAst> internalTypes;
    private GenTypeTab genTypeTab;
    
    
    public RecordDef(Label name, List<DefAst> internalTypes){
        super.name = name;
        this.internalTypes = internalTypes;
        genTypeTab = new GenTypeTab();
    }

    @Override
    public Type generateType(TypeTable typeTable, List<String> semanticErrors) {
        if(super.canInsert(typeTable, semanticErrors)){
            TypeTable internalTypeTable = genTypeTab.generateInternalTable(
                    internalTypes, 
                    semanticErrors, 
                    typeTable
            );
            return new RecordType(name.getName(), internalTypes.size(), internalTypeTable);
        }
        return null;
    }
}


package compi2.pascal.valitations.semantic.obj;

import compi2.pascal.valitations.analysis.typet.Type;
import compi2.pascal.valitations.analysis.typet.TypeTable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class RecordDef extends DefAst{
    List<DefAst> internalTypes;
    
    public RecordDef(Label name, List<DefAst> internalTypes){
        super.name = name;
        this.internalTypes = internalTypes;
    }

    @Override
    public Type generateType(TypeTable typeTable, List<String> semanticErrors) {
        return null;
    }
}

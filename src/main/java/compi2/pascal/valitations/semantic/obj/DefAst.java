
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
public abstract class DefAst {
    protected Label name;
    
    public abstract Type generateType(TypeTable typeTable, List<String> semanticErrors);
    
    protected boolean canInsert(TypeTable typeTable, List<String> semanticErrors){
        if(typeTable.containsKey(this.name.getName())){
            
        } else {
            
        }
    }
    
    /*
        TAREA DE ARCHIVOS
        Crear un trigger para que se pueda 
    */
}

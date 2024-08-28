
package compi2.pascal.valitations.semantic.obj;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class ArrayDef extends DefAst{
    
    private Range range;
    private Label base;
    
    public ArrayDef(Label name, Label base, Range range){
        super.name = name;
        this.base = base;
        this.range = range;
    }
}

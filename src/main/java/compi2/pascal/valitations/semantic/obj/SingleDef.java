
package compi2.pascal.valitations.semantic.obj;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class SingleDef extends DefAst {
    
    private Label base;

    public SingleDef(Label name, Label base) {
        this.name = name;
        this.base = base;
    }
 
}

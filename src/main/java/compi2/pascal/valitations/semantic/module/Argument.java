
package compi2.pascal.valitations.semantic.module;

import compi2.pascal.valitations.semantic.obj.Label;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class Argument {
    private boolean isForReference;
    private Label param;
    private Label type;

    public Argument(boolean isForReference, Label param, Label type) {
        this.isForReference = isForReference;
        this.param = param;
        this.type = type;
    }
    
}


package compi2.pascal.valitations.analysis.actree;

import compi2.pascal.valitations.analysis.symbolt.FunctionST;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class NodeAct {
    private int id;
    private FunctionST function;
    private List<NodeAct> activations;

    public NodeAct(int id, FunctionST function, List<NodeAct> activations) {
        this.id = id;
        this.function = function;
        this.activations = activations;
    }

    public NodeAct(int id, FunctionST function) {
        this.id = id;
        this.function = function;
    }

    public NodeAct() {
    }
    
    public boolean isCorrectorPath(){
        return this.function == null;
    }
    
}

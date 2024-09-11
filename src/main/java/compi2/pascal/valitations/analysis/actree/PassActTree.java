
package compi2.pascal.valitations.analysis.actree;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class PassActTree {
    private List<NodeAct> initNodes;
    private NodeAct finalNode;

    public PassActTree(List<NodeAct> initNodes, NodeAct finalNode) {
        this.initNodes = initNodes;
        this.finalNode = finalNode;
    }
    
}

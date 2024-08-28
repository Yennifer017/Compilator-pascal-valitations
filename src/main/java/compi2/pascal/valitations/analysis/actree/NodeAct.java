
package compi2.pascal.valitations.analysis.actree;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class NodeAct {
    private NodeAct father;
    private List<NodeAct> activations;
}

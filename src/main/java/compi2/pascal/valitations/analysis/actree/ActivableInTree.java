
package compi2.pascal.valitations.analysis.actree;

import compi2.pascal.valitations.analysis.actree.PassActTree;
import compi2.pascal.valitations.util.Index;

/**
 *
 * @author blue-dragon
 */
public interface ActivableInTree {
    public abstract PassActTree getActivationNodeTree(Index index);
}


package compi2.pascal.valitations.semantic.ast;

import compi2.pascal.valitations.semantic.obj.Label;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class StructUse extends Statement{
    private List<Label> accesses;
}

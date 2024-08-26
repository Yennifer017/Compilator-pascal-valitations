
package compi2.pascal.valitations.semantic.ast;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public abstract class ControlStruct extends Statement{
    protected List<Statement> internalStmts;
    
}

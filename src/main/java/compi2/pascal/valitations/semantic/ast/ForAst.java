
package compi2.pascal.valitations.semantic.ast;

import compi2.pascal.valitations.semantic.obj.Label;
import compi2.pascal.valitations.semantic.obj.Range;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class ForAst extends ControlStruct{
    private Label variable;
    private Range range;

    public ForAst(Label variable, Range range, List<Statement> internalStmts) {
        this.variable = variable;
        this.range = range;
        this.internalStmts = internalStmts;
    }
    
}

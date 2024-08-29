
package compi2.pascal.valitations.semantic.module;

import compi2.pascal.valitations.semantic.ast.Statement;
import compi2.pascal.valitations.semantic.obj.DefAst;
import compi2.pascal.valitations.semantic.obj.Label;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public abstract class ModuleDec {
    protected Label name;
    protected List<Argument> args;
    protected List<Statement> statements;
    protected List<DefAst> varDef;
}


package compi2.pascal.valitations.semantic.ast;

import java.util.List;

/**
 *
 * @author blue-dragon
 */
public class ElseAst extends ControlStruct{
    public ElseAst(List<Statement> stmts){
        super.internalStmts = stmts;
    }
}

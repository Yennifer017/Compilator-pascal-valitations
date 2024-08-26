
package compi2.pascal.valitations.analyzator;

import compi2.pascal.valitations.semantic.ast.Statement;
import java.util.LinkedList;

/**
 *
 * @author blue-dragon
 */
public class AstGen {
    public LinkedList<Statement> oneStmtInList(Statement stmt){
        LinkedList<Statement> list = new LinkedList<>();
        list.add(stmt);
        return list;
    }
}


package compi2.pascal.valitations.analyzator;

import compi2.pascal.valitations.semantic.ast.IfAst;
import compi2.pascal.valitations.semantic.ast.PassIf;
import compi2.pascal.valitations.semantic.ast.Statement;
import compi2.pascal.valitations.semantic.module.Argument;
import compi2.pascal.valitations.semantic.obj.Label;
import java.util.LinkedList;
import java.util.List;

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
    
    public LinkedList<Argument> generateArgs(List<Label> params, Label varType, boolean isForReference){
        LinkedList<Argument> list = new LinkedList<>();
        for (Label param : params) {
            list.add(new Argument(isForReference, param, varType));
        }
        return list;
    }
    
    public PassIf genPassIf(PassIf pass, IfAst ifAst){
        if(pass.getIfs() != null){
            pass.setIfs(new LinkedList<>());
        }
        pass.getIfs().add(0, ifAst);
        return pass;
    }
    
    public IfAst transformPassIf(PassIf pass){
        try {
            IfAst first = pass.getIfs().get(0);
            pass.getIfs().remove(0);
            return new IfAst(first.getCondition(),
                    first.getInternalStmts(),
                    pass.getIfs(),
                    pass.getElseAst()
            );
        } catch (Exception e) {
            return null;
        }
    }
    
}

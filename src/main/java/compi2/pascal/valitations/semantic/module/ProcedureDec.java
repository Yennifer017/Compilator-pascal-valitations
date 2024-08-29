
package compi2.pascal.valitations.semantic.module;

import compi2.pascal.valitations.semantic.ast.Statement;
import compi2.pascal.valitations.semantic.obj.DefAst;
import compi2.pascal.valitations.semantic.obj.Label;
import java.util.List;

/**
 *
 * @author blue-dragon
 */
public class ProcedureDec extends ModuleDec {
    
    public ProcedureDec(Label name, List<Argument> args, 
            List<DefAst> varDef, List<Statement> statements){
        super.name = name;
        super.args = args;
        super.statements = statements;
        super.varDef = varDef;
    }
    
}

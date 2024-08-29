
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
public class FunctionDec extends ModuleDec{
    
    private Label varType;
    
    public FunctionDec(Label name, Label varType, List<Argument> args, 
            List<DefAst> varDef, List<Statement> statements){
        super.name = name;
        this.varType = varType;
        super.args = args;
        super.statements = statements;
        super.varDef = varDef;
    }
    
}

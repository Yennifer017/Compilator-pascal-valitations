
package compi2.pascal.valitations.semantic.module;

import compi2.pascal.valitations.analyzator.Analyzator;
import compi2.pascal.valitations.analyzator.GenSymbolTab;
import compi2.pascal.valitations.analyzator.RefAnalyzator;
import compi2.pascal.valitations.analyzator.StmtsAnalizator;
import compi2.pascal.valitations.semantic.ast.Statement;
import compi2.pascal.valitations.semantic.obj.DefAst;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public abstract class ModuleDec extends DefAst{
    protected List<Argument> args;
    protected List<Statement> statements;
    protected List<DefAst> varDef;
    
    protected GenSymbolTab genSymbolTab;
    protected StmtsAnalizator stmtsAnalizator;
    protected RefAnalyzator refAnalyzator;
    
    public ModuleDec(){
        super();
        genSymbolTab = new GenSymbolTab();
        stmtsAnalizator = new StmtsAnalizator();
        refAnalyzator = new RefAnalyzator();
    }
    
    protected String updateFunctionName(){
        StringBuilder typeAddition = new StringBuilder("");
        if(args !=  null && !args.isEmpty()){
            for (Argument arg : args) {
                typeAddition.append(Analyzator.FUNCTION_SEPARATOR);
                typeAddition.append(arg.getType().getName());
            }
        }
        return this.name.getName() + typeAddition.toString();
    }
    
    
    
}

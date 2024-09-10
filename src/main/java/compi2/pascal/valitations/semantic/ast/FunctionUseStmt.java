
package compi2.pascal.valitations.semantic.ast;

import compi2.pascal.valitations.analysis.symbolt.SymbolTable;
import compi2.pascal.valitations.analysis.typet.TypeTable;
import compi2.pascal.valitations.analyzator.Analyzator;
import compi2.pascal.valitations.analyzator.FunctionRefAnalyzator;
import compi2.pascal.valitations.semantic.ReturnCase;
import compi2.pascal.valitations.semantic.SemanticRestrictions;
import compi2.pascal.valitations.semantic.expr.Expression;
import compi2.pascal.valitations.semantic.obj.Label;
import compi2.pascal.valitations.util.Position;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class FunctionUseStmt extends Statement{
    private boolean isSpecialFun;
    private Label nameFun;
    private List<Expression> args;
    private FunctionRefAnalyzator refFun;

    public FunctionUseStmt(Label nameFun, List<Expression> arguments) {
        super(nameFun.getPosition());
        this.nameFun = nameFun;
        this.args = arguments;
        isSpecialFun = false;
        refFun = new FunctionRefAnalyzator();
    }
    
    public FunctionUseStmt(List<Expression> arguments, Position initPos){
        super(initPos);
        this.args = arguments;
        this.isSpecialFun = true;
        refFun = new FunctionRefAnalyzator();
    }

    @Override
    public ReturnCase validate(SymbolTable symbolTable, TypeTable typeTable, 
            List<String> semanticErrors, SemanticRestrictions restrictions) {
        if(isSpecialFun){
            refFun.validateArgs(this.args, symbolTable, typeTable, semanticErrors);
        } else {
            List<String> argsStringList = refFun.validateArgs(
                    this.args, symbolTable, typeTable, semanticErrors
            );
            refFun.existReference(nameFun, symbolTable, typeTable, semanticErrors, argsStringList);
        }
        return new ReturnCase(false);
    }
    
}

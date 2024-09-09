
package compi2.pascal.valitations.semantic.ast;

import compi2.pascal.valitations.analysis.symbolt.SymbolTable;
import compi2.pascal.valitations.analysis.typet.TypeTable;
import compi2.pascal.valitations.semantic.ReturnCase;
import compi2.pascal.valitations.semantic.SemanticRestrictions;
import compi2.pascal.valitations.semantic.expr.Expression;
import compi2.pascal.valitations.semantic.obj.Label;
import compi2.pascal.valitations.util.Position;
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

    public FunctionUseStmt(Label nameFun, List<Expression> arguments) {
        super(nameFun.getPosition());
        this.nameFun = nameFun;
        this.args = arguments;
        isSpecialFun = false;
    }
    
    public FunctionUseStmt(List<Expression> arguments, Position initPos){
        super(initPos);
        this.args = arguments;
        this.isSpecialFun = true;
    }

    @Override
    public ReturnCase validate(SymbolTable symbolTable, TypeTable typeTable, List<String> semanticErrors, SemanticRestrictions restrictions) {
        
        return new ReturnCase(false);
    }
    
}

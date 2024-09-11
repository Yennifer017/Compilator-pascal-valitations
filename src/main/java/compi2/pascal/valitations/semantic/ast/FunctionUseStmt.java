
package compi2.pascal.valitations.semantic.ast;

import compi2.pascal.valitations.analysis.actree.ActTreeGen;
import compi2.pascal.valitations.analysis.actree.PassActTree;
import compi2.pascal.valitations.analysis.symbolt.FunctionST;
import compi2.pascal.valitations.analysis.symbolt.SymbolTable;
import compi2.pascal.valitations.analysis.typet.TypeTable;
import compi2.pascal.valitations.analyzator.FunctionRefAnalyzator;
import compi2.pascal.valitations.semantic.ReturnCase;
import compi2.pascal.valitations.semantic.SemanticRestrictions;
import compi2.pascal.valitations.semantic.expr.Expression;
import compi2.pascal.valitations.semantic.obj.Label;
import compi2.pascal.valitations.util.Index;
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
    private FunctionRefAnalyzator refFun;
    
    private FunctionST functionST;
    private ActTreeGen actTreeGen;

    public FunctionUseStmt(Label nameFun, List<Expression> arguments) {
        super(nameFun.getPosition());
        this.nameFun = nameFun;
        this.args = arguments;
        isSpecialFun = false;
        refFun = new FunctionRefAnalyzator();
        actTreeGen = new ActTreeGen();
    }
    
    public FunctionUseStmt(List<Expression> arguments, Position initPos){
        super(initPos);
        this.args = arguments;
        this.isSpecialFun = true;
        refFun = new FunctionRefAnalyzator();
        actTreeGen = new ActTreeGen();
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
            functionST = refFun.existReference(
                    nameFun, symbolTable, typeTable, semanticErrors, argsStringList
            );
        }
        return new ReturnCase(false);
    }

    @Override
    public PassActTree getActivationNodeTree(Index index) {
        return actTreeGen.generatePass(args, index, functionST);
    }
    
}

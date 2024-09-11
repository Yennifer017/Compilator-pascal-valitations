
package compi2.pascal.valitations.semantic.expr;

import compi2.pascal.valitations.analysis.actree.ActTreeGen;
import compi2.pascal.valitations.analysis.actree.PassActTree;
import compi2.pascal.valitations.analysis.symbolt.FunctionST;
import compi2.pascal.valitations.analysis.symbolt.SymbolTable;
import compi2.pascal.valitations.analysis.typet.TypeTable;
import compi2.pascal.valitations.analyzator.Analyzator;
import compi2.pascal.valitations.analyzator.FunctionRefAnalyzator;
import compi2.pascal.valitations.semantic.obj.Label;
import compi2.pascal.valitations.util.Index;
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
public class FunctionUse extends Expression{
    private String functionName;
    private List<Expression> params;
    private FunctionRefAnalyzator refFunc;
    
    private FunctionST functionST;
    private ActTreeGen actTreeGen;

    public FunctionUse(String functionName, List<Expression> params, Position pos) {
        super();
        this.functionName = functionName;
        this.params = params;
        super.pos = pos;
        this.refFunc = new FunctionRefAnalyzator();
        actTreeGen = new ActTreeGen();
    }
    
    public FunctionUse(String functionName, Position pos){
        this.functionName = functionName;
        this.params = new ArrayList<>();
        super.pos = pos;
        this.refFunc = new FunctionRefAnalyzator();
        actTreeGen = new ActTreeGen();
    }

    @Override
    public Label validateSimpleData(SymbolTable symbolTable, List<String> semanticErrors) {
        semanticErrors.add(errorsRep.ilegalUseError(functionName, pos));
        for (int i = 0; i < params.size(); i++) {
            Expression param = params.get(i);
            param.validateSimpleData(symbolTable, semanticErrors);
        }
        return new Label(Analyzator.ERROR_TYPE, pos);
    }

    @Override
    public Label validateComplexData(SymbolTable symbolTable, TypeTable typeTable, 
            List<String> semanticErrors) {
        List<String> argsStringList = refFunc.validateArgs(
                params, symbolTable, typeTable, semanticErrors
        );
        functionST = refFunc.existReference(
                new Label(functionName, pos), 
                symbolTable, 
                typeTable, 
                semanticErrors, argsStringList);
        if( functionST != null ) {
            return new Label(
                    refFunc.getTypeReturnFun(
                            new Label(functionName, pos), 
                            symbolTable, typeTable, argsStringList
                    ),
                    pos
            );
        }
        return new Label(Analyzator.ERROR_TYPE, pos);
    }

    @Override
    public PassActTree getActivationNodeTree(Index index) {
        return actTreeGen.generatePass(params, index, functionST);
    }
    
}

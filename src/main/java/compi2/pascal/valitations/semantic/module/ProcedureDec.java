
package compi2.pascal.valitations.semantic.module;

import compi2.pascal.valitations.analysis.symbolt.FunctionST;
import compi2.pascal.valitations.analysis.symbolt.RowST;
import compi2.pascal.valitations.analysis.symbolt.SymbolTable;
import compi2.pascal.valitations.analysis.typet.Type;
import compi2.pascal.valitations.analysis.typet.TypeTable;
import compi2.pascal.valitations.semantic.SemanticRestrictions;
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
        super();
        super.name = name;
        super.args = args;
        super.statements = statements;
        super.varDef = varDef;
    }

    @Override
    public Type generateType(TypeTable typeTable, List<String> semanticErrors) {
         throw new RuntimeException("Can't declare a procedure type in typeTable");
    }

    @Override
    public RowST generateRowST(SymbolTable symbolTable, TypeTable typeTable, List<String> semanticErrors) {
        SymbolTable internalST = genSymbolTab.generateInternalTable(
                symbolTable, typeTable, args, semanticErrors
        );
        genSymbolTab.addData(internalST, typeTable, varDef, semanticErrors);
        stmtsAnalizator.validateInternalStmts(
                internalST, 
                typeTable, 
                semanticErrors, 
                new SemanticRestrictions(
                        false, 
                        false, 
                        null, 
                        null
                ),
                statements
        );
        String nameForST = super.updateFunctionName();
        if(refAnalyzator.canInsert(new Label(nameForST, this.name.getPosition()), 
                symbolTable, semanticErrors)){
            return new FunctionST(
                    nameForST, 
                    internalST, 
                    args != null ? args.size() : 0
            );
        }
        return null;
    }
    
}

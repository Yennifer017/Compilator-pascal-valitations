
package compi2.pascal.valitations.semantic.module;

import compi2.pascal.valitations.analysis.symbolt.FunctionST;
import compi2.pascal.valitations.analysis.symbolt.RowST;
import compi2.pascal.valitations.analysis.symbolt.SymbolTable;
import compi2.pascal.valitations.analysis.typet.Type;
import compi2.pascal.valitations.analysis.typet.TypeTable;
import compi2.pascal.valitations.semantic.ReturnCase;
import compi2.pascal.valitations.semantic.SemanticRestrictions;
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
        super();
        super.name = name;
        this.varType = varType;
        super.args = args;
        super.statements = statements;
        super.varDef = varDef;
    }

    @Override
    public Type generateType(TypeTable typeTable, List<String> semanticErrors) {
         throw new RuntimeException("Can't declare a function type in typeTable");
    }

    @Override
    public RowST generateRowST(SymbolTable symbolTable, TypeTable typeTable, List<String> semanticErrors) {        
        SymbolTable internalST = genSymbolTab.generateInternalTable(
                symbolTable, typeTable, args, semanticErrors
        );
        genSymbolTab.addData(internalST, typeTable, varDef, semanticErrors);
        refAnalyzator.existReference(typeTable, semanticErrors, varType);
        ReturnCase returnCase = stmtsAnalizator.validateInternalStmts(
                internalST, 
                typeTable, 
                semanticErrors, 
                new SemanticRestrictions(
                        false, 
                        false, 
                        varType.getName(), 
                        name.getName()
                ),
                statements
        );
        if(!returnCase.isAllScenaries()){
            semanticErrors.add(errorsRep.missingReturnError(
                    this.name.getName(), 
                    varType.getName(), 
                    this.name.getPosition())
            );
        }
        String nameForST = super.updateFunctionName();
        if(refAnalyzator.canInsert(new Label(nameForST, this.name.getPosition()), 
                symbolTable, semanticErrors)){
            return new FunctionST(nameForST, varType.getName(), internalST);
        }
        return null;
    }
    
    
    
}

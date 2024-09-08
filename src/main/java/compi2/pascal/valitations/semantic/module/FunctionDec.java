
package compi2.pascal.valitations.semantic.module;

import compi2.pascal.valitations.analysis.symbolt.RowST;
import compi2.pascal.valitations.analysis.symbolt.SymbolTable;
import compi2.pascal.valitations.analysis.typet.Type;
import compi2.pascal.valitations.analysis.typet.TypeTable;
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

    @Override
    public Type generateType(TypeTable typeTable, List<String> semanticErrors) {
         throw new RuntimeException("Can't declare a function type in typeTable");
    }

    @Override
    public RowST generateRowST(SymbolTable symbolTable, TypeTable typeTable, List<String> semanticErrors) {
        /*
        TODO: 
        validar los stmts
        actualaizar el nombre de la funcion
        verificar si se puede ingresar en la tabla de simbolos (nombre valido, argumentos validos)
        si se puede devolver la funcion
        */
        
        SymbolTable internalST = genSymbolTab.generateInternalTable(
                symbolTable, typeTable, varDef, semanticErrors
        );
        genSymbolTab.addData(internalST, typeTable, varDef, semanticErrors);
        
        
        
        
        if(super.canInsert(symbolTable, semanticErrors)){
            
        }
        
    }
    
    
    
}

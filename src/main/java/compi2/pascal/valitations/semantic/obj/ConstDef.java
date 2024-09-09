
package compi2.pascal.valitations.semantic.obj;

import compi2.pascal.valitations.analysis.symbolt.Category;
import compi2.pascal.valitations.analysis.symbolt.RowST;
import compi2.pascal.valitations.analysis.symbolt.SingleData;
import compi2.pascal.valitations.analysis.symbolt.SymbolTable;
import compi2.pascal.valitations.analysis.typet.Type;
import compi2.pascal.valitations.analysis.typet.TypeTable;
import compi2.pascal.valitations.semantic.expr.Expression;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class ConstDef extends DefAst{
    private Expression expression;
    
    public ConstDef(Label name, Expression expression){
        super();
        super.name = name;
        this.expression = expression;
    }

    @Override
    public Type generateType(TypeTable typeTable, List<String> semanticErrors) {
        throw new RuntimeException("Can't declare a const type in typeTable");
    }

    @Override
    public RowST generateRowST(SymbolTable symbolTable, TypeTable typeTable, List<String> semanticErrors) {
        if(canInsert(typeTable, semanticErrors)){
            Label nameType = expression.validateSimpleData(semanticErrors);
            if(refAnalyzator.existReference(typeTable, semanticErrors, nameType)){
                int lastDir = symbolTable.getLastDir();
                symbolTable.incrementLastDir(1);
                return new SingleData(
                        super.name.getName(), 
                        Category.Constant, 
                        nameType.getName(), 
                        lastDir
                );
            }
        }
        return null;
    }
    
    
}

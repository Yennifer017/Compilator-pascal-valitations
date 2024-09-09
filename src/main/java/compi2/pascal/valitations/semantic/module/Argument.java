
package compi2.pascal.valitations.semantic.module;

import compi2.pascal.valitations.analysis.symbolt.Category;
import compi2.pascal.valitations.analysis.symbolt.RowST;
import compi2.pascal.valitations.analysis.symbolt.SingleData;
import compi2.pascal.valitations.analysis.symbolt.SymbolTable;
import compi2.pascal.valitations.analysis.typet.Type;
import compi2.pascal.valitations.analysis.typet.TypeTable;
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
public class Argument extends DefAst{
    private boolean isForReference;
    private Label type;

    public Argument(boolean isForReference, Label name, Label type) {
        super();
        this.isForReference = isForReference;
        super.name = name;
        this.type = type;
    }

    @Override
    public Type generateType(TypeTable typeTable, List<String> semanticErrors) {
        throw new RuntimeException("Can't declare an arguments in typeTable");
    }

    @Override
    public RowST generateRowST(SymbolTable symbolTable, TypeTable typeTable, List<String> semanticErrors) {
        if(canInsert(symbolTable, semanticErrors) && refAnalyzator.existReference(typeTable, semanticErrors, type)){
            return new SingleData(
                    name.getName(),
                    isForReference ? Category.Param_ref : Category.Param_val, 
                    type.getName(), 
                    0
            );
        }
        return null;
    }
    
}

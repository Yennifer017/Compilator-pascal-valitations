
package compi2.pascal.valitations.semantic.obj;

import compi2.pascal.valitations.analysis.symbolt.Category;
import compi2.pascal.valitations.analysis.symbolt.RowST;
import compi2.pascal.valitations.analysis.symbolt.SingleData;
import compi2.pascal.valitations.analysis.symbolt.SymbolTable;
import compi2.pascal.valitations.analysis.typet.Type;
import compi2.pascal.valitations.analysis.typet.TypeTable;
import compi2.pascal.valitations.analysis.typet.UserType;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class SingleDef extends DefAst {
    
    private Label base;

    public SingleDef(Label name, Label base) {
        super();
        this.name = name;
        this.base = base;
    }

    @Override
    public Type generateType(TypeTable typeTable, List<String> semanticErrors) {
        if(super.canInsert(typeTable, semanticErrors) && existReference(typeTable, semanticErrors, base)){
            return new UserType(name.getName(), base.getName(), 1);
        }
        return null;
    }

    @Override
    public RowST generateRowST(SymbolTable symbolTable, TypeTable typeTable, List<String> semanticErrors) {
        if(canInsert(symbolTable, semanticErrors)){
            if(existReference(typeTable, semanticErrors, base)){
                int lastDir = symbolTable.getLastDir();
                symbolTable.incrementLastDir(1);
                return new SingleData(
                        name.getName(), 
                        Category.Variable, 
                        base.getName(), 
                        lastDir
                );
            }
        }
        return null;
    }
    
}

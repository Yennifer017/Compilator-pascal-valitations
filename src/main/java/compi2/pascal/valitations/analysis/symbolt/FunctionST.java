
package compi2.pascal.valitations.analysis.symbolt;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class FunctionST extends RowST{
    private SymbolTable internalST;
    private int numParams;

    public FunctionST(String name, SymbolTable internalST) {
        super(name, Category.Procedure, null);
        this.internalST = internalST;
        
    }
    
    public FunctionST(String name, String type, SymbolTable internalST){
        super(name, Category.Function, type);
        this.internalST = internalST;
    }
    
}


package compi2.pascal.valitations.analysis.symbolt;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class FunctionST extends RowST{
    private SymbolTable symbolTable;
    private int numParams;
    
    public void referenceFather(SymbolTable father){
        this.symbolTable.setFather(father);
    }
    
}

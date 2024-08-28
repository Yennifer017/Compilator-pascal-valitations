
package compi2.pascal.valitations.semantic.ast;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class SimpleStruct extends Statement{
    public final static int CONTINUE = 1;
    public final static int BREAK = 2;
    private int typeStruct;
    
    public SimpleStruct(int typeStruct){
        this.typeStruct = typeStruct;
    }
    
}

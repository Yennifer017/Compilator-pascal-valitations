
package compi2.pascal.valitations.analysis.typet;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class ArrayType extends Type{
    private String typeBase;
    private int min, max;
    
    public ArrayType(String name, int dimention) {
        super(name, dimention);
    }
    
}

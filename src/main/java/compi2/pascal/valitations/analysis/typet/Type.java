
package compi2.pascal.valitations.analysis.typet;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class Type {
    
    private int typeBaseId; //en cual esta basado
    private int typeFatherId;
    private int dimention;
    private int min, max;
    
    public final static int NOTHING = -1;
    
    public Type(){
        typeBaseId = -1;
        typeFatherId = -1;
    }

    public Type(int dimention) {
        this.dimention = dimention;
        typeBaseId = -1;
        typeFatherId = -1;
    }
    
    
    
    public boolean isCompleate(){
        return this.dimention != 0;
    }
}

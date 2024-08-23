
package compi2.pascal.valitations.analysis.typet;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class Type {
    
    private int typeBaseId;
    private int typeFatherId;
    private int dimention;
    private int min, max;
}


package compi2.pascal.valitations.analysis.typet;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class RecordType extends Type {
    private TypeTable internalTypeTable;
    
    public RecordType(String name, int dimention, TypeTable internalTypeTable) {
        super(name, dimention);
        this.internalTypeTable = internalTypeTable;
    }
    
}

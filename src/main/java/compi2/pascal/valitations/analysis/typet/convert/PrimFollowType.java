
package compi2.pascal.valitations.analysis.typet.convert;

import compi2.pascal.valitations.analysis.typet.PrimitiveType;
import compi2.pascal.valitations.util.Position;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class PrimFollowType {
    PrimitiveType primitiveType;
    Position position;

    public PrimFollowType(PrimitiveType primitiveType, Position position) {
        this.primitiveType = primitiveType;
        this.position = position;
    }
    
}

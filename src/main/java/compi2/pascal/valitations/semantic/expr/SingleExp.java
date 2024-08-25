
package compi2.pascal.valitations.semantic.expr;

import compi2.pascal.valitations.analysis.typet.PrimitiveType;
import compi2.pascal.valitations.util.Position;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class SingleExp extends Expression{
    private String accessId;
    private Object object;
    
    public SingleExp(PrimitiveType type, Position pos){
        super.type = type;
        super.pos = pos;
    }
    
    public SingleExp(PrimitiveType type, Object object, Position pos){
        super.type = type;
        this.object = object;
        super.pos = pos;
    }
    
    public SingleExp(String accessId, Position pos) {
        this.accessId = accessId;
        super.pos = pos;
        
    }
}

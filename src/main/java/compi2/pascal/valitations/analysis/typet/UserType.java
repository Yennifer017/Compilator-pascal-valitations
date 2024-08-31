
package compi2.pascal.valitations.analysis.typet;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class UserType extends Type{
    
    private String fatherType;

    public UserType(String name, String fatherType, int dimention) {
        super(name, dimention);
        this.fatherType = fatherType;
    }
    
}

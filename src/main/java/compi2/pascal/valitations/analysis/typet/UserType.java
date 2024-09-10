
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
    
    @Override
    public StringBuilder getGraphRowCode(String codeRelated){
        StringBuilder builder = getInitialGraphData();
        builder.append(ttGrapher.getDataGraphCode("Tipo personalizado"));
        builder.append(ttGrapher.getDataGraphCode(fatherType));
        builder.append(ttGrapher.getNoDataGraphCode());
        return builder;
    }
    
}

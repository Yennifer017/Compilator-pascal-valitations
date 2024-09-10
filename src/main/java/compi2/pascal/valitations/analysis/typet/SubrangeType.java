
package compi2.pascal.valitations.analysis.typet;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class SubrangeType extends Type{
    private Limits limits;
    private String fatherType;
    
    public SubrangeType(String name, int dimention, Limits limits) {
        super(name, dimention);
        this.fatherType = PrimitiveType.IntegerPT.getName();
        this.limits = limits;
    }
    
    @Override
    public StringBuilder getGraphRowCode(String codeRelated){
        StringBuilder builder = getInitialGraphData();
        builder.append(ttGrapher.getDataGraphCode(fatherType));
        builder.append(ttGrapher.getDataGraphCode(limits.toString()));
        return builder;
    }
}

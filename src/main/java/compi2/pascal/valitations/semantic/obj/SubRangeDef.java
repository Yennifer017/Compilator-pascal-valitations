
package compi2.pascal.valitations.semantic.obj;

import compi2.pascal.valitations.analysis.typet.PrimitiveType;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class SubRangeDef extends DefAst{
    private Range range;
    private Label base;

    public SubRangeDef(Label name, Range range) {
        this.range = range;
        super.name = name;
        this.base = new Label(PrimitiveType.IntegerPT.getName(), null);
    }
    
    
}

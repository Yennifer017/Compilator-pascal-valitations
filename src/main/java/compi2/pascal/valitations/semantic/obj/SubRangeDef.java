
package compi2.pascal.valitations.semantic.obj;

import compi2.pascal.valitations.analysis.typet.Limits;
import compi2.pascal.valitations.analysis.typet.PrimitiveType;
import compi2.pascal.valitations.analysis.typet.SubrangeType;
import compi2.pascal.valitations.analysis.typet.Type;
import compi2.pascal.valitations.analysis.typet.TypeTable;
import java.util.List;
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
        super();
        this.range = range;
        super.name = name;
        this.base = new Label(PrimitiveType.IntegerPT.getName(), null);
    }

    @Override
    public Type generateType(TypeTable typeTable, List<String> semanticErrors) {
        if(super.canInsert(typeTable, semanticErrors)){
            return new SubrangeType(
                    this.name.getName(), 
                    1, 
                    range.validate(semanticErrors, this)
            );
        } else {
            return null;
        }
    }
    
    
}

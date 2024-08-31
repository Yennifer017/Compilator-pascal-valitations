
package compi2.pascal.valitations.semantic.expr;

import compi2.pascal.valitations.analyzator.Analyzator;
import compi2.pascal.valitations.semantic.obj.Label;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class RecordUse extends Expression{
    private List<Label> access;

    public RecordUse(List<Label> access) {
        this.access = access;
    }

    @Override
    public Label validateSimpleData(List<String> semanticErrors) {
        semanticErrors.add(errorsRep.ilegalUseError(access.get(0).getName(), pos));
        return new Label(Analyzator.ERROR_TYPE, pos);
    }

}

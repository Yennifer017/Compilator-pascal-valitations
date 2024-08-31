
package compi2.pascal.valitations.semantic.obj;

import compi2.pascal.valitations.analysis.typet.ArrayType;
import compi2.pascal.valitations.analysis.typet.Limits;
import compi2.pascal.valitations.analysis.typet.Type;
import compi2.pascal.valitations.analysis.typet.TypeTable;
import compi2.pascal.valitations.exceptions.SemanticException;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class ArrayDef extends DefAst{
    
    private Range range;
    private Label base;
    
    public ArrayDef(Label name, Label base, Range range){
        super.name = name;
        this.base = base;
        this.range = range;
    }

    @Override
    public Type generateType(TypeTable typeTable, List<String> semanticErrors) {
        if(super.canInsert(typeTable, semanticErrors)
                && super.existReference(typeTable, semanticErrors, base)){
            try {
                Limits limit = range.validate(semanticErrors, this);
                return new ArrayType(
                        name.getName(),
                        limit.getMaxlimit() - limit.getLowlimit(),
                        base.getName(),
                        limit
                );
            } catch (SemanticException ex) {
                semanticErrors.add(super.errorsRep.negativeIndexError(name.getName(), name.getPosition()));
            }
        }
        return null;
    }
}

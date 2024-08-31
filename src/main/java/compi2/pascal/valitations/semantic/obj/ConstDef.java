
package compi2.pascal.valitations.semantic.obj;

import compi2.pascal.valitations.analysis.typet.Type;
import compi2.pascal.valitations.analysis.typet.TypeTable;
import compi2.pascal.valitations.semantic.expr.Expression;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class ConstDef extends DefAst{
    private Expression expression;
    
    public ConstDef(Label name, Expression expression){
        super.name = name;
        this.expression = expression;
    }

    @Override
    public Type generateType(TypeTable typeTable, List<String> semanticErrors) {
        throw new RuntimeException("Can't declare a const type in typeTable");
    }
}

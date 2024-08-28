
package compi2.pascal.valitations.semantic.ast;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class PassIf {
    private ElseAst elseAst;
    private List<Statement> statements;
    private List<IfAst> ifs;

    public PassIf(ElseAst elseAst, List<Statement> statements) {
        this.elseAst = elseAst;
        this.statements = statements;
    }

    public PassIf(List<Statement> statements) {
        this.statements = statements;
    }
    
    public PassIf(List<IfAst> ifs, ElseAst elseAst){
        this.ifs = ifs;
        this.elseAst = elseAst;
    }
    
    public PassIf(ElseAst elseAst){
        this.elseAst = elseAst;
    }
    
}

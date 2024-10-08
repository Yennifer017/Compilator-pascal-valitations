
package compi2.pascal.valitations.semantic.ast;

import compi2.pascal.valitations.analysis.actree.PassActTree;
import compi2.pascal.valitations.semantic.SemanticRestrictions;
import compi2.pascal.valitations.analysis.symbolt.SymbolTable;
import compi2.pascal.valitations.analysis.typet.TypeTable;
import compi2.pascal.valitations.semantic.ReturnCase;
import compi2.pascal.valitations.util.Index;
import compi2.pascal.valitations.util.Position;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class SimpleStmt extends Statement{
    public final static int CONTINUE = 1;
    public final static int BREAK = 2;
    private int typeStruct;
    
    public SimpleStmt(int typeStruct, Position pos){
        super(pos);
        this.typeStruct = typeStruct;
    }

    @Override
    public ReturnCase validate(SymbolTable symbolTable, TypeTable typeTable, 
            List<String> semanticErrors, SemanticRestrictions restrictions) {
        switch (typeStruct) {
            case CONTINUE -> {
                if(!restrictions.allowContinue()){
                    semanticErrors.add(super.errorsRep.ilegalStmt("CONTINUE", initPos));
                }
            }
            case BREAK -> {
                if(!restrictions.allowBreak()){
                    semanticErrors.add(super.errorsRep.ilegalStmt("BREAK", initPos));
                }
            }
            default -> throw new AssertionError();
        }
        return new ReturnCase(false);
    }

    @Override
    public PassActTree getActivationNodeTree(Index index) {
        return null;
    }
    
}

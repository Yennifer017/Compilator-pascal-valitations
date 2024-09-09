
package compi2.pascal.valitations.semantic.ast;

import compi2.pascal.valitations.analysis.symbolt.SymbolTable;
import compi2.pascal.valitations.analysis.typet.TypeTable;
import compi2.pascal.valitations.util.Position;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class SimpleStruct extends Statement{
    public final static int CONTINUE = 1;
    public final static int BREAK = 2;
    private int typeStruct;
    private Position position;
    
    public SimpleStruct(int typeStruct, Position pos){
        super();
        this.typeStruct = typeStruct;
    }

    @Override
    public void validate(SymbolTable symbolTable, TypeTable typeTable, 
            List<String> semanticErrors, SemanticRestrictions restrictions) {
        switch (typeStruct) {
            case CONTINUE -> {
                if(!restrictions.allowContinue()){
                    semanticErrors.add(super.errorsRep.ilegalStmt("CONTINUE", position));
                }
            }
            case BREAK -> {
                if(!restrictions.allowBreak()){
                    semanticErrors.add(super.errorsRep.ilegalStmt("BREAK", position));
                }
            }
            default -> throw new AssertionError();
        }
    }
    
}

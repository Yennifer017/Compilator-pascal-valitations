
package compi2.pascal.valitations.semantic.obj;

import compi2.pascal.valitations.analysis.symbolt.ArrayST;
import compi2.pascal.valitations.analysis.symbolt.RowST;
import compi2.pascal.valitations.analysis.symbolt.SymbolTable;
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
        super();
        super.name = name;
        this.base = base;
        this.range = range;
    }

    @Override
    public Type generateType(TypeTable typeTable, List<String> semanticErrors) {
        if(super.canInsert(typeTable, semanticErrors)
                && refAnalyzator.existReference(typeTable, semanticErrors, base)){
            try {
                Limits limit = range.validate(null, semanticErrors, this);
                return new ArrayType(
                        name.getName(),
                        base.getName(),
                        limit
                );
            } catch (SemanticException ex) {
                semanticErrors.add(super.errorsRep.negativeIndexError(name.getName(), 
                        name.getPosition()));
            }
        }
        return null;
    }

    @Override
    public RowST generateRowST(SymbolTable symbolTable, TypeTable typeTable, List<String> semanticErrors) {
        if(canInsert(symbolTable, semanticErrors)){
            Limits limits = range.validate(symbolTable, semanticErrors, this);
            if(refAnalyzator.existReference(typeTable, semanticErrors, base)){
                try {
                    ArrayST arrayST = new ArrayST(
                            name.getName(), 
                            base.getName(), 
                            limits, 
                            symbolTable.getLastDir()
                    );
                    symbolTable.incrementLastDir(arrayST.getTotalIndex());
                    return arrayST;
                } catch (SemanticException ex) {
                    semanticErrors.add(super.errorsRep.negativeIndexError(name.getName(), 
                            name.getPosition()));
                }
            }
        }
        return null;
    }
}

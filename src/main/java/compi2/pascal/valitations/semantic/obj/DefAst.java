
package compi2.pascal.valitations.semantic.obj;

import compi2.pascal.valitations.analysis.symbolt.RowST;
import compi2.pascal.valitations.analysis.symbolt.SymbolTable;
import compi2.pascal.valitations.analysis.typet.PrimitiveType;
import compi2.pascal.valitations.analysis.typet.Type;
import compi2.pascal.valitations.analysis.typet.TypeTable;
import compi2.pascal.valitations.analysis.typet.convert.TConvertidor;
import compi2.pascal.valitations.analyzator.RefAnalyzator;
import compi2.pascal.valitations.semantic.expr.Expression;
import compi2.pascal.valitations.util.ErrorsRep;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public abstract class DefAst {
    protected Label name;
    protected ErrorsRep errorsRep;
    protected TConvertidor tConvert;
    protected RefAnalyzator refAnalyzator;
    
    public DefAst(){
        errorsRep = new ErrorsRep();
        tConvert = new TConvertidor();
        refAnalyzator = new RefAnalyzator();
    }
    
    public abstract Type generateType(TypeTable typeTable, List<String> semanticErrors);
    
    public abstract RowST generateRowST(SymbolTable symbolTable, TypeTable typeTable, 
            List<String> semanticErrors);
    
    protected boolean canInsert(TypeTable typeTable, List<String> semanticErrors){
        return refAnalyzator.canInsert(this.name, typeTable, semanticErrors);
    }
    
    protected boolean canInsert(SymbolTable symbolTable, List<String> semanticErrors){
        return refAnalyzator.canInsert(this.name, symbolTable, semanticErrors);
    }
    
    
    protected void validateNumericIntegerType(SymbolTable symbolTable, Expression expression, List<String> semanticErrors){
        Label type = expression.validateSimpleData(symbolTable, semanticErrors);
        if(!tConvert.isNumericIntegerType(type.getName())){
            semanticErrors.add(errorsRep.inesperateTypeError(PrimitiveType.IntegerPT.getName(), 
                    type.getPosition()));
        } 
    }

}

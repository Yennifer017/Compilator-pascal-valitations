
package compi2.pascal.valitations.semantic.obj;

import compi2.pascal.valitations.analysis.symbolt.RowST;
import compi2.pascal.valitations.analysis.symbolt.SymbolTable;
import compi2.pascal.valitations.analysis.typet.PrimitiveType;
import compi2.pascal.valitations.analysis.typet.Type;
import compi2.pascal.valitations.analysis.typet.TypeTable;
import compi2.pascal.valitations.analysis.typet.convert.TConvertidor;
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
    
    public DefAst(){
        errorsRep = new ErrorsRep();
        tConvert = new TConvertidor();
    }
    
    public abstract Type generateType(TypeTable typeTable, List<String> semanticErrors);
    
    public abstract RowST generateRowST(SymbolTable symbolTable, TypeTable typeTable, 
            List<String> semanticErrors);
    
    protected boolean canInsert(TypeTable typeTable, List<String> semanticErrors){
        if(typeTable.containsKey(this.name.getName())){
            semanticErrors.add(errorsRep.repeatedTypeError(
                    this.name.getName(), 
                    this.name.getPosition())
            );
            return false;
        } else {
            return true;
        }
    }
    
    protected boolean canInsert(SymbolTable symbolTable, List<String> semanticErrors){
        if(symbolTable.containsKey(this.name.getName())){
            semanticErrors.add(errorsRep.repeatedDeclarationError(
                    this.name.getName(), 
                    this.name.getPosition())
            );
            return false;
        } else {
            return true;
        }
    }
    
    /**
     * Retorna si en la tabla de tipos existe una referencia de tipos
     * @param typeTable
     * @param semanticErrors
     * @param type
     * @return verdadero si existe, falso de lo contrario
     */
    protected boolean existReference(TypeTable typeTable, List<String> semanticErrors, Label type){
        TypeTable currentTypeTab = typeTable;
        while (currentTypeTab != null) {            
            if (typeTable.containsKey(type.getName())) {
                return true;
            } else {
                currentTypeTab = currentTypeTab.getFather();
            }
        }
        semanticErrors.add(errorsRep.undefiniteTypeError(
                type.getName(),
                type.getPosition())
        );
        return false;
    }
    
    protected void validateNumericIntegerType(Expression expression, List<String> semanticErrors){
        Label type = expression.validateSimpleData(semanticErrors);
        if(!tConvert.isNumericIntegerType(type.getName())){
            semanticErrors.add(errorsRep.inesperateTypeError(PrimitiveType.IntegerPT.getName(), 
                    type.getPosition()));
        } 
    }

}

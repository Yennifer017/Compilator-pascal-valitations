
package compi2.pascal.valitations.semantic.expr;

import compi2.pascal.valitations.analysis.actree.PassActTree;
import compi2.pascal.valitations.analysis.symbolt.SymbolTable;
import compi2.pascal.valitations.analysis.typet.PrimitiveType;
import compi2.pascal.valitations.analysis.typet.TypeTable;
import compi2.pascal.valitations.analysis.typet.convert.TConvertidor;
import compi2.pascal.valitations.analyzator.RefAnalyzator;
import compi2.pascal.valitations.analysis.actree.ActivableInTree;
import compi2.pascal.valitations.semantic.obj.Label;
import compi2.pascal.valitations.util.ErrorsRep;
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
public abstract class Expression implements ActivableInTree{
    
    protected Position pos;
    protected PrimitiveType type;
    
    protected ErrorsRep errorsRep;
    protected TConvertidor tConvert;
    protected RefAnalyzator refAnalyzator;
    
    public Expression(){
        errorsRep = new ErrorsRep();
        tConvert = new TConvertidor();
        refAnalyzator = new RefAnalyzator();
    }
    
    public boolean canRecoveryIntValue(){
        return false;
    }
    
    public int recoveryIntegerData(){
        throw new RuntimeException("Can't recovery data");
    }
    
    /**
     * Valida que la expresion solo contenga datos simples 
     * (no uso de variables/tipos/arreglos/structs)
     * 
     * @param symbolTable
     * @param semanticErrors
     * @return el nombre del tipo encontrado
     */
    public abstract Label validateSimpleData(SymbolTable symbolTable, 
            List<String> semanticErrors);
    
    /**
     * Valida el tipo de la expresion
     * @param symbolTable
     * @param typeTable
     * @param semanticErrors
     * @return el nombre del tipo encontrado
     */
    public abstract Label validateComplexData(SymbolTable symbolTable, 
            TypeTable typeTable, List<String> semanticErrors);
    
    
    @Override
    public abstract PassActTree getActivationNodeTree(Index index);
    
}

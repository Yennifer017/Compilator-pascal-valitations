
package compi2.pascal.valitations.semantic.ast;

import compi2.pascal.valitations.analysis.actree.ActTreeGen;
import compi2.pascal.valitations.analysis.actree.PassActTree;
import compi2.pascal.valitations.semantic.SemanticRestrictions;
import compi2.pascal.valitations.analysis.symbolt.Category;
import compi2.pascal.valitations.analysis.symbolt.RowST;
import compi2.pascal.valitations.analysis.symbolt.SymbolTable;
import compi2.pascal.valitations.analysis.typet.PrimitiveType;
import compi2.pascal.valitations.analysis.typet.TypeTable;
import compi2.pascal.valitations.semantic.ReturnCase;
import compi2.pascal.valitations.semantic.expr.Expression;
import compi2.pascal.valitations.semantic.obj.Label;
import compi2.pascal.valitations.util.Index;
import java.util.List;

/**
 *
 * @author blue-dragon
 */
public class ArrayAssign extends Statement{
    private Label identifier;
    private Expression indexExp;
    private Expression valToAssign;
    
    private ActTreeGen actTreeGen;

    public ArrayAssign(Label identifier, Expression index, Expression valToAssign) {
        super(identifier.getPosition());
        this.identifier = identifier;
        this.indexExp = index;
        this.valToAssign = valToAssign;
        
        actTreeGen = new ActTreeGen();
    }
    
    @Override
    public ReturnCase validate(SymbolTable symbolTable, TypeTable typeTable, 
            List<String> semanticErrors, SemanticRestrictions restrictions) {
        //validar el index
        Label typeIndex = indexExp.validateComplexData(symbolTable, typeTable, semanticErrors);
        if(!super.tConvert.isNumericIntegerType(typeIndex.getName())){
            semanticErrors.add(errorsRep.incorrectTypeError(
                    typeIndex.getName(), 
                    PrimitiveType.IntegerPT.getName(), 
                    typeIndex.getPosition())
            );
        }
        
        //validar la asignacion
        Label typeAssign = valToAssign.validateComplexData(symbolTable, typeTable, semanticErrors);
        if(refAnalyzator.existReference(
                symbolTable, semanticErrors, identifier)){
            RowST row = refAnalyzator.getFromST(symbolTable, identifier.getName());
            if(row.getCategory() != Category.Array){
                semanticErrors.add(super.errorsRep.invalidCategoryAccessError(
                        row.getName(), 
                        row.getCategory().getName(), 
                        Category.Array.getName(), 
                        identifier.getPosition()
                ));
            } else if(!row.getType().equals(typeAssign.getName())){
                semanticErrors.add(super.errorsRep.incorrectTypeError(
                        typeAssign.getName(), 
                        row.getType(), 
                        typeAssign.getPosition())
                );
            }
        }
        
        return new ReturnCase(false);
    }

    @Override
    public PassActTree getActivationNodeTree(Index index) {
        return actTreeGen.generatePass(indexExp, valToAssign, index);
    }
    
}

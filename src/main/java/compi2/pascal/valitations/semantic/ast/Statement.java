
package compi2.pascal.valitations.semantic.ast;

import compi2.pascal.valitations.analysis.actree.PassActTree;
import compi2.pascal.valitations.semantic.SemanticRestrictions;
import compi2.pascal.valitations.analysis.symbolt.SymbolTable;
import compi2.pascal.valitations.analysis.typet.TypeTable;
import compi2.pascal.valitations.analysis.typet.convert.TConvertidor;
import compi2.pascal.valitations.analyzator.RefAnalyzator;
import compi2.pascal.valitations.analysis.actree.ActivableInTree;
import compi2.pascal.valitations.semantic.ReturnCase;
import compi2.pascal.valitations.util.ErrorsRep;
import compi2.pascal.valitations.util.Index;
import compi2.pascal.valitations.util.Position;
import java.util.List;
import lombok.Getter;

/**
 *
 * @author blue-dragon
 */
@Getter
public abstract class Statement implements ActivableInTree{
    
    protected Position initPos;
    
    public abstract ReturnCase validate(SymbolTable symbolTable, TypeTable typeTable, 
            List<String> semanticErrors, SemanticRestrictions restrictions);
    
    protected ErrorsRep errorsRep;
    protected TConvertidor tConvert;
    protected RefAnalyzator refAnalyzator;
    
    
    public Statement(Position initPos){
        this.initPos = initPos;
        this.errorsRep = new ErrorsRep();
        tConvert = new TConvertidor();
        refAnalyzator = new RefAnalyzator();
    }
    
    @Override
    public abstract PassActTree getActivationNodeTree(Index index);
    
}

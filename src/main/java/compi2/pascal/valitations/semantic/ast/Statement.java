
package compi2.pascal.valitations.semantic.ast;

import compi2.pascal.valitations.analysis.symbolt.SymbolTable;
import compi2.pascal.valitations.analysis.typet.TypeTable;
import compi2.pascal.valitations.analysis.typet.convert.TConvertidor;
import compi2.pascal.valitations.analyzator.RefAnalyzator;
import compi2.pascal.valitations.util.ErrorsRep;
import java.util.List;

/**
 *
 * @author blue-dragon
 */
public abstract class Statement {
    public abstract void validate(SymbolTable symbolTable, TypeTable typeTable, 
            List<String> semanticErrors, SemanticRestrictions restrictions);
    protected ErrorsRep errorsRep;
    protected TConvertidor tConvert;
    protected RefAnalyzator refAnalyzator;
    
    
    public Statement(){
        this.errorsRep = new ErrorsRep();
        tConvert = new TConvertidor();
        refAnalyzator = new RefAnalyzator();
    }
    
}

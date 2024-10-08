
package compi2.pascal.valitations.analyzator;

import compi2.pascal.valitations.semantic.obj.ArrayDef;
import compi2.pascal.valitations.semantic.obj.DefAst;
import compi2.pascal.valitations.semantic.obj.Label;
import compi2.pascal.valitations.semantic.obj.Range;
import compi2.pascal.valitations.semantic.obj.RecordDef;
import compi2.pascal.valitations.semantic.obj.SingleDef;
import compi2.pascal.valitations.semantic.obj.SubRangeDef;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author blue-dragon
 */
public abstract class Generator {
    
    public List<DefAst> userDef(List<Label> ids, Label type){
        List<DefAst> definitions = new LinkedList<>();
        for (Label id : ids) {
            definitions.add(new SingleDef(id, type));
        }
        return definitions; 
    }
    
    public List<DefAst> rangeDef(List<Label> ids, Range range){
        List<DefAst> definitions = new LinkedList<>();
        for (Label id : ids) {
            definitions.add(new SubRangeDef(id, range));
        }
        return definitions;
    }
    
    public List<DefAst> arrayDef(List<Label> ids, Label type, Range range){
        List<DefAst> definitions = new LinkedList<>();
        for (Label id : ids) {
            definitions.add(new ArrayDef(id, type, range));
        }
        return definitions; 
    }
    
    public List<DefAst> recordDef(List<Label> ids, List<DefAst> internalDec){
        List<DefAst> definitions = new LinkedList<>();
        for (Label id : ids) {
            definitions.add(new RecordDef(id, internalDec));
        }
        return definitions; 
    };
    
}

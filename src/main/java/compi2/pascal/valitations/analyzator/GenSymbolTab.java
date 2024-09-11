
package compi2.pascal.valitations.analyzator;

import compi2.pascal.valitations.analysis.symbolt.RowST;
import compi2.pascal.valitations.analysis.symbolt.SymbolTable;
import compi2.pascal.valitations.analysis.typet.TypeTable;
import compi2.pascal.valitations.semantic.module.ModuleDec;
import compi2.pascal.valitations.semantic.obj.DefAst;
import java.util.List;

/**
 *
 * @author blue-dragon
 */
public class GenSymbolTab extends Generator{
    public void addData(SymbolTable symbolTable, TypeTable typeTable, List<? extends DefAst> listDef, 
            List<String> semanticErrors){
        if(listDef != null && !listDef.isEmpty()){
            for (DefAst def : listDef) {
                try {
                    RowST rowST = def.generateRowST(symbolTable, typeTable, semanticErrors);
                    if(rowST != null){
                        symbolTable.put(rowST.getName(), rowST);
                    }
                    if(def instanceof ModuleDec modDec){
                        modDec.validate(typeTable, semanticErrors);
                    }
                } catch (NullPointerException e) {
                    System.out.println(e);
                }
            }
        }
    }
    
    public SymbolTable generateInternalTable(SymbolTable symbolTable, TypeTable typeTable,
            List<? extends DefAst> listDef, List<String> semanticErrors){
        SymbolTable internalTable = new SymbolTable();
        internalTable.setFather(symbolTable);
        this.addData(internalTable, typeTable, listDef, semanticErrors);
        return internalTable;
    }
    
    public SymbolTable generateInternalTable(){
        SymbolTable internalTable = new SymbolTable();
        
        return internalTable;
    }
    
    
}

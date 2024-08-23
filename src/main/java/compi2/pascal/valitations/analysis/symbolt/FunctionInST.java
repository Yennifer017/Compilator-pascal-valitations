/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compi2.pascal.valitations.analysis.symbolt;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class FunctionInST extends RowST{
    private SymbolTable symbolTable;
    
    public void referenceFather(SymbolTable father){
        this.symbolTable.setFather(father);
    }
    
}

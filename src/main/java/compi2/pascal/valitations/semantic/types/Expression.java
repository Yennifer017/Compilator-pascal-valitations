/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compi2.pascal.valitations.semantic.types;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class Expression {
    
    private Expression rightExp;
    private Expression leftExp;
    
    public boolean isLeaf(){
        return this.rightExp == null && this.leftExp == null;
    }
}

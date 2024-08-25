/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compi2.pascal.valitations.semantic.obj;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class ArrayDef extends DefAst{
    
    private Range range;
    
    public ArrayDef(Label name, Label base, Range range){
        super.name = name;
        super.base = base;
        this.range = range;
    }
}

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
public class ArrayDef extends TypeDefAst{
    
    private Range range;
    
    public ArrayDef(Label newTipe, Label baseType, Range range){
        super.newType = newType;
        super.baseType = baseType;
        this.range = range;
    }
}

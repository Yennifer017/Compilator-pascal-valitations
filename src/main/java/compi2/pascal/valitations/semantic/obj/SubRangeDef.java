/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compi2.pascal.valitations.semantic.obj;

import compi2.pascal.valitations.analysis.typet.PrimitiveType;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class SubRangeDef extends TypeDefAst{
    private Range range;

    public SubRangeDef(Label newType, Range range) {
        this.range = range;
        super.newType = newType;
        super.baseType = new Label(PrimitiveType.IntegerPT.getName(), null);
    }
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compi2.pascal.valitations.semantic.expr;

import compi2.pascal.valitations.semantic.obj.Label;
import compi2.pascal.valitations.util.Position;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class VectorUse extends Expression{
    private String idVector;
    private Expression expression;

    public VectorUse(String idVector, Expression expression, Position pos) {
        this.idVector = idVector;
        this.expression = expression;
        super.pos = pos;
    }
    
}

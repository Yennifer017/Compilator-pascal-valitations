/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compi2.pascal.valitations.semantic.expr;

import compi2.pascal.valitations.analysis.typet.PrimitiveType;
import compi2.pascal.valitations.util.Position;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class Operation extends Expression{
    private DefiniteOperation operation;
    private Expression leftExp;
    private Expression rightExp;

    public Operation(DefiniteOperation operation, Expression leftExp, Expression rightExp, Position pos) {
        this.operation = operation;
        this.leftExp = leftExp;
        this.rightExp = rightExp;
        super.pos = pos;
    }
    
}

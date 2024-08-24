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
    private String accessId;
    private Expression leftExp;
    private Expression rightExp;

    public Operation(DefiniteOperation operation, Expression leftExp, Expression rightExp, Position pos) {
        this.operation = operation;
        this.leftExp = leftExp;
        this.rightExp = rightExp;
        super.pos = pos;
    }
    
    public Operation(PrimitiveType type, Position pos){
        super.type = type;
        super.pos = pos;
    }

    public Operation(String accessId, Position pos) {
        this.accessId = accessId;
        super.pos = pos;
    }
    
    @Override
    public boolean isLeaf() {
        return this.leftExp == null && this.rightExp == null;
    }

    @Override
    public boolean isComplex() {
        return this.leftExp != null || this.rightExp != null;
    }
    
    
}

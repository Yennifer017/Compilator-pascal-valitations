/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compi2.pascal.valitations.semantic.expr;

import compi2.pascal.valitations.analysis.typet.PrimitiveType;
import compi2.pascal.valitations.util.Position;

/**
 *
 * @author blue-dragon
 */
public class SingleExp extends Expression{
    private String accessId;
    
    public SingleExp(PrimitiveType type, Position pos){
        super.type = type;
        super.pos = pos;
    }
    
    public SingleExp(String accessId, Position pos) {
        this.accessId = accessId;
        super.pos = pos;
    }
}

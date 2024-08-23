/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compi2.pascal.valitations.semantic.obj;

import compi2.pascal.valitations.util.Position;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class VarType {
    private int idPrim;
    private String name;
    Position position;

    public VarType(int idPrim, Position position) {
        this.idPrim = idPrim;
        this.position = position;
    }

    public VarType(String name, Position position) {
        this.name = name;
        this.position = position;
    }
    
}

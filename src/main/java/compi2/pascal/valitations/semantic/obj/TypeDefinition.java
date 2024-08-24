/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compi2.pascal.valitations.semantic.obj;

import compi2.pascal.valitations.analysis.typet.UserType;
import compi2.pascal.valitations.util.Position;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class TypeDefinition {
    private UserType type;
    private Position position;
    private VarType fatherType;

    public TypeDefinition(UserType type, Position position, VarType fatherType) {
        this.type = type;
        this.position = position;
        this.fatherType = fatherType;
    }
 
}

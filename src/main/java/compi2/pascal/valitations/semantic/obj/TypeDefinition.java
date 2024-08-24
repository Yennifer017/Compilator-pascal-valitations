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
public class TypeDefinition {
    Label newType;
    Label baseType;

    public TypeDefinition(Label newType, Label baseType) {
        this.newType = newType;
        this.baseType = baseType;
    }
 
}

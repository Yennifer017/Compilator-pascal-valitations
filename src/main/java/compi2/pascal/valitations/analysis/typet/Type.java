/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compi2.pascal.valitations.analysis.typet;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class Type {
    
    protected String name;
    protected int dimention;
    
    public Type(String name, int dimention) {
        this.name = name;
        this.dimention = dimention;
    }
}

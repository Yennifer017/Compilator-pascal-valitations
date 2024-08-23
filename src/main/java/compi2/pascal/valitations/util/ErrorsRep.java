/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compi2.pascal.valitations.util;

/**
 *
 * @author blue-dragon
 */
public class ErrorsRep {
    
    public String typeError(String type, Position position){
        return "El tipo " + type + " ya se ha declarado anteriormente" + report(position);
                
    }
    
    private String report(Position position){
        return "-- Linea: " + position.getLine() + ", columna: " + position.getCol();
    }
}

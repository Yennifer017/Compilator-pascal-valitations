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
    
    public String typeRepeatedError(String type, Position position){
        return "El tipo " + type + " ya se ha declarado anteriormente" + report(position);
                
    }
    
    public String typeUndefiniteError(String type, Position position){
        return "El tipo " + type + " no se ha definido aun" + report(position);
                
    }
    
    private String report(Position position){
        return "-- Linea: " + position.getLine() + ", columna: " + position.getCol();
    }
}

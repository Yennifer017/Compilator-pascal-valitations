
package compi2.pascal.valitations.util;

/**
 *
 * @author blue-dragon
 */
public class ErrorsRep {
    
    public String repeatedTypeError(String type, Position position){
        return "El tipo " + type + " ya se ha declarado anteriormente" + report(position);
                
    }
    
    public String undefiniteTypeError(String type, Position position){
        return "El tipo " + type + " no se ha definido aun" + report(position);
                
    }
    
    public String incorrectTypeError(String currentType, String correctType, Position position){
        return "El tipo " + currentType + " no es valido, se esperaba: " + correctType + report(position);
    }
    
    public String incorrectTypeError(String currentType, Position position){
        return "El tipo: " + currentType + " no es compatible con la operacion" + report(position);
    }
    
    public String inesperateTypeError(String esperateType, Position position){
        return "Se esperaba el tipo " + esperateType + report(position);
    }
    
    public String ilegalUseError(String variable, Position pos){
        return "Expresion invalida, variable/funcion/record/arreglo no permitido <" 
                + variable + ">" + report(pos);
    }
    
    public String negativeIndexError(String variable, Position pos){
        return "No se puede inicializar el arreglo " + variable + " con indices negativos" + report(pos);
    }
    
    public String undefiniteVarUseError(){
        return null;
    }
    
    public String undefiniteFunctionError(){
        return null;
    }
    
    private String report(Position position){
        return "-- Linea: " + position.getLine() + ", columna: " + position.getCol();
    }
    
}

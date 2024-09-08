
package compi2.pascal.valitations.util;

/**
 *
 * @author blue-dragon
 */
public class ErrorsRep {
    
    public String repeatedTypeError(String type, Position position){
        return "El tipo " + type + " ya se ha declarado anteriormente" + report(position);
                
    }
    
    public String repeatedDeclarationError(String name, Position position){
        return "Se esta redefiniendo la variable/constante " + name + report(position);
    }
    
    public String undefiniteTypeError(String type, Position position){
        return "El tipo " + type + " no se ha definido aun" + report(position);
                
    }
    
    /**
     * @param currentType
     * @param correctType
     * @param position
     * @return error que muestra que un tipo no es adecuado para una situacion
     */
    public String incorrectTypeError(String currentType, String correctType, Position position){
        return "El tipo " + currentType + " no es valido, se esperaba: " + correctType + report(position);
    }
    
    /**
     * @param currentType
     * @param position
     * @return Error que muestra que un tipo no es compatible con una operacion
     */
    public String incorrectTypeError(String currentType, Position position){
        return "El tipo: " + currentType + " no es compatible con la operacion" + report(position);
    }
    
    public String incorrectTypeError(String type1, String type2, String operation, Position pos){
        return "Los tipos " + type1 + " y " + type2 + " no se pueden operar con " 
                + operation + report(pos);
    }
    
    public String invalidCategoryAccessError(String name, String category, Position pos){
        return "No se puede acceder a " + name + " (" + category + ")" + report(pos);
    }
    
    public String invalidCategoryAccessError(String name, String category, 
            String esperateCategory, Position pos){
        return "No se puede acceder a " + name + " (" + category + ") como "
                + esperateCategory + report(pos);
    }
    
    public String inesperateTypeError(String esperateType, Position position){
        return "Se esperaba el tipo " + esperateType + report(position);
    }
    
    public String ilegalUseError(String variable, Position pos){
        return "Expresion invalida, variable/funcion/record/arreglo no permitido <" 
                + variable + ">" + report(pos);
    }
    
    public String ilegalAssignation(String variable, String categoryST, Position pos){
        return "No se puede asignar a " + variable + " (" + categoryST + ") " + report(pos);
    }
    
    public String negativeIndexError(String variable, Position pos){
        return "No se puede inicializar el arreglo " + variable + " con indices negativos" + report(pos);
    }
    
    public String undefiniteVarUseError(String variable, Position pos){
        return "La variable " + variable + " no se ha declarado " + report(pos);
    }
    
    public String undefiniteVarRecordError(String variable, Position pos){
        return "El acceso a " + variable + " no se pueden completar" + report(pos);
    }
    
    public String undefiniteFunctionError(){
        return null;
    }
    
    private String report(Position position){
        return "-- Linea: " + position.getLine() + ", columna: " + position.getCol();
    }
    
    public String incorrectVarTypeError(String varName, String esperatedType, Position pos){
        return "La variaable " + varName + " no es del tipo " + esperatedType + report(pos);
    }
    
    public String ilegalStmt(String nameStmt, Position pos){
        return "No se esperaba " + nameStmt + report(pos);
    }   
    
}

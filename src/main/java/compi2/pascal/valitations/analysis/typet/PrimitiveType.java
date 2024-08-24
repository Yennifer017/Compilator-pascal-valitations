
package compi2.pascal.valitations.analysis.typet;

/**
 *
 * @author blue-dragon
 */
public enum PrimitiveType {
    IntegerPT("integer", 2),
    RealPT("real", 4),
    BooleanPT("boolean", 0),
    CharPT("char", 1),
    StringPT("string", 5),
    LongintPT("longint", 3)
    ;
    
    private String name;
    private Type type;
    private int id;
    
    private PrimitiveType(String name, int id){
        this.name = name;
        this.type = new Type(name, 1);
        this.id = id;
    }
    
    public String getName(){
        return this.name;
    }
    
    public Type getType(){
        return this.type;
    }
    
}   

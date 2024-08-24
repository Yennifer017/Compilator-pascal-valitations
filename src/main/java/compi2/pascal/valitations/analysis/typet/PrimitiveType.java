
package compi2.pascal.valitations.analysis.typet;

/**
 *
 * @author blue-dragon
 */
public enum PrimitiveType {
    IntegerPT("integer"),
    RealPT("real"),
    BooleanPT("boolean"),
    CharPT("char"),
    StringPT("string");
    
    private String name;
    private Type type;
    
    private PrimitiveType(String name){
        this.name = name;
        this.type = new Type(name, 1);
    }
    
    public String getName(){
        return this.name;
    }
    
    public Type getType(){
        return this.type;
    }
    
}   

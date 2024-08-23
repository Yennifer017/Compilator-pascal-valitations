/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package compi2.pascal.valitations.analysis.typet;

/**
 *
 * @author blue-dragon
 */
public enum PrimitiveType {
    IntegerPT(0, "integer"),
    RealPT(1, "real"),
    BooleanPT(2, "boolean"),
    CharPT(3, "char"),
    String(4, "string");
    
    private int idType;
    private String name;
    private Type type;
    private PrimitiveType(int idType, String name){
        this.idType = idType;
        this.name = name;
        this.type = new Type(1);
    }
    
    public int getIdType(){
        return this.idType;
    }
    
    public String getName(){
        return this.name;
    }
    
    public Type getType(){
        return this.type;
    }
    
}

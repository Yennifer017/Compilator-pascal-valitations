/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compi2.pascal.valitations.analysis.typet;

/**
 *
 * @author blue-dragon
 */
public class RecordType extends Type {
    private TypeTable typeTable;
    
    public RecordType(String name, int dimention) {
        super(name, dimention);
        typeTable = new TypeTable(false);
    }
    
    public void setSuperTypeTab(TypeTable father){
        this.typeTable.setFather(father);
    }
    
    public TypeTable getTypeTable(){
        return this.typeTable;
    }
    
}

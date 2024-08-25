/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compi2.pascal.valitations.semantic.obj;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class RecordDef extends TypeDefAst{
    List<TypeDefAst> internalTypes;
    
    public RecordDef(Label newType, List<TypeDefAst> internalTypes){
        super.newType = newType;
        super.baseType = null;
        this.internalTypes = internalTypes;
    }
}

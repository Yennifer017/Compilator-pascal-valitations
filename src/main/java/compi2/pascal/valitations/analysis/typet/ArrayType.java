
package compi2.pascal.valitations.analysis.typet;

import compi2.pascal.valitations.exceptions.SemanticException;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class ArrayType extends Type{
    private String typeBase;
    private Limits limits;
    
    public ArrayType(String name, String typeBase, Limits limits) throws SemanticException {
        super(name, limits.calculateDimension());
        this.typeBase = typeBase;
        this.limits = limits;
        if(limits.containsNegative()){
            throw new SemanticException("No se puede iniciar un arreglo");
        }
    }
    
}

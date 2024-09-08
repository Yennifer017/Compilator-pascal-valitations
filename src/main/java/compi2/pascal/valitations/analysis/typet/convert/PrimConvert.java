package compi2.pascal.valitations.analysis.typet.convert;

import compi2.pascal.valitations.analysis.typet.PrimitiveType;
import compi2.pascal.valitations.util.ErrorsRep;
import java.util.List;

/**
 *
 * @author blue-dragon
 */
public class PrimConvert {
    
    private ErrorsRep errorsRep;
    protected PrimConvert(ErrorsRep errorsRep){
        this.errorsRep = errorsRep;
    }

    public PrimitiveType mayorType(PrimitiveType leftType, PrimitiveType rightType) {
        if (leftType.getId() > rightType.getId()) {
            return leftType;
        } else {
            return rightType;
        }
    }

    public void penalize(PrimFollowType primT, PrimitiveType penalizeType, List<String> semanticErrors) {
        if (primT.getPrimitiveType() == penalizeType) {
            semanticErrors.add(errorsRep.incorrectTypeError(
                    primT.primitiveType.getName(), 
                    primT.getPosition())
            );
        }
    }
}


package compi2.pascal.valitations.analysis.typet.convert;

import compi2.pascal.valitations.analysis.typet.PrimitiveType;
import compi2.pascal.valitations.exceptions.ConvPrimitiveException;
import compi2.pascal.valitations.semantic.expr.DefiniteOperation;
import static compi2.pascal.valitations.semantic.expr.DefiniteOperation.Not;
import compi2.pascal.valitations.semantic.obj.Label;
import java.util.List;

/**
 *
 * @author blue-dragon
 */
public class TConvertidor {
    private PrimConvert primConvert;
    
    public TConvertidor(){
        primConvert = new PrimConvert();
    }
    
    /**
     * trata de convertir los tipos a uno primitivo
     * @param operation
     * @param leftType
     * @param rightType
     * @param semanticErrors
     * @return 
     * @throws compi2.pascal.valitations.exceptions.ConvPrimitiveException 
     */
    public String simpleConvert(DefiniteOperation operation, Label leftType, 
            Label rightType, List<String> semanticErrors) throws ConvPrimitiveException{
        PrimFollowType left = new PrimFollowType(
                convertPrimitive(leftType.getName()),
                leftType.getPosition());
        PrimFollowType right = new PrimFollowType(
                convertPrimitive(rightType.getName()),
                rightType.getPosition());
        
        return implicitConvert(operation, left, right, semanticErrors)
                .getName();
    }
    
    public String simpleConvert(DefiniteOperation unaryOperation, Label type, 
            List<String> semanticErrors) throws ConvPrimitiveException{
        PrimFollowType typePrim = new PrimFollowType(
                convertPrimitive(type.getName()),
                type.getPosition());
        
        switch (unaryOperation) {
            case Not:
                primConvert.penalize(typePrim, PrimitiveType.StringPT, semanticErrors);
                return PrimitiveType.BooleanPT.getName();
            case Addition, Substraction:
                primConvert.penalize(typePrim, PrimitiveType.StringPT, semanticErrors);
                return type.getName();
            default:
                throw new AssertionError();
        }
    }
    
    public boolean isNumericIntegerType(String type){
        try {
            PrimitiveType primType = convertPrimitive(type);
            return primType.isIntegerNumeric();
        } catch (ConvPrimitiveException ex) {
            return false;
        }
    }
    
    
    private PrimitiveType convertPrimitive(String type) throws ConvPrimitiveException{
        if (type.equals(PrimitiveType.IntegerPT.getName())) {
            return PrimitiveType.IntegerPT;
        } else if (type.equals(PrimitiveType.RealPT.getName())) {
            return PrimitiveType.RealPT;
        } else if (type.equals(PrimitiveType.BooleanPT.getName())) {
            return PrimitiveType.BooleanPT;
        } else if (type.equals(PrimitiveType.CharPT.getName())) {
            return PrimitiveType.CharPT;
        } else if (type.equals(PrimitiveType.StringPT.getName())) {
            return PrimitiveType.StringPT;
        } else if (type.equals(PrimitiveType.LongintPT.getName())) {
            return PrimitiveType.LongintPT;
        } else {
            throw new ConvPrimitiveException();
        }
    }
    
    
    private PrimitiveType implicitConvert(DefiniteOperation operation, 
            PrimFollowType leftType, PrimFollowType rightType, List<String> semanticErrors){
        switch (operation) {
            case Addition -> {
                return primConvert.mayorType(leftType.getPrimitiveType(), rightType.getPrimitiveType());
            }
            case Substraction, Multiplication -> {
                primConvert.penalize(leftType, PrimitiveType.StringPT, semanticErrors);
                primConvert.penalize(rightType, PrimitiveType.StringPT, semanticErrors);
                return  primConvert.mayorType(leftType.getPrimitiveType(), rightType.getPrimitiveType());
            }
            case Division -> {
                primConvert.penalize(leftType, PrimitiveType.StringPT, semanticErrors);
                primConvert.penalize(rightType, PrimitiveType.StringPT, semanticErrors);
                return PrimitiveType.RealPT;
            }
            case IntDivision, Module -> {
                primConvert.penalize(leftType, PrimitiveType.StringPT, semanticErrors);
                primConvert.penalize(rightType, PrimitiveType.StringPT, semanticErrors);
                primConvert.penalize(leftType, PrimitiveType.RealPT, semanticErrors);
                primConvert.penalize(rightType, PrimitiveType.RealPT, semanticErrors);
                return PrimitiveType.IntegerPT;
            }
            case EqualsTo, DifferentTo -> {
                if(rightType.getPrimitiveType() == PrimitiveType.StringPT 
                        && leftType.getPrimitiveType() == PrimitiveType.StringPT){
                    return PrimitiveType.BooleanPT;
                } else {
                    primConvert.penalize(leftType, PrimitiveType.StringPT, semanticErrors);
                    primConvert.penalize(rightType, PrimitiveType.StringPT, semanticErrors);
                    return PrimitiveType.BooleanPT;
                }
            }
            case GraterEq, GraterThan, LessEq, LessThan, And, AndThen, Or, OrElse -> {
                primConvert.penalize(leftType, PrimitiveType.StringPT, semanticErrors);
                primConvert.penalize(rightType, PrimitiveType.StringPT, semanticErrors);
                return PrimitiveType.BooleanPT;
            }
            default -> throw new AssertionError();
        }
    }
    
    
}

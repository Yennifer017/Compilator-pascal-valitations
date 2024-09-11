
package compi2.pascal.valitations.semantic.ast;

import compi2.pascal.valitations.analysis.actree.NodeAct;
import compi2.pascal.valitations.analysis.actree.PassActTree;
import compi2.pascal.valitations.semantic.SemanticRestrictions;
import compi2.pascal.valitations.analysis.symbolt.SymbolTable;
import compi2.pascal.valitations.analysis.typet.PrimitiveType;
import compi2.pascal.valitations.analysis.typet.TypeTable;
import compi2.pascal.valitations.semantic.ReturnCase;
import compi2.pascal.valitations.semantic.expr.Expression;
import compi2.pascal.valitations.semantic.obj.Label;
import compi2.pascal.valitations.util.Index;
import compi2.pascal.valitations.util.Position;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class CaseAst extends Statement{
    private Expression expression;
    private List<SimpleCase> cases;
    private ElseAst elseAst;

    public CaseAst(Expression expression, List<SimpleCase> cases, 
            ElseAst elseAst, Position initPos) {
        super(initPos);
        this.expression = expression;
        this.cases = cases;
        this.elseAst = elseAst;
    }

    @Override
    public ReturnCase validate(SymbolTable symbolTable, TypeTable typeTable, 
            List<String> semanticErrors, SemanticRestrictions restrictions) {
        Label typeCase = expression.validateComplexData(symbolTable, typeTable, semanticErrors);
        if(!typeCase.getName().equals(PrimitiveType.IntegerPT.getName())
                && !typeCase.getName().equals(PrimitiveType.CharPT.getName())
                && !typeCase.getName().equals(PrimitiveType.BooleanPT.getName())
                ){
            semanticErrors.add(errorsRep.incorrectTypeError(
                    typeCase.getName(), 
                    "[" + PrimitiveType.IntegerPT.getName() + ", " 
                            + PrimitiveType.CharPT.getName() + ", " 
                            + PrimitiveType.BooleanPT.getName() 
                            + "]",
                    typeCase.getPosition())
            );
        }
        ReturnCase returnCase = new ReturnCase(true);
        if(cases != null && !cases.isEmpty()){
            for (SimpleCase simpleCase : cases) {
                ReturnCase currentCase = simpleCase.validate(
                        symbolTable, typeTable, semanticErrors, restrictions, typeCase.getName()
                );
                if(!currentCase.isAllScenaries()){
                    returnCase.setAllScenaries(false);
                }
            }
        }
        
        if(elseAst != null){
            ReturnCase elseRC = elseAst.validate(symbolTable, typeTable, semanticErrors, restrictions);
            if(returnCase.isAllScenaries() && !elseRC.isAllScenaries()){
                returnCase.setAllScenaries(false);
            }
        } else {
            returnCase.setAllScenaries(false);
        }
        
        return returnCase;
    }

    @Override
    public PassActTree getActivationNodeTree(Index index) {
        NodeAct finalNode = new NodeAct(index.getNumber());
        index.increment();
        List<NodeAct> finalNodeList = new ArrayList<>();
        finalNodeList.add(finalNode);
        
        List<NodeAct> listInitNodes = new ArrayList<>();
        PassActTree passExp = expression.getActivationNodeTree(index);
        
        //validate cases
        if (cases != null && !cases.isEmpty()) {
            for (SimpleCase simpleCase : cases) {
                PassActTree currentPass = simpleCase.getActivationNodeTree(index);
                if (currentPass != null) {
                    currentPass.getFinalNode().setActivations(finalNodeList);
                    listInitNodes.addAll(currentPass.getInitNodes());
                }
            }
        }
        
        //validate else
        PassActTree elsePass = elseAst.getActivationNodeTree(index);
        if(elsePass != null){
            elsePass.getFinalNode().setActivations(finalNodeList);
            listInitNodes.addAll(elsePass.getInitNodes());
        }
        
        //maquetar
        if(passExp == null){
            return listInitNodes.isEmpty()
                    ? null : new PassActTree(listInitNodes, finalNode);
        } else {
            passExp.getInitNodes().get(0).setActivations(listInitNodes);
            passExp.setFinalNode(finalNode);
            return passExp;
        }
    }
    
}

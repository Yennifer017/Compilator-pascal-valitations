
package compi2.pascal.valitations.semantic.ast;

import compi2.pascal.valitations.analysis.actree.NodeAct;
import compi2.pascal.valitations.analysis.actree.PassActTree;
import compi2.pascal.valitations.semantic.SemanticRestrictions;
import compi2.pascal.valitations.analysis.symbolt.SymbolTable;
import compi2.pascal.valitations.analysis.typet.TypeTable;
import compi2.pascal.valitations.semantic.ReturnCase;
import compi2.pascal.valitations.semantic.expr.Expression;
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
public class IfAst extends ControlStruct{
    private Expression condition;
    private List<IfAst> elifs;
    private ElseAst elseStmt;

    public IfAst(Expression condition, List<Statement> statements, Position initPos) {
        super(initPos);
        this.condition = condition;
        super.internalStmts = statements;
    }

    public IfAst(Expression condition, List<Statement> statements, 
            List<IfAst> elifs, ElseAst elseStmt, Position initPos) {
        super(initPos);
        this.condition = condition;
        this.elifs = elifs;
        this.elseStmt = elseStmt;
        super.internalStmts = statements;
    }

    @Override
    public ReturnCase validate(SymbolTable symbolTable, TypeTable typeTable, 
            List<String> semanticErrors, SemanticRestrictions restrictions) {
        super.validateCondition(condition, symbolTable, typeTable, semanticErrors);
        
        ReturnCase internalRC = super.validateInternalStmts(symbolTable, typeTable, semanticErrors, 
                restrictions
        );
        
        if(elifs != null && !elifs.isEmpty()){
            for (IfAst ifAst : elifs) {
                ReturnCase pathRC = ifAst.validate(symbolTable, typeTable, semanticErrors, restrictions);
                if(internalRC.isAllScenaries() && !pathRC.isAllScenaries()){
                    internalRC.setAllScenaries(false);
                }
            }
        }
        
        if(elseStmt != null){
            ReturnCase elseRC = elseStmt.validate(symbolTable, typeTable, semanticErrors, restrictions);
            if(internalRC.isAllScenaries() && !elseRC.isAllScenaries()){
                internalRC.setAllScenaries(false);
            }
        } else {
            internalRC.setAllScenaries(false);
        }
        return internalRC;
    }

    @Override
    public PassActTree getActivationNodeTree(Index index) {
        NodeAct finalNode = new NodeAct(index.getNumber());
        index.increment();
        List<NodeAct> finalNodeList = new ArrayList<>();
        finalNodeList.add(finalNode);
        
        List<NodeAct> listInitNodes = new ArrayList<>();
        
        //arbol de activaciones del if
        PassActTree passIfActTree = actTreeGen.generatePass(
                condition.getActivationNodeTree(index), 
                actTreeGen.generatePass(internalStmts, index),
                index
        );
        if(passIfActTree != null){
            passIfActTree.getFinalNode().setActivations(finalNodeList);
            listInitNodes.addAll(passIfActTree.getInitNodes());
        }
        //activaciones en los elifs
        if(elifs != null && !elifs.isEmpty()){
            for (IfAst ifAst : elifs) {
                PassActTree currentPass = ifAst.getActivationNodeTree(index);
                if (currentPass != null) {
                    currentPass.getFinalNode().setActivations(finalNodeList);
                    listInitNodes.addAll(currentPass.getInitNodes());
                }
            }
        }
        //activaciones en el else
        if(elseStmt != null){
            PassActTree elsePass = elseStmt.getActivationNodeTree(index);
            if(elsePass != null){
                elsePass.getFinalNode().setActivations(finalNodeList);
                listInitNodes.addAll(elsePass.getInitNodes());
            }
        }
        
        return listInitNodes.isEmpty() ? 
                null : new PassActTree(listInitNodes, finalNode);
    }
    
}

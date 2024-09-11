
package compi2.pascal.valitations.analysis.actree;

import compi2.pascal.valitations.analysis.symbolt.FunctionST;
import compi2.pascal.valitations.semantic.ast.Statement;
import compi2.pascal.valitations.semantic.expr.Expression;
import compi2.pascal.valitations.util.Index;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author blue-dragon
 */
public class ActTreeGen {
    
    public PassActTree generatePass(Expression leftExp, Expression rightExp, Index index){
        PassActTree leftAct = leftExp.getActivationNodeTree(index);
        PassActTree rightAct = rightExp.getActivationNodeTree(index);
        if(leftAct == null && rightAct == null){
            return null;
        } if(leftAct == null){
            return rightAct;
        } else if (rightAct == null){
            return leftAct;
        } else {
            NodeAct finalNode = rightAct.getFinalNode();
            leftAct.getFinalNode()
                    .setActivations(rightAct.getInitNodes());
            leftAct.setFinalNode(finalNode);
            return leftAct;
        }
    }
    
    public PassActTree generatePass(PassActTree left, Expression rightExp, Index index){
        PassActTree rightAct = rightExp.getActivationNodeTree(index);
        if(left == null && rightAct == null){
            return null;
        } else if (rightAct == null){
            return left;
        } else if(left == null){
            return null;
        } else {
            NodeAct finalNode = rightAct.getFinalNode();
            left.getFinalNode()
                    .setActivations(rightAct.getInitNodes());
            left.setFinalNode(finalNode);
            return left;
        }
    }
    
    public PassActTree generatePass(PassActTree left, PassActTree right, Index index){
        if(left == null && right == null){
            return null;
        } else if(left == null){
            return right;
        } else if (right == null){
            return left;
        } 
        NodeAct finalNode = right.getFinalNode();
        left.getFinalNode()
                .setActivations(right.getInitNodes());
        left.setFinalNode(finalNode);
        return left;
    }
    
    public PassActTree generatePass(List<Expression> expressions, Index index, FunctionST currentFun){
        NodeAct nodeAct = new NodeAct(index.getNumber(), currentFun);
        List<NodeAct> list =  new ArrayList<>();
        list.add(nodeAct);
        index.increment();
        PassActTree superPass = new PassActTree(list, nodeAct);
        if(expressions != null && !expressions.isEmpty()){
            for (Expression param : expressions) {
                superPass = this.generatePass(superPass, param, index);
            }
        }
        return superPass;
    }
    
    
    public PassActTree generatePass(List<Statement> stmts, Index index){
        PassActTree first = null;
        if(stmts != null && !stmts.isEmpty()){
            for (Statement stmt : stmts) {
                if(first != null){
                    first = generatePass(first, stmt.getActivationNodeTree(index), index);
                } else {
                    first = stmt.getActivationNodeTree(index);
                }
            }
        }
        return first;
    }
}

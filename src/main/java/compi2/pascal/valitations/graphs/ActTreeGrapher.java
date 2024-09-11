
package compi2.pascal.valitations.graphs;

import compi2.pascal.valitations.analysis.actree.ActTreeGen;
import compi2.pascal.valitations.analysis.actree.NodeAct;
import compi2.pascal.valitations.analysis.actree.PassActTree;
import compi2.pascal.valitations.semantic.ast.Statement;
import compi2.pascal.valitations.semantic.module.FunctionDec;
import compi2.pascal.valitations.semantic.module.ModuleDec;
import compi2.pascal.valitations.semantic.module.ProcedureDec;
import compi2.pascal.valitations.util.Index;
import java.util.List;

/**
 *
 * @author blue-dragon
 */
public class ActTreeGrapher {
    
    private ActTreeGen actTreeGen;
    public ActTreeGrapher(){
        actTreeGen = new ActTreeGen();
    }
    
    public String generateCodeForAll(
            List<FunctionDec> functions, 
            List<ProcedureDec> procedures, 
            List<Statement> statements){
        StringBuilder builder = new StringBuilder();
        Index index = new Index();
        builder.append(getInitCode());
        builder.append(generateFunCode(functions, index));
        builder.append(generateFunCode(procedures, index));
        builder.append(generateCode("__MAIN__", statements, index));
        builder.append(getFinalCode());
        return builder.toString();
    }
    
    public String generateCode(String name, List<Statement> list, Index index){
        StringBuilder builder = new StringBuilder();
        int current = index.getNumber();
        index.increment();
        builder.append(current);
        builder.append(getLabelCode(name));
        PassActTree pass = actTreeGen.generatePass(list, index);
        if(pass != null){
            builder.append(generateCodeForPass(pass.getInitNodes(), current));
        }
        return builder.toString();
    }
    
    public String generateCodeForPass(List<NodeAct> listNode, int fatherId){
        StringBuilder builder = new StringBuilder();
        if(listNode != null && !listNode.isEmpty()){
            for (NodeAct initNode : listNode) {
                builder.append(getUnionCode(fatherId, initNode.getId()));
                builder.append(initNode.getId());
                if(initNode.isCorrectorPath()){
                    builder.append(getRomboCode());
                } else {
                    builder.append(getLabelCode(initNode.getFunction().getCompleateName()));
                }
                builder.append(generateCodeForPass(
                        initNode.getActivations(), 
                        initNode.getId())
                );
            }
        }
        return builder.toString();
    }
    
    public String generateFunCode(List<? extends ModuleDec> list, Index index){
        StringBuilder builder = new StringBuilder();
        if(!list.isEmpty()){
            for (ModuleDec moduleDec : list) {
                builder.append(generateCode(
                        moduleDec.getFunctionST().getCompleateName(), 
                        moduleDec.getStatements(), 
                        index)
                );
            }
        }
        return builder.toString();
    }
    
    
    private String getInitCode(){
        return "digraph G {";
    }
    
    private String getFinalCode(){
        return "}";
    }
    
    private String getRomboCode(){
        return """
               [shape=diamond, style=filled, fillcolor=black, label="", width=0.3, height=0.3];
               """;
    }
    
    private String getLabelCode(String label){
        return "[label=\"" + label + "\"];\n";
    }
    
    private String getUnionCode(int first, int second){
        return first + "->" + second + "\n";
    }
}

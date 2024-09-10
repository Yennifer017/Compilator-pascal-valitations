
package compi2.pascal.valitations.analysis.typet;

import compi2.pascal.valitations.graphs.TTGrapher;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class Type {
    
    protected String name;
    protected int dimention;
    
    protected TTGrapher ttGrapher;
    
    public Type(String name, int dimention) {
        this.name = name;
        this.dimention = dimention;
        ttGrapher = new TTGrapher();
    }
    
    public StringBuilder getGraphRowCode(String codeRelated){
        StringBuilder builder = getInitialGraphData();
        builder.append(ttGrapher.getDataGraphCode("Tipo primitivo"));
        builder.append(ttGrapher.getNoDataGraphCode());
        builder.append(ttGrapher.getNoDataGraphCode());
        return builder;
    }
    
    protected StringBuilder getInitialGraphData(){
        StringBuilder builder = new StringBuilder(
                ttGrapher.getDataGraphCode(name));
        builder.append(ttGrapher.getDataGraphCode(String.valueOf(dimention)));
        return builder;
    }
    
    public boolean isLinked(){
        return false;
    };
}

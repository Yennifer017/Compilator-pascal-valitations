
package compi2.pascal.valitations.analysis.symbolt;

import compi2.pascal.valitations.analysis.typet.TypeTable;
import compi2.pascal.valitations.graphs.Graphicator;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public abstract class RowST {
    protected String name;
    protected Category category;
    protected String type;
    
    protected Graphicator graphicator;

    public RowST(String name, Category category, String type) {
        this.name = name;
        this.category = category;
        this.type = type;
        graphicator = new Graphicator();
    }
    
    public abstract StringBuilder getGraphRowCode(String codeRelated);
    public StringBuilder getGraphInternalTab(){
        throw new RuntimeException();
    };
    
    public abstract boolean isLinked();
    
    protected StringBuilder getInitialGraphCodeData(){
        StringBuilder builder = new StringBuilder(
                graphicator.getDataGraphCode(name))
                .append(graphicator.getDataGraphCode(category.getName()))
                .append(graphicator.getDataGraphCode(type));
        return builder;
    }
            
}

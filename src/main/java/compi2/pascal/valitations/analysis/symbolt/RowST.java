
package compi2.pascal.valitations.analysis.symbolt;

import compi2.pascal.valitations.graphs.Index;
import compi2.pascal.valitations.graphs.STGrapher;
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
    
    protected STGrapher stGraphicator;

    public RowST(String name, Category category, String type) {
        this.name = name;
        this.category = category;
        this.type = type;
        stGraphicator = new STGrapher();
    }
    
    public abstract StringBuilder getGraphRowCode(String codeRelated);
    
    public StringBuilder getGraphInternalTab(int fatherId, Index currentIndex){
        throw new RuntimeException();
    };
    
    public abstract boolean isLinked();
    
    protected StringBuilder getInitialGraphCodeData(){
        StringBuilder builder = new StringBuilder(
                stGraphicator.getDataGraphCode(name))
                .append(stGraphicator.getDataGraphCode(category.getName()))
                .append(stGraphicator.getDataGraphCode(type));
        return builder;
    }
            
}


package compi2.pascal.valitations.analysis.typet;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class Limits {
    private int minLimit;
    private int maxLimit;

    public Limits(int minLimit, int maxLimit) {
        this.minLimit = minLimit;
        this.maxLimit = maxLimit;
    }
    
    public boolean containsNegative(){
        return minLimit < 0 || maxLimit < 0;
    }
    
    public int calculateDimension(){
        return maxLimit - minLimit;
    }
    
}

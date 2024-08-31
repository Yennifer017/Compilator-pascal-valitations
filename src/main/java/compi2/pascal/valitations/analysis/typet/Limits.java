
package compi2.pascal.valitations.analysis.typet;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class Limits {
    private int lowlimit;
    private int maxlimit;

    public Limits(int lowlimit, int maxlimit) {
        this.lowlimit = lowlimit;
        this.maxlimit = maxlimit;
    }
    
    protected boolean containsNegative(){
        return lowlimit < 0 || maxlimit < 0;
    }
}

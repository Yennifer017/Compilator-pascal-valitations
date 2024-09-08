
package compi2.pascal.valitations.semantic.ast;

import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Setter
public class SemanticRestrictions {
    private boolean allowContinue;
    private boolean allowBreak;
    private boolean needReturnVal;
    private String returnType;

    public SemanticRestrictions(boolean allowContinue, boolean allowBreak, 
            boolean needReturnVal, String returnType) {
        this.allowContinue = allowContinue;
        this.allowBreak = allowBreak;
        this.needReturnVal = needReturnVal;
        this.returnType = returnType;
    }

    public boolean allowContinue() {
        return allowContinue;
    }

    public boolean allowBreak() {
        return this.allowBreak;
    }

    public boolean needReturnVal() {
        return needReturnVal;
    }

    public String getReturnType() {
        return returnType;
    }
    
    
}

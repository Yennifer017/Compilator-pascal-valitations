
package compi2.pascal.valitations.graphs;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class CodeRecolector {
    private StringBuilder internalCode;
    private StringBuilder externalCode;

    public CodeRecolector(StringBuilder internalCode, StringBuilder externalCode) {
        this.internalCode = internalCode;
        this.externalCode = externalCode;
    }

}

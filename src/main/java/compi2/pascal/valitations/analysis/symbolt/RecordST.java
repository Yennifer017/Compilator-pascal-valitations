
package compi2.pascal.valitations.analysis.symbolt;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class RecordST extends RowST{
    private int totalElements;
    private SymbolTable internalST;

    public RecordST(String name) {
        super(name, Category.Record, null);
    }
}

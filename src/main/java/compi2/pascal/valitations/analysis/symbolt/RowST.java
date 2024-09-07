
package compi2.pascal.valitations.analysis.symbolt;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public abstract class RowST {
    private String name;
    private Category category;
    private String type;

    public RowST(String name, Category category, String type) {
        this.name = name;
        this.category = category;
        this.type = type;
    }
            
}

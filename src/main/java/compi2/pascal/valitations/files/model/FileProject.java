
package compi2.pascal.valitations.files.model;

import java.io.File;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author yenni
 */
@Getter @Setter @AllArgsConstructor
public class FileProject{
    private File file; 
    
    @Override
    public String toString(){
        return this.file.getName();
    }
}


package compi2.pascal.valitations.graphs;

import lombok.Getter;

/**
 *
 * @author blue-dragon
 */
@Getter
public class Index {
    private int number;
    
    public void increment(){
        number++;
    }
    
    public void increment(int number){
        this.number += number;
    }
}

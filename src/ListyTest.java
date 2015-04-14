import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author Piotr Górak, Maciej Knicha³ dnia 2015-03-31.
 */
public class ListyTest {
    Collection<Integer> list = new ArrayList<Integer>(Arrays.asList(3,2,1,4));
    Collection<Integer> lista2 = new ArrayList<Integer>(Arrays.asList(0,2,7));

    List<Integer> sourceList = new ArrayList<Integer>(list);
    List<Integer> destinationList = new ArrayList<Integer>(lista2);


    public void addToList(){

    }

    public void porownaj(){

        sourceList.removeAll( lista2 );
        destinationList.removeAll( list );
        list.removeAll(sourceList);


        System.out.println(sourceList);
        System.out.println( destinationList );
        System.out.println(list);
    }
}

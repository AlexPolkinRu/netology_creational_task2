import java.util.ArrayList;
import java.util.List;

public class Filter {

    private final int treshold;

    public Filter(int treshold) {
        this.treshold = treshold;
    }

    public List<Integer> filterOut(List<Integer> list) {

        Logger logger = Logger.getInstance();
        List<Integer> filteredList = new ArrayList<>();

        for (int x : list) {
            if (x > treshold) {
                logger.log("Элемент \"" + x + "\" проходит");
                filteredList.add(x);
            } else {
                logger.log("Элемент \"" + x + "\" не проходит");
            }
        }

        return filteredList;
    }

}

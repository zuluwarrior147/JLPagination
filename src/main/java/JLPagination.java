import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrii Markovych
 */
public class JLPagination implements Pagination {

    public static final int THRESHOLD = 4;
    public static final String ECLIPSES = "...";
    public static final String FIRST_ELEMENT = "1";

    @Override
    public List<String> paginate(int current, int total) {
        if (current < 1 || total < 1 || current > total) {
            throw new IllegalArgumentException();
        }
        ResultBuilder builder = new ResultBuilder();
        if (total - THRESHOLD <= 1) {
            return builder
                    .iterate(1, total)
                    .getResult();
        } else if (atTheBeginning(current)) {
            return builder
                    .iterate(1, THRESHOLD)
                    .add(ECLIPSES)
                    .add(total)
                    .getResult();
        } else if(atTheEnding(current, total)) {
            return builder
                    .add(FIRST_ELEMENT)
                    .add(ECLIPSES)
                    .iterate((total - THRESHOLD + 1), total)
                    .getResult();
        } else {
            return builder
                    .add(FIRST_ELEMENT)
                    .add(ECLIPSES)
                    .iterate(current - 1, current + 1)
                    .add(ECLIPSES)
                    .add(total)
                    .getResult();
        }
    }

    /*public List<String> badPaginate(int current, int total) {
        if (current < 1 || total < 1 || current > total) {
            throw new IllegalArgumentException();
        }
        ArrayList<String> result = new ArrayList<>();
        if (total - THRESHOLD <= 1) {
            for (int i = 1; i <= total; i++) {
                result.add("" + i);
            }
        } else if (atTheBeginning(current)) {
            for (int i = 1; i <= THRESHOLD; i++) {
                result.add("" + i);
            }
            result.add(ECLIPSES);
            result.add("" + total);
        } else if (atTheEnding(current, total)) {
            result.add(FIRST_ELEMENT);
            result.add(ECLIPSES);
            for (int i = total - THRESHOLD + 1; i <= total; i++) {
                result.add("" + i);
            }
        } else {
            result.add(FIRST_ELEMENT);
            result.add(ECLIPSES);
            for (int i = -1; i <= 1 ; i++) {
                result.add("" + (current + i));
            }
            result.add(ECLIPSES);
            result.add("" + total);
        }
        return result;
    }*/

    private boolean atTheBeginning(int current) {
        return current < THRESHOLD;
    }

    private boolean atTheEnding(int current, int total) {
        return total - current < THRESHOLD;
    }

    public static class ResultBuilder {
        private final List<String> resultInstance = new ArrayList<>();

        public ResultBuilder add(int item) {
            resultInstance.add("" + item);
            return this;
        }

        public ResultBuilder add(String item) {
            resultInstance.add(item);
            return this;
        }

        public ResultBuilder iterate(int start, int end) {
            for (int i = start; i <= end ; i++) {
                resultInstance.add("" + i);
            }
            return this;
        }

        public List<String> getResult() {
            return resultInstance;
        }

    }
}

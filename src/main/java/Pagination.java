import java.util.List;

/**
 * @author Andrii Markovych
 */
public interface Pagination {
    List<String> paginate(int current, int total);
}

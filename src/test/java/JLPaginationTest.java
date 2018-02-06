import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

/**
 * @author Andrii Markovych
 */
@RunWith(MockitoJUnitRunner.class)
public class JLPaginationTest {

    private JLPagination testedInstance = new JLPagination();

    @Test
    public void testPaginateWith1TotalAnd1Current() {
        assertThat(testedInstance.paginate(1, 1), contains("1"));
    }

    @Test
    public void testPaginateWhen2TotalAnd1Current() {
        assertThat(testedInstance.paginate(1, 2), contains("1", "2"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPaginateWhenArgumentsLessThanOne() {
        testedInstance.paginate(-1,0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPaginateWhenCuurentGreaterThanTotal() {
        testedInstance.paginate(10, 1);
    }

    @Test
    public void testPaginateWhen3TotalAnd1Current() {
        assertThat(testedInstance.paginate(1, 3), contains("1", "2", "3"));
    }

    @Test
    public void testPaginateWhen1Current10Total() {
        assertThat(testedInstance.paginate(1, 10), contains("1", "2", "3", "4", "...", "10"));
    }

    @Test
    public void testPagianteWhen2Current10Total()  {
        assertThat(testedInstance.paginate(1, 10), contains("1", "2", "3", "4", "...", "10"));
    }

    @Test
    public void testPaginateWhenCurrent9Total10() {
        assertThat(testedInstance.paginate(9, 10), contains("1", "...", "7", "8", "9", "10"));
    }

    @Test
    public void testPaginateWhenCurrent1Total5() {
        assertThat(testedInstance.paginate(1, 5), contains("1", "2", "3", "4", "5"));
    }

    @Test
    public void testPaginateWhenCurrent4Total5() {
        assertThat(testedInstance.paginate(4, 5), contains("1", "2", "3", "4", "5"));
    }

    @Test
    public void testPaginateWhenCurrent5Total10() {
        assertThat(testedInstance.paginate(5, 10), contains("1", "...", "4", "5", "6", "...", "10"));
    }

    @Test
    public void testPaginateWhenCurrent10Total20() {
        assertThat(testedInstance.paginate(10, 20), contains("1", "...", "9", "10", "11", "...", "20"));
    }
}

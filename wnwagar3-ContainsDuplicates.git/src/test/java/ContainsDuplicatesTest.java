
import org.junit.Assert;
import org.junit.Test;

public class ContainsDuplicatesTest {
    ContainsDuplicates cd = new ContainsDuplicates();

    /**
     * The array {1, 2, 3, 4, 5} does not contain any duplicates, so containsDuplicate should return false.
     */
    @Test
    public void containsDuplicatesTest1(){
        int[] nums = {1, 2, 3, 4, 5};
        boolean target = false;
        Assert.assertEquals(target, cd.containsDuplicate(nums));
    }
    /**
     * The array {1, 2, 2, 3, 4, 5} contains duplicates, so containsDuplicate should return true.
     */
    @Test
    public void containsDuplicatesTest2(){
        int[] nums = {1, 2, 2, 3, 4, 5};
        boolean target = true;
        Assert.assertEquals(target, cd.containsDuplicate(nums));
    }
    /**
     * The array {1, 1, 1, 1, 1} contains duplicates, so containsDuplicate should return true.
     */
    @Test
    public void containsDuplicatesTest3(){
        int[] nums = {1, 1, 1, 1, 1};
        boolean target = true;
        Assert.assertEquals(target, cd.containsDuplicate(nums));
    }
    /**
     * The array {5,8,3,4,5,0} contains duplicates, so containsDuplicate should return true.
     */
    @Test
    public void containsDuplicatesTest4(){
        int[] nums = {5,8,3,4,5,0};
        boolean target = true;
        Assert.assertEquals(target, cd.containsDuplicate(nums));
    }
}

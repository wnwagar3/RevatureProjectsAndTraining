
import java.util.List;

public class LargestSum {
    /**
     * Get the largest possible sum that can be obtained from a pair of values in the list. A number can't be added
     * to itself, unless there are duplicates.
     *
     * @param nums a list of ints.
     * @return the largest possible sum of separate numbers from nums.
     */
    public int bigSum(List<Integer> nums){
        int biggestSum = -10000;
        int listSize = nums.size();
        for (int i = 0; i < listSize -1 ; i++) {
            for (int j = i + 1; j < listSize; j++){
                if (nums.get(i) + nums.get(j) > biggestSum) {
                    biggestSum = nums.get(i) + nums.get(j);
                }
            }
        }
        return biggestSum;
    }
}
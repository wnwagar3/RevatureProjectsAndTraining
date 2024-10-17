
public class ReturnLongestString {
    /**
     * Return the longest String out of arr.
     * Remember that you can get the length of String with str.length().
     *
     * @param arr an array of Strings.
     * @return the longest String within arr. You can assume that there are no ties for the longest string.
     */
    public String longest(String[] arr){
        String longestString = arr[0];
        for (String str : arr){
            if (str.length() > longestString.length()){
                longestString = str;
            }

        }
        return longestString;
    }
}

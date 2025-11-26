import java.util.ArrayList;
import java.util.List;

public class algorithm {
    //A function that accepts an array and returns a list
    // of all subarrays that are in ascending order and in a sequence of at least 2.
    public static List<List<Integer>> getIncreasingSubarrays(int[] arr) {
        List<List<Integer>> res = new ArrayList<>();
        if (arr.length < 2) return res;//If my array is less than two, there is no sequence.

        List<Integer> current = new ArrayList<>();
        current.add(arr[0]);

        for (int i = 1; i < arr.length; i++) {
            //A loop that iterates and each time compares the current element
            // to the previous element in the list, and accordingly
            // increments or resets the current list.
            if (arr[i] > arr[i - 1]) {
                current.add(arr[i]);
            } else {
                if (current.size() >= 2)
                    res.add(new ArrayList<>(current));
                current.clear();
                current.add(arr[i]);
            }
        }
        // If the last sequence also increases
        if (current.size() >= 2)
            res.add(current);

        return res;
    }


    public static void main(String[] args) {
        //Testing the algorithm
        int[] arr= {5,4,3,4,5,10,8,9,100,8};
        for (List<Integer> list: getIncreasingSubarrays(arr)){
            for (int i: list)
                System.out.print(i+", ");
            System.out.println();
        }
    }
}
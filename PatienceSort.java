import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class PatienceSort {
    static int count = 0;

    public static List<Integer> patienceSort(List<Integer> arr) {
        count = 0;
        List<List<Integer>> stopki = new ArrayList<List<Integer>>();
        for (int i = 0; i < arr.size(); i++) {
            if (stopki.isEmpty()) {
                List<Integer> temp = new ArrayList<Integer>();
                temp.add(arr.get(i));
                stopki.add(temp);
            } else {
                boolean flag = true;
                for (int j = 0; j < stopki.size(); j++) {
                    if (arr.get(i) < stopki.get(j).get(stopki.get(j).size() - 1)) {
                        stopki.get(j).add(arr.get(i));
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    List<Integer> temp = new ArrayList<Integer>();
                    temp.add(arr.get(i));
                    stopki.add(temp);
                }
            }
            count++;
        }
        List<Integer> ans = sobratStopki(stopki);
        return ans;
    }

    public static List<Integer> sobratStopki(List<List<Integer>> stopki) {

        List<Integer> ans = new ArrayList<Integer>();
        while (true) {
            int min = Integer.MAX_VALUE;
            int index = -1;

            for (int i = 0; i < stopki.size(); i++) {

                if (!stopki.get(i).isEmpty() && min > stopki.get(i).get(stopki.get(i).size() - 1)) {
                    min = stopki.get(i).get(stopki.get(i).size() - 1);
                    index = i;
                }
                count++;
            }
            if (index == -1) {
                break;
            }
            ans.add(min);
            stopki.get(index).remove(stopki.get(index).size() - 1);
            if (stopki.get(index).isEmpty()) {
                stopki.remove(index);
            }
        }
        return ans;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.txt"));
        while (sc.hasNext()){
            List<Integer> arr = new ArrayList<Integer>();
            String s = sc.nextLine();
            String[] scan = s.split(" ");
            for (int i = 0; i < scan.length; i++) {
                arr.add(Integer.parseInt(scan[i]));
            }
            long start = System.nanoTime();
            patienceSort(arr);
            long finish = System.nanoTime();
            long time = finish - start;
            System.out.print(time);
            System.out.print(" ");
            System.out.println(count);
            System.out.print(" ");
            System.out.println(scan.length);
        }
    }
}

import java.util.Collections;
import java.util.ArrayList;

public class SortTest{

  //INSERTION SORT from Bailey's using generic type
  private static <T extends Comparable<T>> void insertionSort(ArrayList<T> data) {
    int n = data.size();
    int index, numSorted = 1;
    while (numSorted < n) {
      T temp = data.get(numSorted);
      for (index = numSorted; index > 0; index--) {
        if (temp.compareTo(data.get(index-1)) < 0) {
          data.set(index, data.get(index-1));
        } else {
          break;
        }
      }
      data.set(index, temp);
      numSorted++;
    }
  }

  //MERGE METHOD from Bailey's using generic type
  private static <T extends Comparable<T>> void merge(ArrayList<T> data, ArrayList<T> temp, int low, int middle, int high){
    int ri = low; //result index
    int ti = low; //temp index
    int di = middle; // destination index
    // while two lists are not empty, merge smaller first value
    while (ti < middle && di <= high){
      if (data.get(di).compareTo(temp.get(ti)) < 0){
         data.set(ri++, data.get(di++));
       } else {
         data.set(ri++, temp.get(ti++));
       }
    }
    // rescue values left in the temp array
    while (ti < middle){
      data.set(ri++, temp.get(ti++));
    }
  }

  //MERGE SORT from Bailey's using generic type
  private static <T extends Comparable<T>> void mergeSortRecursive(ArrayList<T> data, ArrayList<T> temp, int low, int high) {
    int n = high - low + 1;
    int middle = low + (n/2);
    if (n < 2) return;
    //move lower half into temporary storage
    for (int i = low; i < middle; i++) {
      temp.set(i, data.get(i));
    }
    //use method recursively on lower and upper half of range.. then merge
    mergeSortRecursive(temp,data,low,middle-1);
    mergeSortRecursive(data,temp,middle,high);
    merge(data,temp,low,middle,high);
  }

  //HYBRID SORT using generic type
  private static <T extends Comparable<T>> void hybridSort(ArrayList<T> data){
    //check how many of 100* evenly spaced values are in order.. *(100 for n=10,000)
    int numsInOrder = 0;
    for (int i = 0; i < (data.size()-100); i = i + 100) {
      if (data.get(i).compareTo(data.get(i+100)) < 0) numsInOrder++;
    }
    //if mostly sorted.. run insertion sort...
    if (numsInOrder > 60) insertionSort(data);
    else { // run merge sort..
      ArrayList<T> temp = new ArrayList<T>();
      for (int i = 0; i < (data.size()); i++) temp.add(null);
      mergeSortRecursive(data, temp, 0, data.size() - 1);
    }
  }

  //TEST SPEED using string from homework document
  private static void testSpeed(ArrayList<String> testList, String sort_type) {
    int iterations = 0;
    int index;
    long startTime = System.currentTimeMillis();
    //run for at least 5 seconds
    while (System.currentTimeMillis()- startTime < 5000) {
      iterations++;
      //shuffle the arrays in two different possible ways
      if (Math.random() < 0.5) {
        //completely shuffle the arrays
        Collections.shuffle(testList);
      } else {
          for (int i=0; i< 0.001*testList.size(); i++) {
            //pick a random index
            index = (int)Math.floor(Math.random() * testList.size());
            // replace that sequence with a new one
            testList.set(index, geneSequence());
          }
      }
      //now sort the array based on call..
      if (sort_type.equals("insertion")) insertionSort(testList);
      if (sort_type.equals("hybrid")) hybridSort(testList);
      if (sort_type.equals("merge")){
        //initialize temp array with list of null strings
        ArrayList<String> temp = new ArrayList<String>();
        for (int i = 0; i< (testList.size()); i++) temp.add(null);
        mergeSortRecursive(testList, temp, 0, testList.size() - 1);
      }
    } //END OF WHILE loop
    long totalTime = System.currentTimeMillis() - startTime;
    System.out.println("" + sort_type + " sort was able to run " + String.valueOf(iterations)
    + " times in " + String.valueOf(totalTime) + " ms ");
  }

  //GENE SEQUENCE method from homework document
  private static String geneSequence(){
    char [] seq = new char[20];
    double p;
    for (int i = 0; i < seq.length; i++) {
      p = Math.random();
      if (p < 0.25) seq[i] = 'A';
      else if (p < 0.5) seq[i] = 'G';
      else if (p < 0.75) seq[i] = 'G';
      else seq[i] = 'G';
    }
    return new String(seq);
  }

  //MAIN method
  public static void main(String[] args) {
    //initialize testing array and fill with gene sequences
    ArrayList<String> testList;
    final int ARRAY_SIZE = 10000;
    testList = new ArrayList<String>();
    for (int i = 0; i < ARRAY_SIZE; i++) testList.add(geneSequence());
    //calculate and display the timing for insertion sort, merge sort and the hybrid
    testSpeed(testList, "insertion");
    testSpeed(testList, "merge");
    testSpeed(testList, "hybrid");
  }
}

import java.util.Collections;
import java.util.ArrayList;

public class sorts_homework_test {

  private static String geneSequence () {
  char [] seq = new char[20];
  double p;
  for (int i = 0; i < seq.length; i++) {
    p = Math.random();
    if (p < 0.25) {
      seq[i] = 'A';
    } else if (p < 0.5) {
      seq[i] = 'G';
    } else if (p < 0.75) {
      seq[i] = 'T';
    } else {
      seq[i] = 'C';
    }
  }
  return new String(seq);
} //end of geneSequence

  public static void main(String[] args) {

    ArrayList<String> testList;
    final static int ARRAY_SIZE = 10000;
    testList = new ArrayList<String>();

    // fill the ArrayList with gene sequences
    for (int i = 0; i < ARRAY_SIZE; i++) {
      testList.add(geneSequence());
    }

    //  calculate and display the timing for insertion sort
    //testSpeed(testList, "insertion");

    // calcuate and display the timing for mergesort
    //testSpeed(testList, "merge");

    // calculate and display the timing for the hybrid
    //testSpeed(testList, "hybrid");

  } // end of main function

}

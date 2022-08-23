package task2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * @author Oleh Fliurkevych
 */
public class Task2 {
  
  public static void main(String[] args) {
    List<Integer> collection = new ArrayList<>();

    var infinitePutThread = new PutThread(collection);
    var sumThread = new SumThread(collection);
    var sqrtSumThread = new SqrtSumThread(collection);

    var executorService = Executors.newFixedThreadPool(3);
    executorService.execute(infinitePutThread);
    executorService.execute(sumThread);
    executorService.execute(sqrtSumThread);

  }

}

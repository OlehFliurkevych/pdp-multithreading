package task2;

import java.util.Collection;

/**
 * @author Oleh Fliurkevych
 */
public class SqrtSumThread extends Thread {

  private final Collection<Integer> collection;
  
  public SqrtSumThread(Collection<Integer> collection) {
    Thread.currentThread().setName("SqrtSumThread");
    System.out.println("Create thread :" + Thread.currentThread().getName());
    this.collection = collection;
  }

  @Override
  public void run() {
    System.out.println("Start thread :" + Thread.currentThread().getName());
    while (true) {
      synchronized (collection){
        var sum = Math.sqrt(collection.stream().mapToInt(Integer::intValue).map(i -> i * i).sum());
        System.out.println("Square root of sum of squares of numbers: " + sum);
      }
    }
  }

}

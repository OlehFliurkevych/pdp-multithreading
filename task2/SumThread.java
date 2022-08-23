package task2;

import java.util.Collection;

/**
 * @author Oleh Fliurkevych
 */
public class SumThread extends Thread {

  private final Collection<Integer> collection;


  public SumThread(Collection<Integer> collection) {
    Thread.currentThread().setName("SumThread");
    System.out.println("Create thread :" + Thread.currentThread().getName());
    this.collection = collection;
  }

  @Override
  public void run() {
    System.out.println("Start thread :" + Thread.currentThread().getName());
    while (true) {
      synchronized (collection) {
        var sum = collection.stream().mapToInt(Integer::intValue).sum();
        System.out.println("Sum of numbers: " + sum);
      }
    }
  }

}

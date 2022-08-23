package task2;

import java.util.Collection;
import java.util.Random;

/**
 * @author Oleh Fliurkevych
 */
public class PutThread extends Thread {

  private static final Random random = new Random();
  private final Collection<Integer> collection;

  public PutThread(Collection<Integer> collection) {
    Thread.currentThread().setName("InfinitePutThread");
    System.out.println("Create thread :" + Thread.currentThread().getName());
    this.collection = collection;
  }

  @Override
  public void run() {
    System.out.println("Start thread :" + Thread.currentThread().getName());
    while (true) {
      synchronized (collection){
        var num = random.nextInt();
        collection.add(num);
        System.out.println("Add number: " + num);
      }
    }

  }
}

package task1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Oleh Fliurkevych
 */
public class Task1 {

  public static void main(String[] args) {

//    HashMap<Integer, Integer> map = new HashMap<>();
    ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();
//    Map<Integer, Integer> map2 = Collections.synchronizedMap(map);

    Thread threadPut = new Thread(() -> {
      Thread.currentThread().setName("Put thread");
      System.out.println("Thread " + Thread.currentThread().getName() + " started.");
//      synchronized (map) {
        for (int i = 0; i < 1_000_000; i++) {
          map.put(i, i);
        }
//      }
      System.out.println("Thread " + Thread.currentThread().getName() + " finished.");
    });

    Thread threadSum = new Thread(() -> {
      Thread.currentThread().setName("Sum thread");
      System.out.println("Thread " + Thread.currentThread().getName() + " started.");
      int sum = 0;
//      synchronized (map) {
        Iterator<Entry<Integer, Integer>> iterator = map.entrySet().iterator();
        if (iterator.hasNext()) {
          sum = sum + iterator.next().getValue();
//        }
      }
      System.out.println(sum);
      System.out.println("Thread " + Thread.currentThread().getName() + " finished.");
    });

    threadPut.start();
    threadSum.start();

  }

}

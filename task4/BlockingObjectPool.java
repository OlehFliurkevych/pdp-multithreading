package task4;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Oleh Fliurkevych
 */
public class BlockingObjectPool<E> {

  private final int size;
  private final Queue<E> objects;

  private final Lock lock = new ReentrantLock();

  private final Condition fullCondition = lock.newCondition();

  private final Condition emptyCondition = lock.newCondition();

  public BlockingObjectPool(int size) {
    this.size = size;
    objects = new PriorityQueue<>(size);
  }

  /**
   * Gets object from pool or blocks if pool is empty
   * * @return object from pool
   */
  public E get() throws InterruptedException {
    lock.lock();
    try {
      while (objects.isEmpty()) {
        emptyCondition.await();
      }
      fullCondition.signal();
      return objects.poll();
    } finally {
      lock.unlock();
    }
  }

  /**
   * Puts object to pool or blocks if pool is full
   *
   * @param object to be taken back to pool
   */
  public void take(E object) throws InterruptedException {
    lock.lock();
    try {
      while (objects.size() == size) {
        fullCondition.await();
      }
      emptyCondition.signal();
      objects.add(object);
      System.out.println("An object was added to the pool"
        + objects);
    } finally {
      lock.unlock();
    }
  }

}

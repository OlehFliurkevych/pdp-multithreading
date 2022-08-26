package task3;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Oleh Fliurkevych
 */
public class BlockingQueue<E> {

  private final List<E> queue = new LinkedList<>();

  private final int limit;

  public BlockingQueue(int limit) {
    this.limit = limit;
  }

  public synchronized void put(E item) throws InterruptedException {
    while (this.queue.size() == this.limit) {
      wait();
    }
    if (this.queue.size() == 0) {
      notifyAll();
    }
    this.queue.add(item);
  }

  public synchronized E take() throws InterruptedException {
    while (this.queue.size() == 0) {
      wait();
    }
    if (this.queue.size() == this.limit) {
      notifyAll();
    }
    return this.queue.remove(0);
  }

}

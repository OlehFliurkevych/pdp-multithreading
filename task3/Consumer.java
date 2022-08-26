package task3;

/**
 * @author Oleh Fliurkevych
 */
public class Consumer extends Thread {

  private final BlockingQueue<Message> queue;

  Consumer(BlockingQueue<Message> queue) {
    this.queue = queue;
  }

  public void run() {
    while (true) {
      try {
        var message = this.queue.take();
        System.out.println(
          "Consumed by thread [" + Thread.currentThread().getName() + "] message ["
            + message.getPayload() + "]");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}

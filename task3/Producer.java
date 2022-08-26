package task3;

import java.util.Random;

/**
 * @author Oleh Fliurkevych
 */
public class Producer extends Thread {

  private static final Random random = new Random();
  public final BlockingQueue<Message> queue;

  Producer(BlockingQueue<Message> queue) {
    this.queue = queue;
  }

  public void run() {
    while (true) {
      var message = new Message("" + random.nextInt());
      try {
        System.out.println(
          "Produced by thread [" + Thread.currentThread().getName() + "] message ["
            + message.getPayload() + "]");
        queue.put(message);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      sleep();
    }
  }

  private void sleep() {
    try {
      sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}

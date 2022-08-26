package task3;

/**
 * @author Oleh Fliurkevych
 */
public class Task3 {

  public static void main(String[] args) {
    BlockingQueue<Message> queue = new BlockingQueue<>(3);

    Consumer consumer1 = new Consumer(queue);
    Consumer consumer2 = new Consumer(queue);
    Consumer consumer3 = new Consumer(queue);

    Producer producer1 = new Producer(queue);
    Producer producer2 = new Producer(queue);
    Producer producer3 = new Producer(queue);

    Thread producerThread1 = new Thread(producer1);
    Thread producerThread2 = new Thread(producer2);
    Thread producerThread3 = new Thread(producer3);
    Thread consumerThread1 = new Thread(consumer1);
    Thread consumerThread2 = new Thread(consumer2);
    Thread consumerThread3 = new Thread(consumer3);

    consumerThread1.start();
    consumerThread2.start();
    consumerThread3.start();

    producerThread1.start();
    producerThread2.start();
    producerThread3.start();

  }

}

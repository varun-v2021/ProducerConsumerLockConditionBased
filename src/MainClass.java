
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> sharedQueue = new LinkedList<Integer>(); // Creating shared object

		Lock lock = new ReentrantLock();
		// producerCondition
		Condition producerCondition = lock.newCondition();
		// consumerCondition
		Condition consumerCondition = lock.newCondition();

		Producer producer = new Producer(sharedQueue, lock, producerCondition, consumerCondition);
		Consumer consumer = new Consumer(sharedQueue, lock, producerCondition, consumerCondition);

		Thread producerThread = new Thread(producer, "ProducerThread");
		Thread consumerThread = new Thread(consumer, "ConsumerThread");
		producerThread.start();
		consumerThread.start();
	}

}

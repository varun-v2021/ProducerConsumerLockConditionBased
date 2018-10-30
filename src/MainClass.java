
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Shared Object
		List<Integer> sharedQueue = new LinkedList<Integer>(); 

		Lock lock = new ReentrantLock();
		
		Condition producerCondition = lock.newCondition();

		Condition consumerCondition = lock.newCondition();

		Producer producer = new Producer(sharedQueue, lock, producerCondition, consumerCondition);
		Consumer consumer = new Consumer(sharedQueue, lock, producerCondition, consumerCondition);

		Thread producerThread = new Thread(producer, "ProducerThread");
		Thread consumerThread = new Thread(consumer, "ConsumerThread");
		producerThread.start();
		consumerThread.start();
	}

}

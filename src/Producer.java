import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Producer implements Runnable {
	private List<Integer> sharedQueue;
	private int maxSize = 5; 

	Lock lock;
	Condition producerCondition;
	Condition consumerCondition;

	public Producer(List<Integer> sharedQueue, Lock lock, Condition producerCondition, Condition consumerCondition) {
		this.sharedQueue = sharedQueue;
		this.lock = lock;
		this.producerCondition = producerCondition;
		this.consumerCondition = consumerCondition;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			try {
				produce(i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void produce(int i) throws InterruptedException {
		lock.lock();

		if (sharedQueue.size() == maxSize) {
			producerCondition.await();
		}

		System.out.println("Produced : " + i);
		sharedQueue.add(i);
		consumerCondition.signal();

		lock.unlock();

	}
}

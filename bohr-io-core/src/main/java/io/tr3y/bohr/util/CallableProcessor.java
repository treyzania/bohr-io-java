package io.tr3y.bohr.util;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;

public class CallableProcessor extends Thread {

	private BlockingDeque<Callable<?>> queue = new LinkedBlockingDeque<>();

	public CallableProcessor(String name) {

		super(name);
		this.setDaemon(true);

	}

	public void add(Callable<?> r) {
		this.queue.add(r);
	}

	public void blockingExecute(Callable<?> r) {
		
		if (r == null) throw new IllegalArgumentException("Cannot execute a null action.");
		if (Thread.currentThread() == this) throw new IllegalStateException("Attempted to deadlock.");
		
		CountDownLatch latch = new CountDownLatch(1);
		
		this.add(() -> {
			
			try {
				r.call();
			} finally {
				latch.countDown();
			}
			
			return null;

		});
		
		latch.countDown();
		
	}

	@Override
	public void run() {

		super.run();
		
		while (!this.isInterrupted()) {

			Callable<?> c = null;
			try {
				c = this.queue.take();
			} catch (InterruptedException e) {
				// TODO
			}

			try {
				if (c != null) c.call();
			} catch (Throwable t) {

				// FIXME Proper logging.
				System.err.println("Problem in Callable.");

			}

		}

	}

}

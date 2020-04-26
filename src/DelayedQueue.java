import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Queue for which all Word elements will be made available after some delay set up
 * in the constructor. The delay can be more or less than the delay set up. It is not very exact.
 * From an external point of view, only the delayed messages are observable.
 * Most importtantly, put(e) will put an element in the first queue, the element will be transfered after some delay
 * in a second queue, and take() will only return elements from the second queue.
 */
public final class DelayedQueue implements BlockingQueue<Word> {

    private final long delay;
    private final int maxBatch;
    private final BlockingQueue<Word> input;
    private final BlockingQueue<Word> output;
    private final ScheduledExecutorService executor;

    public DelayedQueue(long delayInMs, int maxBatch) {
        delay = delayInMs;
        this.maxBatch = maxBatch;
        input = new LinkedBlockingDeque<>();
        output = new LinkedBlockingDeque<>();
        executor = new ScheduledThreadPoolExecutor(1);
    }

    public void start() {
        executor.scheduleWithFixedDelay(
            () -> {
                // System.out.println("Starting to transfer words from input to output");
                try {
                    int i = 0;
                    while(input.peek() != null && i < maxBatch) {
                        // System.out.println("Transfer word: " + input.peek().get());
                        output.put(input.take());
                        i++;
                    }
                } catch (InterruptedException e) {
                    System.out.print("thread interrupted");
                }
            }, 
            delay,
            delay,
            TimeUnit.MILLISECONDS);
    }

    public void stop() {
        executor.shutdown();
    }

    @Override
    public int size() {
        return output.size();
    }

    @Override
    public boolean isEmpty() {
        return output.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return output.contains(o);
    }

    @Override
    public Iterator<Word> iterator() {
        return output.iterator();
    }

    @Override
    public Object[] toArray() {
        return output.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return output.toArray(a);
    }

    @Override
    public boolean remove(Object o) {
        return output.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return output.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Word> c) {
        return input.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return output.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return output.retainAll(c);
    }

    @Override
    public void clear() {
        output.clear();
    }

    @Override
    public boolean add(Word e) {
        return input.add(e);
    }

    @Override
    public boolean offer(Word e) {
        return input.offer(e);
    }

    @Override
    public Word remove() {
        return output.remove();
    }

    @Override
    public Word poll() {
        return output.poll();
    }

    @Override
    public Word element() {
        return output.element();
    }

    @Override
    public Word peek() {
        return output.peek();
    }

    @Override
    public void put(Word e) throws InterruptedException {
        input.put(e);
    }

    @Override
    public boolean offer(Word e, long timeout, TimeUnit unit) throws InterruptedException {
        return input.offer(e, timeout, unit);
    }

    @Override
    public Word take() throws InterruptedException {
        return output.take();
    }

    @Override
    public Word poll(long timeout, TimeUnit unit) throws InterruptedException {
        return output.poll(timeout, unit);
    }

    @Override
    public int remainingCapacity() {
        return output.remainingCapacity();
    }

    @Override
    public int drainTo(Collection<? super Word> c) {
        return output.drainTo(c);
    }

    @Override
    public int drainTo(Collection<? super Word> c, int maxElements) {
        return output.drainTo(c, maxElements);
    }

}
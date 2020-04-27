import java.util.concurrent.BlockingQueue;

public final class QueueReceiver implements Receiver {

    private BlockingQueue<Word> queue;

    public QueueReceiver(BlockingQueue<Word> queue) {
        this.queue = queue;
    }

    @Override
    public Word receive() {
        try {
            return queue.take();
        } catch (Exception e) {
            System.out.println("Error receiving messages");
            return null;
        }
    }
}
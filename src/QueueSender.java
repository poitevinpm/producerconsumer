import java.util.concurrent.BlockingQueue;

public final class QueueSender implements Sender {

    private BlockingQueue<Word> queue;

    public QueueSender(BlockingQueue<Word> queue) {
        this.queue = queue;
    }

    @Override
    public boolean send(Word w) {
        try {
            queue.put(w);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
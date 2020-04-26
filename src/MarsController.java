import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MarsController {

    public final Receiver receiver;
    public final ExecutorService executor;

    public MarsController(BlockingQueue<Word> queue) {
        receiver = new QueueReceiver(queue);
        executor = Executors.newFixedThreadPool(1);
    }

    public final void start() {
        executor.submit(
            () -> {
                Word w  = receiver.receive();
                while (w != null) {
                    System.out.print(w.get() + " ");
                    w = receiver.receive();
                }
            }
        );
    }

    public final void stop() {
        executor.shutdown();
    }
}
import java.io.InputStream;
import java.util.concurrent.BlockingQueue;

public final class EarthController {

    private final Parser parser;

    public EarthController(BlockingQueue<Word> queue) {
        Sender sender = new QueueSender(queue);
        parser = new Parser(sender);
    }

    public boolean processURL(String url) {
        try {
            Downloader d = new Downloader(url);
            InputStream stream = d.getStream();
            return parser.breakDownAndSend(stream);
        } catch (Exception e) {
            System.out.println("Error while processing url: " + url);
            System.out.println(e);
            return false;
        }
    }
}
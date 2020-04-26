import java.io.InputStream;
import java.util.Scanner;

public final class Parser {

    private final Sender sender;

    public Parser(Sender sender) {
        this.sender = sender;
    }

    public boolean breakDownAndSend(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);
        boolean success = true;
        while (scanner.hasNext()) {
            String s = scanner.next();
            try {
                Word w = new Word(s);
                if (!sender.send(w)) {
                    success = false;
                }
            } catch (Exception e) {
                System.out.println("Error processing string: " + s);
                success = false;
            }
        }
        scanner.close();
        return success;
    }
}
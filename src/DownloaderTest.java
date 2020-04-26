import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public final class DownloaderTest {

    public static void test() {
        try {
            // https://pastebin.com/raw/1MttbNKa
            Downloader instance = new Downloader("https://pastebin.com/raw/1MttbNKa");
            InputStream stream = instance.getStream();
            String text = null;
            try (Scanner scanner = new Scanner(stream, StandardCharsets.UTF_8.name())) {
                text = scanner.useDelimiter("\\A").next();
            }
            System.out.println(text);
        } catch (Exception e) {
            System.out.println("An exception occurred");
            System.out.println(e);
        }
    }
}
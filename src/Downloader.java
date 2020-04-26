import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public final class Downloader {

    private final URL toDownload;

    public Downloader(String urlString) throws MalformedURLException {
        toDownload = new URL(urlString);
    }

    public InputStream getStream() throws IOException {
        return toDownload.openStream();
    }
}
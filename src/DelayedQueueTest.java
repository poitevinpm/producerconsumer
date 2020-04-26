
import java.util.ArrayList;
import java.util.List;

import javax.management.InvalidAttributeValueException;

public class DelayedQueueTest {

    public static void test() {
        try {
            DelayedQueue dq = new DelayedQueue(3000L);
            String[] stringArr = new String[] { "Hello", "from", "the", "other", "side" };
            List<Word> toSend = wordsFromArr(stringArr);
            int n = toSend.size();
            for (Word w : toSend) {
                System.out.println("Send word: " + w.get());
                dq.put(w);
            }
            dq.start();
            for (int i = 0; i < n; i++) {
                System.out.println("Receive word: " + dq.take().get());
            }
            dq.stop();
            System.out.println("Goodbye!");
        } catch (Exception e) {
            System.out.println("execution error");
            System.out.println(e);
        }

    }

    private static List<Word> wordsFromArr(String[] arr) throws InvalidAttributeValueException {
        List<Word> res = new ArrayList<>();
        for (String s : arr) {
            res.add(new Word(s));
        }
        return res;
    }
}
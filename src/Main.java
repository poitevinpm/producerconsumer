class Main {

    public static void main(String[] args) {
        DelayedQueue dq = new DelayedQueue(200L, 1);
        dq.start();
        EarthController controller = new EarthController(dq);
        boolean success = controller.processURL("https://pastebin.com/raw/1Av2Xxwd");
        System.out.println("success: " + success);
        while (true) {
            try {
                Word w = dq.take();
                System.out.print(w.get() + " ");
            } catch (Exception e) {
                System.out.println("Error while consuming the queue: " + e);
            }
        }
    }
}
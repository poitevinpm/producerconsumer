class Main {

    public static void main(String[] args) {
        DelayedQueue dq = new DelayedQueue(100L, 2);
        dq.start();
        EarthController controller = new EarthController(dq);
        MarsController marsController = new MarsController(dq);
        marsController.start();
        boolean success = controller.processURL("https://pastebin.com/raw/1Av2Xxwd");
        System.out.println("success: " + success);
    }
}
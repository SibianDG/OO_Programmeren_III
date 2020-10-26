public class Miltitreading {

    public static void main(String[] args) {
        System.out.println("Start main multitreading");
        System.out.println(Thread.currentThread().getName());
        Thread x = new Thread(() -> {
            System.out.println("Extra thread");
            System.out.println(Thread.currentThread().getName());
        });
        x.start();
        new Thread(() -> {
            System.out.println("Extra2 thread");
            System.out.println(Thread.currentThread().getName());
        }).start();
        System.out.println("Einde main multitreading");
    }
}

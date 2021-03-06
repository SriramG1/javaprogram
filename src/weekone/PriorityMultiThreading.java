package weekone;

class ThreadOne  extends Thread{

    @Override
    public void run() {
        for(int i=1000;i<=2000;i++){
            if(i%7==0) {
                System.out.println(Thread.currentThread().getName()+" -> "+i);
            }
        }
    }
}
class ThreadTwo extends Thread{

    @Override
    public void run() {
        for(int i=1000;i<=2000;i++){
            if(i%11==0) {
                System.out.println(Thread.currentThread().getName()+" -> "+i);
            }
        }
    }
}
class ThreadThree extends Thread{

    @Override
    public void run() {
        for(int i=1000;i<=2000;i++){
            if(i%13==0) {
                System.out.println(Thread.currentThread().getName()+" -> "+i);
            }
        }
    }
}
public class PriorityMultiThreading {
    public static void main(String[] args) {
        Thread thread1 = new ThreadOne();
        thread1.setPriority(Thread.MIN_PRIORITY);
        thread1.setName("Thread One");
        thread1.start();
        Thread thread2 = new ThreadTwo();
        thread2.setPriority(Thread.MAX_PRIORITY);
        thread2.setName("Thread Two");
        thread2.start();
        Thread thread3 = new ThreadThree();
        thread3.setPriority(Thread.NORM_PRIORITY);
        thread3.setName("Thread Three");
        thread3.start();
    }
}

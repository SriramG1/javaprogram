package weekone;

class FirstThread extends Thread{

    @Override
    public void run() {
        for(int i=1000;i<=2000;i++){
            if(i%7==0) {
                System.out.println(Thread.currentThread().getName()+" -> "+i);
            }
        }
    }
}
class SecondThread extends Thread{

    @Override
    public void run() {
        for(int i=1000;i<=2000;i++){
            if(i%11==0) {
                System.out.println(Thread.currentThread().getName()+" -> "+i);
            }
        }
    }
}
public class JoinMultiThreading extends Thread{
    @Override
    public void run() {
        for(int i=1000;i<=2000;i++){
            if(i%13==0) {
                System.out.println(Thread.currentThread().getName()+" -> "+i);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(new FirstThread());
        thread1.setName("Thread One");
        thread1.setPriority(1);
        thread1.start();
        thread1.join();
        Thread thread2 = new Thread(new SecondThread());
        thread2.setName("Thread Two");
        thread2.start();
        thread2.join();
        Thread thread3 = new Thread(new JoinMultiThreading());
        thread3.setName("Thread Three");
        thread3.start();
    }
}

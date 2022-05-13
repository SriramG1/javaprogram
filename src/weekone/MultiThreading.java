package weekone;

class Q1{
    int num;
    volatile boolean t1 = false;
    volatile boolean t2 = true;
    volatile boolean t3 = true;
    synchronized public void put(int num){
        if(t1){
            try {
                wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t1 = true;
        System.out.println("put : "+num);
        this.num += num;
        notifyAll();
    }
    synchronized public void get(){
        if(t2){
            try {
                wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t2 = false;
        System.out.println("get : "+num);
        notifyAll();
    }
    synchronized public void set(){
        if(t3){
            try {
                wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t3 = false;
        System.out.println("set : "+num);
        notifyAll();
    }
}
@SuppressWarnings("All")
class Thread_1 implements Runnable{
    Q1 q;
    public Thread_1(Q1 q){
        this.q = q;
        Thread thread = new Thread(this,"Producer");
        thread.start();
    }
    @Override
    public void run() {
        int i=0;
        while (true) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            q.put(1);
        }
    }
}
@SuppressWarnings("All")
class Thread_2 implements Runnable{
    Q1 q;
    public Thread_2(Q1 q){
        this.q = q;
        Thread thread = new Thread(this,"Consumer");
        thread.start();
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            q.get();
        }
    }
}
@SuppressWarnings("ALL")
class Thread_3 implements Runnable{
    Q1 q;
    public Thread_3(Q1 q){
        this.q = q;
        Thread thread = new Thread(this,"Thread_3");
        thread.start();
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            q.set();
        }
    }
}
public class MultiThreading {
    public static void main(String[] args) {
        Q1 q = new Q1();
        new Thread_1(q);
        new Thread_2(q);
        new Thread_3(q);
    }
}
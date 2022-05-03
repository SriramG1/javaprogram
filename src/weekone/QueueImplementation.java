package weekone;

import java.util.*;

class Queue{
    LinkedList<Integer> queue = new LinkedList<>();
    public void dequeue(){
        if(queue.size()==0){
            System.err.println("Queue underflow");
        }
        else{
            queue.remove(0);
        }
    }
    public void enqueue(int value){
        queue.add(value);
    }
    public void peek(){
        if(queue.size()==0){
            System.err.println("Queue is empty");
        }
        else {
            System.out.println(queue.get(0));
        }
    }
    public int poll(){
        int value=queue.get(0);
        queue.remove(0);
        return value;
    }
    public void print(){
        System.out.println(queue);
    }
}
public class QueueImplementation {
    public static void main(String[] args) {
        Queue queue = new Queue();
        queue.enqueue(10);
        queue.enqueue(70);
        queue.enqueue(40);
        queue.enqueue(90);
        queue.enqueue(20);
        queue.dequeue();
        queue.peek();
        queue.poll();
        queue.print();
    }
}

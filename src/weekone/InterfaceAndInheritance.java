package weekone;

import java.util.Scanner;

interface Shape{
    double getArea();
    double getPerimeter();
    void printDetails();
}
class Square implements Shape{
    double edge;
    Square(double edge){
        this.edge=edge;
    }
    @Override
    public double getArea() {
        return edge*edge;
    }
    @Override
    public double getPerimeter() {
        return 4*edge;
    }
    @Override
    public void printDetails() {
        System.out.println("Area of square :  "+getArea());
        System.out.println("Perimeter of square :  "+getPerimeter()+"\n");
    }
}
class Circle implements Shape{
    double radius;
    Circle(double radius){
        this.radius=radius;
    }
    @Override
    public double getArea() {
        return Math.PI*radius*radius;
    }

    @Override
    public double getPerimeter() {
        return 2*Math.PI*radius;
    }

    @Override
    public void printDetails() {
        System.out.println("Area of circle :  "+getArea());
        System.out.println("Perimeter of circle :  "+getPerimeter()+"\n");
    }
}
class Triangle implements Shape{
    double side1,side2;
    double base,height;
    Triangle(double side1,double side2,double base,double height){
        this.base=base;
        this.height=height;
        this.side1=side1;
        this.side2=side2;
    }
    @Override
    public double getArea() {
        return (height*base)/2;
    }

    @Override
    public double getPerimeter() {
        return side1+side2+base;
    }

    @Override
    public void printDetails() {
        System.out.println("Area of Triangle :  "+getArea());
        System.out.println("Perimeter of Triangle :  "+getPerimeter()+"\n");
    }
}
public class InterfaceAndInheritance {
    private void getInput() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the side of square : ");
        Square square = new Square(in.nextDouble());
        square.printDetails();
        System.out.println("Enter the radius of circle : ");
        Circle circle = new Circle(in.nextDouble());
        circle.printDetails();
        System.out.println("Enter triangle details (side one,side two,base,height) : ");
        Triangle triangle = new Triangle(in.nextDouble(),in.nextDouble(),in.nextDouble(),in.nextDouble());
        triangle.printDetails();
    }
    public static void main(String[] args) {
        InterfaceAndInheritance obj = new InterfaceAndInheritance();
        obj.getInput();
    }
}

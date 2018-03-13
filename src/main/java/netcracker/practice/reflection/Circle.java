package netcracker.practice.reflection;

import java.io.Serializable;

public class Circle extends Shape implements Serializable {
    private int radius;

    public Circle() {
    }

    @Override
    public void draw() {
        System.out.println(this + ".draw()");
    }

    @Override
    public String toString() {
        return "Circle";
    }

    private void privateMethod() {
    }

    @MyAnnotation("Annotation for method1()")
    public void method1(String string) {
        System.out.println(string);
    }

    @MyAnnotation()
    public void method2(int number) {
        System.out.println(number);
    }
}

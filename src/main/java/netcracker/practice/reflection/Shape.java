package netcracker.practice.reflection;

public abstract class Shape {
    public abstract void draw();

    public abstract String toString();

    private String privateShapeMethod() {
        return "privateShapeMethod";
    }

    @MyAnnotation("Shape annotation")
    protected void protectedShapeMethod() {
        System.out.println("protectedShapeMethod");
    }

    @MyAnnotation()
    public void publicShapeMethod() {
        System.out.println("publicShapeMethod");
    }
}

public class Circle extends Shape {
    private int radius;

    public Circle(String color, int radius) {
        super(color);
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a " + getColor() + " circle with radius " + radius);
    }

    @Override
    public void resize() {
        this.radius = this.radius * 2;
        System.out.println("Resizing circle - new radius: " + radius);
    }
}

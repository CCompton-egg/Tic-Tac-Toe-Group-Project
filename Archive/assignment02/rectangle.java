 public class Rectangle extends Shape {
    private int width;
    private int height;

    public Rectangle(String color, int width, int height) {
        super(color);
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a " + getColor() + " rectangle with width " + width + " and height " + height);
    }

    @Override
    public void resize() {
        this.width = this.width * 2;
        this.height = this.height * 2;
        System.out.println("Resizing rectangle - new width: " + width + ", new height: " + height);
    }
    
}

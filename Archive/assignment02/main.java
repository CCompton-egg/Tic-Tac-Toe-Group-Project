public class Main {
    public static void main(String[] args) {
        Drawable[] shapes = {
            new Circle("Red", 10),
            new Rectangle("Blue", 5, 8)
    
        };
        for (Drawable d : shapes) {
            d.draw();
            if (d instanceof Shape) {
                ((Shape) d).resize();
            }
        }
    }
}

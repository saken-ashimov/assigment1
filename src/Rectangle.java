public class Rectangle {
    private double width;
    private double height;
    private int id;
    private static int idGen = 1;

    // Constructor
    public Rectangle() {
        width = 1.0;
        height = 1.0;
        this.id = idGen++;
    }


    // Checking input
    public void setWidth(double width) {
        if (width <= 0)
            throw new IllegalArgumentException("Invalid input (width must be > 0)");
        this.width = width;
    }

    public void setHeight(double height) {
        if (height <= 0)
            throw new IllegalArgumentException("Invalid input (height must be > 0)");
        this.height = height;
    }

    // Getters
    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }


    // Constructor with parameters
    public Rectangle(double width, double height) {
        this();
        setWidth(width);
        setHeight(height);
    }


    // area and perimeter
    public double area() {
        return this.height * this.width;
    }

    public double perimeter() {
        return (this.height + this.width) * 2;
    }

    @Override
    public String toString() {
        return "Rectangle{id=" + id + ", width=" + width + ", height=" + height + "}";
    }

}

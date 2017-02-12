package graphicshape;

/**
 * Created by Sapir Michaeli on 05.02.2017.
 */
public class Circle extends  Shape {
    protected int x;
    int y;
    private int radius;

    public Circle() {
        x = 20;
        y = 20;
        radius = 10;
    }

    /**
     * sets the radius of the circle
     * @param radius non negative radius length
     */
    public void setRadius(int radius) {
        if(radius<0)
            return;
        this.radius = radius;
    }

    @Override
    public String toString() {
        //return super.toString(); // הוא כמו this אך במקרה של ירושה super מתייחס לאבא שממנו אני יורש , בעצם תפעיל את הSTRING  של האבא  -super
        return "center =(" + x + "," + y + ") and radius" + radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public double perimeter() {
        return 2*Math.PI*radius;
    }

    public void fillColor(int red, int green, int blue) {

    }

    //fillColor float, float, float
    public void fillColor(float hue, float saturation, float brightness) {
    }
}

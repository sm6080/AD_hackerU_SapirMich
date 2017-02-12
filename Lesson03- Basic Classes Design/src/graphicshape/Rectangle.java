package graphicshape;

/**
 * Created by Sapir Michaeli on 06.02.2017.
 */
public class Rectangle extends Shape{
    private int width;
    private int height;


    public Rectangle(int width, int height){
        setWidth(width);
        setHeight(height);
    }

    public void setWidth(int width){
        if(width<0)
            return;
        this.width=width;
    }

    public void setHeight(int height) {
        if(height<0)
            return;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public double area() {
        return width*height;
    }

    @Override
    public double perimeter() {
        return 2*(width+height);
    }
}

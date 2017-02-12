package graphicshape;

/**
 * Created by Sapir Michaeli on 05.02.2017.
 */
public abstract class Shape {

    public void refresh() {
        System.out.println("refreshing");
    }

    public abstract double area();

    public abstract double perimeter();
}

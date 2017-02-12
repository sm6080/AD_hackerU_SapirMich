package graphicshape;

/**
 * Created by Sapir Michaeli on 05.02.2017.
 */
public class Canvas {

    //לא ניתן לשנות את  canvas  (הכתובת) את איברי המערך- הערכים כמובן שאפשר
    private final boolean[][] canvas=new boolean[10][10];
    //אפשר בדרך הנ"ל לאתחל או ע"י הבנאי

    void draw(){
        Circle c=new Circle();
        c.x=120;

    }
    void refreshAll(final Shape[] shapes){
        for(Shape shape:shapes)
            shape.refresh();
    }
}

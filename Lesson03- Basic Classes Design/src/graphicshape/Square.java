package graphicshape;

/**
 * Created by hackeru on 06.02.2017.
 */
public final class Square extends Rectangle {

    public Square(int side) { // יווצר בניאי דיפולטי ויפעיל את הבנאי הדיפולטי של האבא
        super(side, side);// מפעיל את הבנאי של האבא
    }

    public void setSide(int side) {
        super.setWidth(side);  // השדות פרייבט ולכן צריך את set
        super.setHeight(side);
    }

    public int getSide() {
        return getHeight();// לא דרסתי את המתודה ולכן אני לא צריך super
    }

    // בשתי הפונקציות האלו הוא הולך לsetSide שלמעלה ושם את אותו ה side
    @Override
    public void setWidth(int width) {
        setSide(width);
    }

    @Override
    public void setHeight(int height) {
        setSide(height);
    }



}

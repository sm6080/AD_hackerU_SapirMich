package graphicshape;

/**
 * Created by Sapir Michaeli on 07.02.2017.
 */


public class Segment {
    private Point p1, p2;
    private double length;
    private boolean isLengthCalculated;

    //חייב להכיר פה את הטריק של השדה הבוליאי שחוסך מלא משאבים

    public Segment(Point p1, Point p2) {
        // ידוע שה SET מאתחל ולכן הורדנו את האתחול של השדות
        setP1(p1);
        setP2(p2);
    }

    public Point getP1() {

        return new Point(p1);// התגוננות מאליאסים -מחזיר P1 אחר
    }

    public void setP1(Point p1) {
        this.p1 = new Point(p1); // נשים אוביקט חדש לגמרי והערכים עם אותם ערכים, אך מי שקרא לו אין לו את הכתובת בזיכרון - התגוננות מאליאסים
        isLengthCalculated = false;
    }

    public Point getP2() {
        return p2;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
        isLengthCalculated = false;
    }

    //the distance between two points, 2 edges of segment
    public void calculateLength() {
        int deltaY = p1.getY() - p2.getY();
        int deltaX = p1.getX() - p2.getX();
        length = Math.sqrt(deltaX * deltaY + deltaY * deltaY);
        isLengthCalculated = true;
    }

    public double getLength() {
        if (!isLengthCalculated)
            calculateLength();
        return length;
    }

    //שיפוע
    public double slope() {
        double deltaY = p1.getY() - p2.getY();
        double deltaX = p1.getX() - p2.getX();
        if (deltaX == 0)
            return Double.MAX_VALUE;
        return deltaY / deltaX;
    }

    //A,B,C של ישר כלשהו

    /**
     * the line that goes through the two points p1,p2 that determine this segment can be represented as  Ax+By+c=0
     * @return returns the A of the equation
     */
    public double A() {
        return -1*slope();
    }

    public double B() {
        return 1;
    }
    //mx-y
    public double C() {
        return slope()*p1.getX()-p1.getY();
    }

    //מרחק נקודה מישר
    public double distanceToPoint (Point point) {
        double A = A();
        double numerator =A*point.getX()+B()*point.getY()+C();
        if(numerator<0)
            numerator*=-1;
        double denominator=Math.sqrt(A*A+B()+B());
        //B()*B()*B()==1
        return numerator/denominator;
    }



}


/*
נוסחאות:
זו אותה נוסחא , הראינו את הקשר בשלושת השורות הבאות
y=mx+n
Ax+By+c=0
-mx+1y=n

|Ax1+By1+C| /sqr(A^2+B^2)
 */

package com.company;

/**
 * Created by Sapir Michaeli on 12.02.2017.
 */

public class Shape {

    private Color color=new Color(10,12,13);

    // מחלקה עצמאית לכל דבר ומוגדרת בתוך  Shape רק מטעמי ארגון וסדר
    public static class Color {

        private int red, green, blue;

        public Color(int red, int green, int blue) {
            this.red = red;
            this.green = green;
            this.blue = blue;
            // Shape.this.getArea();   // א"א
        }

        public int getRed() {
            return red;
        }

        public void setRed(int red) {
            this.red = red;
        }

        public int getGreen() {
            return green;
        }

        public void setGreen(int green) {
            this.green = green;
        }

        public int getBlue() {
            return blue;
        }

        public void setBlue(int blue) {
            this.blue = blue;
        }
    }

    public double getArea(){
        return 0;
    }

}

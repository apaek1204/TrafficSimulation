package absentee;

import java.awt.Point;

public enum Landmarks {
	School (340,100),
    Hospital (100,340),
    Stadium (100,340),
    Bookstore (100,580),
    Park (350,340),
    Zoo (360,340),
    Bank (340,560),
    Market (340,580),
    Restaurant(560,340),
    Post_Office(460,580);

    private final int X;   // in kilograms
    private final int Y; // in meters
    Landmarks(int tmpX, int tmpY) {
        this.X = tmpX;
        this.Y = tmpY;
    }
    public Point getPoint() { return new Point(X,Y); }
    //private double getY() { return Y; }
}

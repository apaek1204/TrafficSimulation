package absentee;

public enum Landmarks {
	School (1,1),
    Hospital (1,1),
    Stadium (1,1),
    Bookstore (1,1),
    Park (1,1),
    Zoo (1,1),
    Bank (1,1),
    Market (1,1),
    Restaurant(1,1),
    Post_Office(1,1);


    private final double X;   // in kilograms
    private final double Y; // in meters
    Landmarks(double tmpX, double tmpY) {
        this.X = tmpX;
        this.Y = tmpY;
    }
    private double getX() { return X; }
    private double getY() { return Y; }
}

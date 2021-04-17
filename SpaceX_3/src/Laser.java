
import java.awt.Point;
import java.awt.event.MouseEvent;

public class Laser {

    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private final double angle;
    private final double pi = Math.PI;
    private final double multiplier = 13;
    private int inc = 0;

    public Laser(int mx, int my, Point spaceship) {
        x1 = spaceship.x + 15;
        y1 = spaceship.y + 15;
        //credit to Tomo Wakayama for getting the angle to work
        angle = Math.atan2((my - y1), (mx - x1));
        x2 += x1 + 50 * Math.cos(angle);
        y2 += y1 + 50 * Math.sin(angle);
    }

    public void Move() {
        x1 += multiplier * Math.cos(angle);
        x2 += multiplier * Math.cos(angle);
        y1 += multiplier * Math.sin(angle);
        y2 += multiplier * Math.sin(angle);
        //counts laser's moves
        inc++;
    }

    public int getX1() {
        return (int) x1;
    }

    public int getX2() {
        return (int) x2;
    }

    public int getY1() {
        return (int) y1;
    }

    public int getY2() {
        return (int) y2;
    }

    public int getInc() {
        return inc;
    }

}

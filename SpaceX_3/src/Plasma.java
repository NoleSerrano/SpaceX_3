
import java.awt.Point;

//shooter for the enemies
public class Plasma {

    private double x;
    private double y;
    private final double angle;
    private int inc = 0;
    private final double multiplier;

    public Plasma(Point enemy, Point spaceship, double mult, int move) {
        //if size of ball is 18 it's +6 for small enemy
        x = enemy.x + move;
        y = enemy.y + move;
        multiplier = mult;
        //problem creating angle @ top left of spaceship with top left of enemy ship need
        //to create angle with centers of each of the spaceship
        angle = Math.atan2(((spaceship.y + 15) - (enemy.y + move)), ((spaceship.x + 15)
                - (enemy.x + move)));
    }

    public void Move() {

        x += multiplier * Math.cos(angle);
        y += multiplier * Math.sin(angle);
        //counts plasmas's moves
        inc++;
    }

    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }

    public int getInc() {
        return inc;
    }

}

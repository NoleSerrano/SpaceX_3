
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

public class Enemy {

    //enemy class with internal timer to shoot plasma and to move
    //closer to the player
    private double x;
    private double y;
    private int spaceshipx;
    private int spaceshipy;
    private final Timer t;
    private double health = 20;
    //essentially modifies speed
    private final double multiplier = 1.3;
    private static ArrayList<Plasma> Plasmas;

    public Enemy(Point enemy, int speed) {
        Plasmas = new ArrayList();
        x = enemy.x;
        y = enemy.y;
        //every 300 seconds shoots
        ActionListener task = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1) {
                //if enemy is alive then shoot
                if (health > 0) {
                    doAction2(e1);
                }
            }

            private void doAction2(ActionEvent e1) {
                Plasmas.add(new Plasma(new Point((int) x, (int) y), new Point(spaceshipx, spaceshipy), 3.5, 6));
            }
        };
        t = new Timer(speed, task);
        t.start();
    }

    public void Move(Point enemy, Point spaceship) {
        //moves the enemy spaceship towards player's spaceship
        double angle = Math.atan2((spaceship.y - enemy.y), (spaceship.x - enemy.x));
        x += multiplier * Math.cos(angle);
        y += multiplier * Math.sin(angle);
    }

    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }

    public double getHealth() {
        return health;
    }

    public void decHealth() {
        health--;
    }

    public ArrayList<Plasma> getPlasmaList() {
        return Plasmas;
    }

    public void update(Point spaceship) {
        spaceshipx = spaceship.x;
        spaceshipy = spaceship.y;
    }
}

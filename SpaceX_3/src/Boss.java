
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

public class Boss {

    private double x;
    private double y;
    private int spaceshipx;
    private int spaceshipy;
    private final Timer t;
    private double health;
    private final double multiplier = .5;
    private static ArrayList<Plasma> Plasmas;
    private static ArrayList<Enemy> Enemies;
    private double speed;

    public Boss(Point enemy, int h, double sp) {
        health = h;
        speed = sp;
        Plasmas = new ArrayList();
        x = enemy.x;
        y = enemy.y;
        ActionListener task = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1) {
                //if enemy is alive then shoot
                if (health > 0) {
                    doAction2(e1);
                }
            }

            private void doAction2(ActionEvent e1) {
                Plasmas.add(new Plasma(new Point((int) x, (int) y), new Point(spaceshipx, spaceshipy), sp, 41));
            }
        };

        t = new Timer(50, task);
        t.start();

    }

    public void Move(Point enemy, Point spaceship) {
        //moves the enemy spaceship but also remembers the player's spaceship
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

    //updates player's spaceship cords for plasmas **can't just do it in the move
    //method or else if enemy can't move it won't update its cords
    public void update(Point spaceship) {
        spaceshipx = spaceship.x;
        spaceshipy = spaceship.y;
    }
}

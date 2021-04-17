
public class LaserExplosion {

    private int x;
    private int y;
    private int count = 0;

    public LaserExplosion(int x1, int y1) {
        //laser explosion will be 10x10
        x = x1 - 5;
        y = y1 - 5;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getCount() {
        return count;
    }

    public void update() {
        count++;
    }
}

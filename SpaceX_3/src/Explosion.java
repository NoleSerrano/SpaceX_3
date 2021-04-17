
public class Explosion {

    private int count;
    private int x;
    private int y;

    public Explosion(Enemy E) {
        //so explosion will be 40x40
        x = E.getX() - 5;
        y = E.getY() - 5;
        count = 0;
    }

    //sees how long explosion is for
    public void incCount() {
        count++;
    }

    public int getCount() {
        return count;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

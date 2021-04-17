
public class BossExplosion {

    private int x;
    private int y;
    private int count;

    public BossExplosion(Boss B) {
        //bExplosion will be 120x120F
        x = B.getX() - 10;
        y = B.getY() - 10;
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

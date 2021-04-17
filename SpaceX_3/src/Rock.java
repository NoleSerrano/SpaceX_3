
import java.awt.Image;

public class Rock {

    private int x;
    private int y;
    private Image picture;
    private int size;

    public Rock(int x1, int y1, Image pic) {
        x = x1;
        y = y1;
        picture = pic;
        size = (int) (Math.random() * 20) + 20;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getPic() {
        return picture;
    }

    public int getSize() {
        return size;
    }
}

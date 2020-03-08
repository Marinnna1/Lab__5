import java.util.Objects;

/**
 * This class used for setting and getting coordinates from objects of MusicBand class
 */
public class Coordinates implements Comparable<Coordinates> {
    private Double x; //Поле не может быть null
    private Integer y; //Поле не может быть null

    public Coordinates(double x, Integer y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

   @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return x.equals(that.x) &&
                y.equals(that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    @Override
    public int compareTo(Coordinates o) {
        int result =this.x.compareTo(o.x);
        if (result==0){
            result = this.y.compareTo(o.y);
       }
       return result;
    }
}

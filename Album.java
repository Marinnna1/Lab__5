/**
* This class used for creating objects and sorting it.
 * Objects of this class are used for field bestAlbum in MusicBand class
 */
public class Album implements Comparable<Album> {
    private String name; //Строка не может быть пустой, Поле не может быть null
    private Long tracks;
    private Long length; //Значение поля должно быть больше 0

    public Album(String name, Long tracks, Long length) {
        this.name = name;
        this.tracks = tracks;
        this.length = length;
    }

    @Override
    public String toString() {
        return "Album{" +
                "name=" + name + '\"' +
                ", tracks=" + length + '\"' +
                ", length=" + tracks +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTracks() {
        return tracks;
    }

    public void setTracks(Long tracks) {
        this.tracks = tracks;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    @Override
    public int compareTo(Album o) {
        int result = this.name.compareTo(o.name);
        if (result == 0){
            result = (int) (this.length-o.length);
        }
        return result;
    }
}

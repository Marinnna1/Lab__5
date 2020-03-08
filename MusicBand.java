import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.time.LocalDateTime;

/**
 *This class used for creating collection of objects and sorting it
 */
public class MusicBand implements Comparable<MusicBand> {
    private static int globalID = 0;
    private int id; //Значение этого поля должно быть уникальным, Значение поля должно быть больше 0, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDateTime creationDate; //Значение этого поля должно генерироваться автоматически, Поле не может быть null
    private Long numberOfParticipants; //Поле может быть null, Значение поля должно быть больше 0
    private Long singlesCount; //Значение поля должно быть больше 0, Поле не может быть null
    private MusicGenre genre; //Поле не может быть null
    private Album bestAlbum; //Поле не может быть null

    public MusicBand() {
        globalID++;
        this.id = globalID;
        this.creationDate = LocalDateTime.now();
    }

    public MusicBand(String name, Coordinates coordinates, Long numberOfParticipants, Long singlesCount, MusicGenre genre, Album bestAlbum) {
        globalID++;
        this.id = globalID;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDateTime.now();
        this.numberOfParticipants = numberOfParticipants;
        this.singlesCount = singlesCount;
        this.genre = genre;
        this.bestAlbum = bestAlbum;
    }



    @Override
    public String toString() {
        return "MusicBand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates.toString() +
                ", creationDate=" + creationDate +
                ", numberOfParticipants=" + numberOfParticipants +
                ", singlesCount=" + singlesCount +
                ", genre=" + genre +
                ", bestAlbum= " + bestAlbum.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MusicBand musicBand = (MusicBand) o;
        return id == musicBand.id &&
                name.equals(musicBand.name) &&
                coordinates.equals(musicBand.coordinates) &&
                creationDate.equals(musicBand.creationDate) &&
                numberOfParticipants.equals(musicBand.numberOfParticipants) &&
                singlesCount.equals(musicBand.singlesCount) &&
                genre == musicBand.genre &&
                bestAlbum.equals(musicBand.bestAlbum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, numberOfParticipants, singlesCount, genre, bestAlbum);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate( LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Long getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(Long numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    public Long getSinglesCount() {
        return singlesCount;
    }

    public void setSinglesCount(Long singlesCount) {
        this.singlesCount = singlesCount;
    }

    public MusicGenre getGenre() {
        return genre;
    }

    public void setGenre(MusicGenre genre) {
        this.genre = genre;
    }

    public Album getBestAlbum() {
        return bestAlbum;
    }

    public void setBestAlbum(Album bestAlbum) {
        this.bestAlbum = bestAlbum;
    }

    public void update() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите название Группы");
        String name = reader.readLine();
        while (name.trim().equals("")||name==null){
            System.out.println("имя не должно быть пустым или содержать только пробелы. Повторите ввод.");
            name = reader.readLine();
        }
        this.setName(name);

        System.out.println("Введите координаты x и y через запятую");
        Double x = null;
        Integer y = null;
        while (x==null||y==null) {
            try {
                String[] coords = reader.readLine().split(",");
                x = Double.parseDouble(coords[0]);
                y = Integer.parseInt(coords[1]);
            } catch (Exception e) {
                System.out.println("Данные введены некорректно. Повторите ввод.");
            }
        }
        this.setCoordinates( new Coordinates(x,y));

        System.out.println("Введите кол-во участников");
        Long count = null;
        while (count == null ||count <= 0){
            try{
                count = Long.parseLong(reader.readLine());
                if (count <=0){
                    System.out.println("Количество участников должно быть больше нуля. Повторите ввод");
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Данные введены некорректно. Повторите ввод.");
            }
        }
        this.setNumberOfParticipants(count);

        System.out.println("Введите количество синглов группы");
        Long singles = null;
        while (singles==null||singles<=0){
            try{
                singles = Long.parseLong(reader.readLine());
                if (singles<=0){
                    System.out.println("Количество синглов должно быть больше нуля. Повторите ввод.");
                }
            }
            catch (NumberFormatException e){
                System.out.println("Данные введены некорректно. Повторите ввод.");
            }
        }
        this.setSinglesCount(singles);

        System.out.println("Введите жанр. (RAP, PSYCHEDELIC_CLOUD_RAP, POST_ROCK, POST_PUNK, BRIT_POP)");
        MusicGenre mg = null;
        while(mg == null) {
            try {
                String str = reader.readLine();
                str = str.toUpperCase();
                mg = MusicGenre.valueOf(str);
            } catch (IllegalArgumentException e) {
                System.out.println("Введите корректный жанр");
            }
        }
        this.setGenre(mg);

        System.out.println("Введите название лучшего альбома");
        String albumName = reader.readLine();
        while (albumName==null||albumName.trim().equals("")){
            System.out.println("Название альбома не может быть пустым или содержать только пробелы. Повторите ввод.");
            albumName = reader.readLine();
        }

        System.out.println("Введите продолжительность альбома");
        long length = 0;
        while (length<=0){
            try{
                length = Long.parseLong(reader.readLine());
                if (length <= 0) {
                    System.out.println("Продолжительность альбома должна быть больше нуля");
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Продолжительность альбома введена некорректно");
            }
        }
        System.out.println("Введите количество треков группы");
        long tracks = 0;
        while (tracks <=0){
            try{
                tracks = Long.parseLong(reader.readLine());
                if (tracks <= 0) {
                    System.out.println("Количество треков должно быть больше нуля");
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Количество треков альбома введена некорректно");
            }
        }

        Album album = new Album(albumName,tracks, length);
        this.setBestAlbum(album);
    }
    //Не учитываем id и creationDate т.к. они генерируются автоматически
    public int compareTo(MusicBand o) {
        int result = this.name.compareTo(o.name);
        if (result==0){
            result = this.coordinates.compareTo(o.coordinates);
        }
        if (result == 0){
            result = this.numberOfParticipants.compareTo(o.numberOfParticipants);
        }
        if (result == 0){
            result = this.singlesCount.compareTo(o.singlesCount);
        }
        if (result == 0){
            result = this.bestAlbum.compareTo(o.bestAlbum);
        }
        return result;
    }

    public void changeAll(MusicBand musicBand){
        this.name = musicBand.name;
        this.coordinates = musicBand.coordinates;
        this.creationDate = musicBand.creationDate;
        this.numberOfParticipants = musicBand.numberOfParticipants;
        this.singlesCount = musicBand.singlesCount;
        this.genre = musicBand.genre;
        this.bestAlbum = musicBand.bestAlbum;
    }
}
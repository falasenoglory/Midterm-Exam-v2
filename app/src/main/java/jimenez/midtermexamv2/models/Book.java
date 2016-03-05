package jimenez.midtermexamv2.models;

/**
 * Created by Shanyl Jimenez on 2/23/2016.
 */
public class Book {
    private String title;
    private String genre;
    private String author;
    private boolean isRead;

    public Book( String title, String genre, String author, boolean isRead) {

        this.title = title;
        this.genre = genre;
        this.author = author;
        this.isRead = isRead;
    }

    public Book() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }
}

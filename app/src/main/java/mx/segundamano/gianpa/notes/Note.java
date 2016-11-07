package mx.segundamano.gianpa.notes;

public class Note {
    public String id;
    public String title;
    public String body;

    public Note() {
    }

    public Note(String id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }
}

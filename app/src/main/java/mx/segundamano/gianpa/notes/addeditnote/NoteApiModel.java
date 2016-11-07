package mx.segundamano.gianpa.notes.addeditnote;

import mx.segundamano.gianpa.notes.Note;

public class NoteApiModel {
    public String title;
    public String body;

    public NoteApiModel() {
    }

    public NoteApiModel(String title, String body) {
        this.title = title;
        this.body = body;
    }
}

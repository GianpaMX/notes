package mx.segundamano.gianpa.notes;

import android.text.Editable;

public class NoteViewModel {
    public String id;
    public CharSequence title;
    public CharSequence body;

    public Note toModel() {
        Note note = new Note();

        note.id = id;
        note.title = title.toString();
        note.body = body.toString();

        return note;
    }

    public void copyFromModel(Note note) {
        this.id = note.id;
        this.title = note.title;
        this.body = note.body;
    }
}

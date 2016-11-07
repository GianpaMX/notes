package mx.segundamano.gianpa.notes;

import android.text.Editable;

public class NoteViewModel {
    public CharSequence title;
    public CharSequence body;

    public Note toModel() {
        Note note = new Note();

        note.title = title.toString();
        note.body = body.toString();

        return note;
    }
}

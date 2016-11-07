package mx.segundamano.gianpa.notes.addeditnote;

import mx.segundamano.gianpa.notes.Note;

public interface AddEditNoteView {
    void onNoteSaved(Note note);

    void onFailure(Throwable e);
}

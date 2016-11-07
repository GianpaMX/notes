package mx.segundamano.gianpa.notes.addeditnote;

import mx.segundamano.gianpa.notes.Note;

public interface AddEditNoteModelCallback {
    void onSuccess(Note note);
    void onError(Throwable e);
}

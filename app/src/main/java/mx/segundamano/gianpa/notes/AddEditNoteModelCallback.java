package mx.segundamano.gianpa.notes;

public interface AddEditNoteModelCallback {
    void onSuccess(Note note);
    void onError(Throwable e);
}

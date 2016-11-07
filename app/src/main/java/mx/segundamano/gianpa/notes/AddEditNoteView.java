package mx.segundamano.gianpa.notes;

public interface AddEditNoteView {
    void onNoteSaved(Note note);

    void onFailure(Throwable e);
}

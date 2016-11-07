package mx.segundamano.gianpa.notes.addeditnote;

import mx.segundamano.gianpa.notes.Note;

public class AddEditNotePresenter implements AddEditNoteModelCallback {
    private AddEditNoteView view;
    private AddEditNoteModel model;

    public AddEditNotePresenter(AddEditNoteView view, AddEditNoteModel model) {
        this.view = view;
        this.model = model;
    }

    public void save(Note note) {
        model.save(note, this);
    }

    @Override
    public void onSuccess(Note note) {
        view.onNoteSaved(note);
    }

    @Override
    public void onError(Throwable e) {
        view.onFailure(e);
    }
}

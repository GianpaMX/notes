package mx.segundamano.gianpa.notes;

public class AddEditNotePresenter {
    private AddEditNoteView view;
    private AddEditNoteModel model;

    public AddEditNotePresenter(AddEditNoteView view) {
        this.model = new AddEditNoteModel();
        this.view = view;
    }

    public void save(Note note) {
        model.save(note, new AddEditNoteModelCallback() {
            @Override
            public void onSuccess(Note note) {
                view.onNoteSaved(note);
            }

            @Override
            public void onError(Throwable e) {
                view.onFailure(e);
            }
        });
    }
}

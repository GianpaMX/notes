package mx.segundamano.gianpa.notes.addeditnote.di;

import dagger.Module;
import dagger.Provides;
import mx.segundamano.gianpa.notes.addeditnote.AddEditNoteActivity;
import mx.segundamano.gianpa.notes.addeditnote.AddEditNoteModel;
import mx.segundamano.gianpa.notes.addeditnote.AddEditNotePresenter;
import mx.segundamano.gianpa.notes.addeditnote.AddEditNoteView;

@Module
public class AddEditNoteActivityModule {
    private AddEditNoteActivity activity;

    public AddEditNoteActivityModule(AddEditNoteActivity activity) {
        this.activity = activity;
    }

    @Provides
    public AddEditNoteView provideAddEditNoteView() {
        return activity;
    }

    @Provides
    public AddEditNotePresenter provideAddEditNotePresenter(AddEditNoteView view, AddEditNoteModel model) {
        return new AddEditNotePresenter(view, model);
    }
}

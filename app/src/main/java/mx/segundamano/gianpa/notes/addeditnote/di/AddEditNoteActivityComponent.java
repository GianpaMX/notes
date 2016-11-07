package mx.segundamano.gianpa.notes.addeditnote.di;

import dagger.Subcomponent;
import mx.segundamano.gianpa.notes.addeditnote.AddEditNoteActivity;
import mx.segundamano.gianpa.notes.addeditnote.di.AddEditNoteActivityModule;
import mx.segundamano.gianpa.notes.di.ActivityScope;

@ActivityScope
@Subcomponent(modules = {AddEditNoteActivityModule.class})
public interface AddEditNoteActivityComponent {
    void inject(AddEditNoteActivity activity);
}

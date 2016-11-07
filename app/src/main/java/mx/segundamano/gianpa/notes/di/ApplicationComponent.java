package mx.segundamano.gianpa.notes.di;

import dagger.Component;
import mx.segundamano.gianpa.notes.addeditnote.AddEditNoteActivity;

@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    void inject(AddEditNoteActivity activity);
}

package mx.segundamano.gianpa.notes.di;

import javax.inject.Singleton;

import dagger.Component;
import mx.segundamano.gianpa.notes.addeditnote.AddEditNoteActivity;

@Component(modules = {ApplicationModule.class})
@Singleton
public interface ApplicationComponent {
    void inject(AddEditNoteActivity activity);
}

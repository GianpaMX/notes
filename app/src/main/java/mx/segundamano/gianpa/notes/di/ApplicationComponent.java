package mx.segundamano.gianpa.notes.di;

import javax.inject.Singleton;

import dagger.Component;
import mx.segundamano.gianpa.notes.addeditnote.di.AddEditNoteActivityComponent;
import mx.segundamano.gianpa.notes.addeditnote.di.AddEditNoteActivityModule;

@Component(modules = {ApplicationModule.class})
@Singleton
public interface ApplicationComponent {
    AddEditNoteActivityComponent getAddEditNoteActivityComponent(AddEditNoteActivityModule module);
}

package mx.segundamano.gianpa.notes.listnotes.di;

import dagger.Subcomponent;
import mx.segundamano.gianpa.notes.di.ActivityScope;
import mx.segundamano.gianpa.notes.listnotes.ListNotesActivity;

@ActivityScope
@Subcomponent(modules = {ListNotesActivityModule.class})
public interface ListNotesActivityComponent {
    void inject(ListNotesActivity activity);
}

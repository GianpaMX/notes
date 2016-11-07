package mx.segundamano.gianpa.notes.listnotes.di;

import dagger.Module;
import dagger.Provides;
import mx.segundamano.gianpa.notes.listnotes.ListNotesActivity;
import mx.segundamano.gianpa.notes.listnotes.ListNotesAdapter;
import mx.segundamano.gianpa.notes.listnotes.ListNotesModel;
import mx.segundamano.gianpa.notes.listnotes.ListNotesPresenter;
import mx.segundamano.gianpa.notes.listnotes.ListNotesView;

@Module
public class ListNotesActivityModule {
    private ListNotesActivity activity;

    public ListNotesActivityModule(ListNotesActivity activity) {
        this.activity = activity;
    }

    @Provides
    public ListNotesView provideListNotesView() {
        return activity;
    }

    @Provides
    public ListNotesPresenter provideListNotesPresenter(ListNotesView view, ListNotesModel model) {
        return new ListNotesPresenter(view, model);
    }

    @Provides
    public ListNotesAdapter provideListNotesAdapter() {
        return new ListNotesAdapter(activity);
    }
}

package mx.segundamano.gianpa.notes.listnotes;

import java.util.ArrayList;
import java.util.List;

import mx.segundamano.gianpa.notes.Note;
import mx.segundamano.gianpa.notes.NoteViewModel;

public class ListNotesPresenter implements ListNotesModelChangeListener {
    private ListNotesView view;
    private ListNotesModel model;

    public ListNotesPresenter(ListNotesView view, ListNotesModel model) {
        this.view = view;
        this.model = model;
    }

    public void start() {
        model.setListener(this);
    }

    public void stop() {
        model.removeListener();
    }

    @Override
    public void onListNotesModelChange(List<Note> notes) {
        List<NoteViewModel> noteViewModelList = new ArrayList<>();
        for (Note note : notes) {
            NoteViewModel noteViewModel = new NoteViewModel();
            noteViewModel.copyFromModel(note);
            noteViewModelList.add(noteViewModel);
        }
        view.onNotesLoaded(noteViewModelList);
    }
}

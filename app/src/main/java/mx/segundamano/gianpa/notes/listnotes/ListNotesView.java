package mx.segundamano.gianpa.notes.listnotes;

import java.util.List;

import mx.segundamano.gianpa.notes.NoteViewModel;

public interface ListNotesView {
    void onNotesLoaded(List<NoteViewModel> noteViewModelList);
}

package mx.segundamano.gianpa.notes.listnotes;

import java.util.List;

import mx.segundamano.gianpa.notes.Note;

public interface ListNotesModelChangeListener {
    void onListNotesModelChange(List<Note> notes);
}

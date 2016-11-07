package mx.segundamano.gianpa.notes;

import mx.segundamano.gianpa.notes.addeditnote.AddEditNoteModelCallback;

public interface NotesGateway {
    void create(String title, String body, GatewayCallback<Note> callback);
    void update(String id, String title, String body, GatewayCallback<Note> callback);
}

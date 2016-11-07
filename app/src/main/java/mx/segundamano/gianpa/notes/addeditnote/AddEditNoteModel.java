package mx.segundamano.gianpa.notes.addeditnote;

import android.support.annotation.NonNull;

import mx.segundamano.gianpa.notes.GatewayCallback;
import mx.segundamano.gianpa.notes.Note;
import mx.segundamano.gianpa.notes.NotesGateway;

public class AddEditNoteModel {
    private NotesGateway gateway;

    public AddEditNoteModel(NotesGateway gateway) {
        this.gateway = gateway;
    }

    public void save(Note note, final AddEditNoteModelCallback addEditNoteModelCallback) {
        GatewayCallback<Note> gatewayCallback = newGatewayCallback(addEditNoteModelCallback);

        if (note.id == null) {
            gateway.create(note.title, note.body, gatewayCallback);
        } else {
            gateway.update(note.id, note.title, note.body, gatewayCallback);
        }
    }

    @NonNull
    GatewayCallback<Note> newGatewayCallback(final AddEditNoteModelCallback addEditNoteModelCallback) {
        return new GatewayCallback<Note>() {
            @Override
            public void onSuccess(Note note) {
                addEditNoteModelCallback.onSuccess(note);
            }

            @Override
            public void onError(Throwable e) {
                addEditNoteModelCallback.onError(e);
            }
        };
    }
}

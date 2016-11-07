package mx.segundamano.gianpa.notes.addeditnote;

import io.realm.Realm;
import mx.segundamano.gianpa.notes.GatewayCallback;
import mx.segundamano.gianpa.notes.Note;
import mx.segundamano.gianpa.notes.NotesGateway;
import mx.segundamano.gianpa.notes.NotesRepository;
import mx.segundamano.gianpa.notes.NotesService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddEditNoteModel {
    private NotesGateway gateway;

    public AddEditNoteModel() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.myjson.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NotesService service = retrofit.create(NotesService.class);
        Realm realm = Realm.getDefaultInstance();

        gateway = new NotesRepository(service, realm);
    }

    public void save(Note note, final AddEditNoteModelCallback addEditNoteModelCallback) {
        GatewayCallback<Note> gatewayCallback = new GatewayCallback<Note>() {
            @Override
            public void onSuccess(Note note) {
                addEditNoteModelCallback.onSuccess(note);
            }

            @Override
            public void onError(Throwable e) {
                addEditNoteModelCallback.onError(e);
            }
        };

        if (note.id == null) {
            gateway.create(note.title, note.body, gatewayCallback);
        } else {
            gateway.update(note.id, note.title, note.body, gatewayCallback);
        }
    }
}

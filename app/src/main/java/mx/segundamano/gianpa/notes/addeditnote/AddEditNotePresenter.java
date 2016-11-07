package mx.segundamano.gianpa.notes.addeditnote;

import io.realm.Realm;
import mx.segundamano.gianpa.notes.Note;
import mx.segundamano.gianpa.notes.NotesRepository;
import mx.segundamano.gianpa.notes.NotesService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddEditNotePresenter implements AddEditNoteModelCallback {
    private AddEditNoteView view;
    private AddEditNoteModel model;

    public AddEditNotePresenter(AddEditNoteView view) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.myjson.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NotesService service = retrofit.create(NotesService.class);
        Realm realm = Realm.getDefaultInstance();
        NotesRepository gateway = new NotesRepository(service, realm);

        this.model = new AddEditNoteModel(gateway);
        this.view = view;
    }

    public void save(Note note) {
        model.save(note, this);
    }

    @Override
    public void onSuccess(Note note) {
        view.onNoteSaved(note);
    }

    @Override
    public void onError(Throwable e) {
        view.onFailure(e);
    }
}

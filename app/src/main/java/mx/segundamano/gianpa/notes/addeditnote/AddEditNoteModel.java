package mx.segundamano.gianpa.notes.addeditnote;

import android.net.Uri;

import io.realm.Realm;
import mx.segundamano.gianpa.notes.Note;
import mx.segundamano.gianpa.notes.NoteRealmModel;
import mx.segundamano.gianpa.notes.NotesService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddEditNoteModel {

    private final NotesService service;
    private Realm realm;

    public AddEditNoteModel() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.myjson.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(NotesService.class);

        realm = Realm.getDefaultInstance();
    }

    public void save(final Note note, final AddEditNoteModelCallback callback) {
        NoteApiModel noteApiModel = new NoteApiModel();
        noteApiModel.title = note.title;
        noteApiModel.body = note.body;

        Call<MyJsonResponse> call = service.create(noteApiModel);
        call.enqueue(new Callback<MyJsonResponse>() {
            @Override
            public void onResponse(final Call<MyJsonResponse> call, Response<MyJsonResponse> response) {
                if (response.code() != 201) {
                    callback.onError(new Exception(response.message()));
                    return;
                }

                Uri uri = Uri.parse(response.body().uri);
                note.id = uri.getLastPathSegment();

                realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        NoteRealmModel noteRealmModel = realm.createObject(NoteRealmModel.class);
                        noteRealmModel.id = note.id;
                        noteRealmModel.title = note.title;
                        noteRealmModel.body = note.body;
                    }
                }, new Realm.Transaction.OnSuccess() {
                    @Override
                    public void onSuccess() {
                        callback.onSuccess(note);
                    }
                }, new Realm.Transaction.OnError() {
                    @Override
                    public void onError(Throwable error) {
                        callback.onError(error);
                    }
                });
            }

            @Override
            public void onFailure(Call<MyJsonResponse> call, Throwable t) {
                callback.onError(t);
            }
        });
    }
}

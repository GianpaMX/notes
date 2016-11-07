package mx.segundamano.gianpa.notes;

import android.net.Uri;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddEditNoteModel {

    private final NotesService service;

    public AddEditNoteModel() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.myjson.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(NotesService.class);
    }

    public void save(final Note note, final AddEditNoteModelCallback callback) {
        NoteApiModel noteApiModel = new NoteApiModel();
        noteApiModel.title = note.title;
        noteApiModel.body = note.body;

        Call<MyJsonResponse> call = service.create(noteApiModel);
        call.enqueue(new Callback<MyJsonResponse>() {
            @Override
            public void onResponse(Call<MyJsonResponse> call, Response<MyJsonResponse> response) {
                if (response.code() != 201) {
                    callback.onError(new Exception(response.message()));
                    return;
                }

                Uri uri = Uri.parse(response.body().uri);
                note.id = uri.getLastPathSegment();

                callback.onSuccess(note);
            }

            @Override
            public void onFailure(Call<MyJsonResponse> call, Throwable t) {
                callback.onError(t);
            }
        });
    }
}

package mx.segundamano.gianpa.notes;

import mx.segundamano.gianpa.notes.addeditnote.MyJsonResponse;
import mx.segundamano.gianpa.notes.addeditnote.NoteApiModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface NotesService {
    @POST("bins")
    Call<MyJsonResponse> create(@Body NoteApiModel note);

    @PUT("bins/{id}")
    Call<NoteApiModel> update(@Path("id") String id, @Body NoteApiModel noteApiModel);
}

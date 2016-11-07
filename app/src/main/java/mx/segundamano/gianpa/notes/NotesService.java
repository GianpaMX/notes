package mx.segundamano.gianpa.notes;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface NotesService {
    @POST("bins")
    Call<MyJsonResponse> create(@Body NoteApiModel note);
}

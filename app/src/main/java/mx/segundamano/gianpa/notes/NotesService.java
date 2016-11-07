package mx.segundamano.gianpa.notes;

import mx.segundamano.gianpa.notes.addeditnote.MyJsonResponse;
import mx.segundamano.gianpa.notes.addeditnote.NoteApiModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface NotesService {
    @POST("bins")
    Call<MyJsonResponse> create(@Body NoteApiModel note);
}

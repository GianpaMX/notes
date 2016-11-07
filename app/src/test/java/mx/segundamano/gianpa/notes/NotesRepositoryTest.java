package mx.segundamano.gianpa.notes;

import org.junit.Before;
import org.junit.Test;

import io.realm.Realm;
import io.realm.RealmAsyncTask;
import mx.segundamano.gianpa.notes.addeditnote.MyJsonResponse;
import mx.segundamano.gianpa.notes.addeditnote.NoteApiModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class NotesRepositoryTest {

    private NotesService service;
    private Realm realm;
    private NotesRepository notesRepository;
    private Response<MyJsonResponse> response;
    private GatewayCallback gatewayCallback;
    private Call call;

    @Before
    public void setUp() throws Exception {
        service = mock(NotesService.class);
        realm = mock(Realm.class);

        notesRepository = new NotesRepository(service, realm);
        response = mock(Response.class);
        gatewayCallback = mock(GatewayCallback.class);
        call = mock(Call.class);
    }

    @Test
    public void create() throws Exception {
        when(service.create(any(NoteApiModel.class))).thenReturn(call);

        notesRepository.create("title", "body", mock(GatewayCallback.class));

        verify(call).enqueue(any(Callback.class));
    }

    @Test
    public void update() throws Exception {
        when(service.update(anyString(), any(NoteApiModel.class))).thenReturn(call);

        notesRepository.update("id", "title", "body", mock(GatewayCallback.class));

        verify(call).enqueue(any(Callback.class));
    }

    @Test
    public void onCreateSuccesfulRetrofitResponse() {
        MyJsonResponse myJsonResponse = mock(MyJsonResponse.class);
        myJsonResponse.uri = "https://api.myjson.com/bins/id";
        when(response.isSuccessful()).thenReturn(true);
        when(response.body()).thenReturn(myJsonResponse);
        NotesRepository notesRepository = spy(this.notesRepository);
        doNothing().when(notesRepository).persist(anyString(), anyString(), anyString(), any(GatewayCallback.class));

        notesRepository.onCreateRetrofitResponse(response, gatewayCallback, "title", "body");


        verify(notesRepository).persist(eq("id"), eq("title"), eq("body"), any(GatewayCallback.class));
    }

    @Test
    public void onCreateFailureRetrofitResponse() {
        when(response.isSuccessful()).thenReturn(false);

        notesRepository.onCreateRetrofitResponse(mock(Response.class), gatewayCallback, "title", "body");

        verify(gatewayCallback).onError(any(Exception.class));
    }
}
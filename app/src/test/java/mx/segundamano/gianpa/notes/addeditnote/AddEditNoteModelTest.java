package mx.segundamano.gianpa.notes.addeditnote;

import org.junit.Before;
import org.junit.Test;

import mx.segundamano.gianpa.notes.GatewayCallback;
import mx.segundamano.gianpa.notes.Note;
import mx.segundamano.gianpa.notes.NotesGateway;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AddEditNoteModelTest {

    private NotesGateway gateway;
    private AddEditNoteModel addEditNoteModel;

    @Before
    public void setUp() throws Exception {
        gateway = mock(NotesGateway.class);
        addEditNoteModel = new AddEditNoteModel(gateway);
    }

    @Test
    public void save() {
        addEditNoteModel.save(new Note(null, "title", "body"), mock(AddEditNoteModelCallback.class));

        verify(gateway).create(anyString(), anyString(), any(GatewayCallback.class));
    }

    @Test
    public void newGatewaySuccessCallback() {
        Note expected = new Note();
        AddEditNoteModelCallback addEditNoteModelCallback = mock(AddEditNoteModelCallback.class);
        GatewayCallback<Note> noteGatewayCallback = addEditNoteModel.newGatewayCallback(addEditNoteModelCallback);

        noteGatewayCallback.onSuccess(expected);

        verify(addEditNoteModelCallback).onSuccess(expected);
    }

    @Test
    public void newGatewayErrorCallback() {
        Throwable expected = new Throwable();
        AddEditNoteModelCallback addEditNoteModelCallback = mock(AddEditNoteModelCallback.class);
        GatewayCallback<Note> noteGatewayCallback = addEditNoteModel.newGatewayCallback(addEditNoteModelCallback);

        noteGatewayCallback.onError(expected);

        verify(addEditNoteModelCallback).onError(expected);
    }
}

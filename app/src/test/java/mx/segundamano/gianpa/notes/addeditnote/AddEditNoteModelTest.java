package mx.segundamano.gianpa.notes.addeditnote;

import org.junit.Test;

import mx.segundamano.gianpa.notes.GatewayCallback;
import mx.segundamano.gianpa.notes.Note;
import mx.segundamano.gianpa.notes.NotesGateway;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AddEditNoteModelTest {
    @Test
    public void save() {
        NotesGateway gateway = mock(NotesGateway.class);
        AddEditNoteModel addEditNoteModel = new AddEditNoteModel(gateway);

        addEditNoteModel.save(new Note(null, "title", "body"), mock(AddEditNoteModelCallback.class));

        verify(gateway).create(anyString(), anyString(), any(GatewayCallback.class));
    }
}

package mx.segundamano.gianpa.notes.addeditnote;

import org.junit.Before;
import org.junit.Test;

import mx.segundamano.gianpa.notes.Note;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AddEditNotePresenterTest {

    private AddEditNoteView view;
    private AddEditNoteModel model;
    private AddEditNotePresenter addEditNotePresenter;

    @Before
    public void setUp() throws Exception {
        view = mock(AddEditNoteView.class);
        model = mock(AddEditNoteModel.class);
        addEditNotePresenter = new AddEditNotePresenter(view, model);
    }

    @Test
    public void save() throws Exception {
        Note expected = new Note();

        addEditNotePresenter.save(expected);

        verify(model).save(eq(expected), any(AddEditNoteModelCallback.class));
    }

    @Test
    public void onSuccess() throws Exception {
        Note expected = new Note();

        addEditNotePresenter.onSuccess(expected);

        verify(view).onNoteSaved(eq(expected));
    }

    @Test
    public void onError() throws Exception {
        Throwable expected = new Throwable();

        addEditNotePresenter.onError(expected);

        verify(view).onFailure(eq(expected));
    }
}
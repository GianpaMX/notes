package mx.segundamano.gianpa.notes;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

public class AddEditNoteActivity extends AppCompatActivity implements AddEditNoteView {

    private EditText titleEditText;
    private EditText bodyEditText;

    private AddEditNotePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_edit_note_activity);

        titleEditText = (EditText) findViewById(R.id.title_edit_text);
        bodyEditText = (EditText) findViewById(R.id.body_edit_text);

        presenter = new AddEditNotePresenter(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_edit_note_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save_action) {
            NoteViewModel note = new NoteViewModel();
            note.title = titleEditText.getText();
            note.body = bodyEditText.getText();

            presenter.save(note.toModel());

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onNoteSaved(Note note) {
        Snackbar.make(findViewById(android.R.id.content), note.id, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onFailure(Throwable e) {
        Snackbar.make(findViewById(android.R.id.content), e.getMessage(), Snackbar.LENGTH_LONG).show();
    }
}

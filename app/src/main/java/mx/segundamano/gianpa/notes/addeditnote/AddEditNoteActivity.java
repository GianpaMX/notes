package mx.segundamano.gianpa.notes.addeditnote;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import io.realm.Realm;
import mx.segundamano.gianpa.notes.Note;
import mx.segundamano.gianpa.notes.NoteViewModel;
import mx.segundamano.gianpa.notes.NotesRepository;
import mx.segundamano.gianpa.notes.NotesService;
import mx.segundamano.gianpa.notes.R;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddEditNoteActivity extends AppCompatActivity implements AddEditNoteView {
    public static final String NOTE_VIEW_MODEL = "NOTE_VIEW_MODEL";

    private EditText titleEditText;
    private EditText bodyEditText;

    private AddEditNotePresenter presenter;
    private NoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_edit_note_activity);

        titleEditText = (EditText) findViewById(R.id.title_edit_text);
        bodyEditText = (EditText) findViewById(R.id.body_edit_text);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.myjson.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NotesService service = retrofit.create(NotesService.class);
        Realm realm = Realm.getDefaultInstance();
        NotesRepository gateway = new NotesRepository(service, realm);
        AddEditNoteModel model = new AddEditNoteModel(gateway);

        presenter = new AddEditNotePresenter(this, model);

        if (savedInstanceState != null) {
            noteViewModel = savedInstanceState.getParcelable(NOTE_VIEW_MODEL);
        } else if (getIntent().getExtras() != null) {
            noteViewModel = getIntent().getExtras().getParcelable(NOTE_VIEW_MODEL);
        } else {
            noteViewModel = new NoteViewModel();
        }

        titleEditText.setText(noteViewModel.title);
        bodyEditText.setText(noteViewModel.body);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        noteViewModel.title = titleEditText.getText().toString();
        noteViewModel.body = bodyEditText.getText().toString();

        outState.putParcelable(NOTE_VIEW_MODEL, noteViewModel);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_edit_note_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save_action) {
            noteViewModel.title = titleEditText.getText().toString();
            noteViewModel.body = bodyEditText.getText().toString();

            presenter.save(noteViewModel.toModel());

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onNoteSaved(Note note) {
        finish();
    }

    @Override
    public void onFailure(Throwable e) {
        Snackbar.make(findViewById(android.R.id.content), e.getMessage(), Snackbar.LENGTH_LONG).show();
    }
}

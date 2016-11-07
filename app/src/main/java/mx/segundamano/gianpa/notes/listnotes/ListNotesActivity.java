package mx.segundamano.gianpa.notes.listnotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import mx.segundamano.gianpa.notes.NoteViewModel;
import mx.segundamano.gianpa.notes.NotesApplication;
import mx.segundamano.gianpa.notes.R;
import mx.segundamano.gianpa.notes.addeditnote.AddEditNoteActivity;
import mx.segundamano.gianpa.notes.listnotes.di.ListNotesActivityModule;

public class ListNotesActivity extends AppCompatActivity implements ListNotesView {

    private RecyclerView notesRecyclerView;
    private ListNotesAdapter adapter;

    @Inject
    public ListNotesPresenter presenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_notes_activity);

        adapter = new ListNotesAdapter(this);

        notesRecyclerView = (RecyclerView) findViewById(R.id.notes_recycler_view);
        notesRecyclerView.setHasFixedSize(true);
        notesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        notesRecyclerView.setAdapter(adapter);

        inject(this);
    }

    private void inject(ListNotesActivity listNotesActivity) {
        ((NotesApplication) getApplication()).getApplicationComponent()
                .getListNotesActivityComponent(new ListNotesActivityModule(listNotesActivity))
                .inject(listNotesActivity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.list_notes_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_action) {
            Intent addEditNoteActivity = new Intent(this, AddEditNoteActivity.class);
            startActivity(addEditNoteActivity);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.stop();
    }

    @Override
    public void onNotesLoaded(List<NoteViewModel> noteViewModelList) {
        adapter.replace(noteViewModelList);
    }
}

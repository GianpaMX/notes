package mx.segundamano.gianpa.notes.listnotes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import mx.segundamano.gianpa.notes.R;

public class ListNotesActivity extends AppCompatActivity {

    private RecyclerView notesRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_notes_activity);

        notesRecyclerView = (RecyclerView) findViewById(R.id.notes_recycler_view);
        notesRecyclerView.setHasFixedSize(true);
        notesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}

package mx.segundamano.gianpa.notes.listnotes;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import mx.segundamano.gianpa.notes.Note;
import mx.segundamano.gianpa.notes.NoteRealmModel;

public class ListNotesModel {
    private final Realm realm;
    private ListNotesModelChangeListener listener;
    private RealmResults<NoteRealmModel> realmResults;

    public ListNotesModel(Realm realm) {
        this.realm = realm;
    }

    public void setListener(ListNotesModelChangeListener listener) {
        this.listener = listener;
        realmResults = realm.where(NoteRealmModel.class).findAllAsync();
        realmResults.addChangeListener(realmChangeListener);
    }

    public void removeListener() {
        realmResults.removeChangeListener(realmChangeListener);
        realmResults = null;
        this.listener = null;
    }

    private RealmChangeListener<RealmResults<NoteRealmModel>> realmChangeListener = new RealmChangeListener<RealmResults<NoteRealmModel>>() {
        @Override
        public void onChange(RealmResults<NoteRealmModel> realmNotes) {
            if (realmResults.isLoaded()) {
                List<Note> notes = new ArrayList<>();

                for (NoteRealmModel noteRealmModel : realmNotes) {
                    Note note = new Note();
                    note.id = noteRealmModel.id;
                    note.title = noteRealmModel.title;
                    note.body = noteRealmModel.body;

                    notes.add(note);
                }

                listener.onListNotesModelChange(notes);
            }
        }
    };
}

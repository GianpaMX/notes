package mx.segundamano.gianpa.notes;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class NoteRealmModel extends RealmObject {
    @PrimaryKey
    public String id;
    public String title;
    public String body;

    public NoteRealmModel() {
    }

    public NoteRealmModel(String id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public void copyFrom(Note note) {
        id = note.id;
        title = note.title;
        body = note.body;
    }
}

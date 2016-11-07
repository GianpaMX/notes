package mx.segundamano.gianpa.notes;

import io.realm.RealmObject;


public class NoteRealmModel extends RealmObject {
    public String id;
    public String title;
    public String body;
}

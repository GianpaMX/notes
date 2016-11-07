package mx.segundamano.gianpa.notes;

import android.os.Parcel;
import android.os.Parcelable;

public class NoteViewModel implements Parcelable {
    public String id;
    public String title;
    public String body;

    public NoteViewModel() {
    }

    protected NoteViewModel(Parcel in) {
        id = in.readString();
        title = in.readString();
        body = in.readString();
    }

    public Note toModel() {
        Note note = new Note();

        note.id = id;
        note.title = title;
        note.body = body;

        return note;
    }

    public void copyFromModel(Note note) {
        this.id = note.id;
        this.title = note.title;
        this.body = note.body;
    }

    public static final Creator<NoteViewModel> CREATOR = new Creator<NoteViewModel>() {
        @Override
        public NoteViewModel createFromParcel(Parcel in) {
            return new NoteViewModel(in);
        }

        @Override
        public NoteViewModel[] newArray(int size) {
            return new NoteViewModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeString(body);
    }
}

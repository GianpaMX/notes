package mx.segundamano.gianpa.notes.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import mx.segundamano.gianpa.notes.NotesGateway;
import mx.segundamano.gianpa.notes.NotesRepository;
import mx.segundamano.gianpa.notes.NotesService;
import mx.segundamano.gianpa.notes.addeditnote.AddEditNoteModel;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {
    private Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    public Retrofit provideRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.myjson.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    @Singleton
    @Provides
    public NotesService provideNotesService(Retrofit retrofit) {
        return retrofit.create(NotesService.class);
    }

    @Singleton
    @Provides
    public Realm provideRealm() {
        Realm.init(context);

        return Realm.getDefaultInstance();
    }

    @Singleton
    @Provides
    public NotesGateway provideNotesRepository(NotesService service, Realm realm) {
        return new NotesRepository(service, realm);
    }

    @Singleton
    @Provides
    public AddEditNoteModel provideAddEditNoteModel(NotesGateway gateway) {
        return new AddEditNoteModel(gateway);
    }
}

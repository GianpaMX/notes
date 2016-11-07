package mx.segundamano.gianpa.notes.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import mx.segundamano.gianpa.notes.NotesService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {
    private Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Provides
    public Retrofit provideRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.myjson.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    @Provides
    public NotesService provideNotesService(Retrofit retrofit) {
        return retrofit.create(NotesService.class);
    }
}

package mx.segundamano.gianpa.notes;

import android.app.Application;

import io.realm.Realm;
import mx.segundamano.gianpa.notes.di.ApplicationComponent;
import mx.segundamano.gianpa.notes.di.ApplicationModule;
import mx.segundamano.gianpa.notes.di.DaggerApplicationComponent;


public class NotesApplication extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }

    public ApplicationComponent getApplicationComponent() {
        if (applicationComponent == null) {
            applicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }

        return applicationComponent;
    }
}

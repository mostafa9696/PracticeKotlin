package mostafa.example.com.practicekotlin

import android.app.Application
import mostafa.example.com.practicekotlin.dagger.AppComponent
import mostafa.example.com.practicekotlin.dagger.AppModule
import mostafa.example.com.practicekotlin.dagger.DaggerAppComponent

class MainApplication : Application() {

    companion object {          // companion like static and object for singletone
        lateinit var app: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        app= DaggerAppComponent.builder().appModule(AppModule(this)).build()
        app.inject(this)
    }
}
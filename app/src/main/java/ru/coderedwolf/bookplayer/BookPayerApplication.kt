package ru.coderedwolf.bookplayer

import android.app.Application
import ru.coderedwolf.bookplayer.di.*

class BookPayerApplication : Application() {

    companion object {

        @JvmStatic
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        component = buildComponent()
    }

    private fun buildComponent(): AppComponent {
        return DaggerAppComponent.builder()
            .dataBaseModule(DataBaseModule(this))
            .navigationModule(NavigationModule())
            .build()
    }
}
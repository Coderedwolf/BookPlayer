package ru.coderedwolf.bookplayer

import android.app.Activity
import android.app.Application
import android.app.Service
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasServiceInjector
import ru.coderedwolf.bookplayer.di.ApplicationComponent
import ru.coderedwolf.bookplayer.di.DaggerApplicationComponent
import javax.inject.Inject

class BookPlayerApplication : Application(), HasActivityInjector, HasServiceInjector {

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var dispatchingServiceInjector: DispatchingAndroidInjector<Service>

    override fun onCreate() {
        super.onCreate()
        createAppComponent().inject(this)
    }

    override fun serviceInjector(): AndroidInjector<Service> = dispatchingServiceInjector

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityInjector

    private fun createAppComponent(): ApplicationComponent {
        return DaggerApplicationComponent.builder()
                .context(this)
                .build()
    }
}
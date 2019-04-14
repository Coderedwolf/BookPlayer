package ru.coderedwolf.bookplayer.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import ru.coderedwolf.bookplayer.BookPlayerApplication
import ru.coderedwolf.bookplayer.di.build.ActivityBuilderModule
import ru.coderedwolf.bookplayer.di.build.FragmentBuilderModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AndroidSupportInjectionModule::class,
            FragmentBuilderModule::class,
            ManagersModule::class,
            ActivityBuilderModule::class,
            DataBaseModule::class,
            NavigationModule::class,
            RepositoryModule::class
        ]
)
interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): ApplicationComponent
    }

    fun inject(bookPlayerApplication: BookPlayerApplication)
}
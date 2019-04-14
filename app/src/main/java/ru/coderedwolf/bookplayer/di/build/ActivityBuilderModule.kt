package ru.coderedwolf.bookplayer.di.build

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.coderedwolf.bookplayer.modules.main.MainActivity
import ru.coderedwolf.bookplayer.modules.splash.SplashActivity

@Module
interface ActivityBuilderModule {

    @ContributesAndroidInjector fun contributeConditionsActivity(): MainActivity
    @ContributesAndroidInjector fun contributeSplashActivity(): SplashActivity
}
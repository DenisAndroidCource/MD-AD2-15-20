package by.it.academy.dependencyinjection.di

import by.it.academy.dependencyinjection.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
}
package by.it.academy.dependencyinjection.di

import by.it.academy.dependencyinjection.AllNewsRepoImpl
import by.it.academy.dependencyinjection.GodRepo
import by.it.academy.dependencyinjection.NewsRepo
import by.it.academy.dependencyinjection.NewsRepoImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
abstract class AppModule {

    @Provides
    @Named("provideAllNewsRepo")
    fun provideAllNewsRepo(): AllNewsRepoImpl {
        return AllNewsRepoImpl()
    }

    @Provides
    @Named("provideAllNewsRepo1")
    fun provideAllNewsRepo1(): AllNewsRepoImpl {
        return AllNewsRepoImpl()
    }

    @Provides
    fun provideGodRepo(@Named("provideAllNewsRepo") allNewsRepoImpl: AllNewsRepoImpl): GodRepo {
        return GodRepo(allNewsRepoImpl)
    }
}
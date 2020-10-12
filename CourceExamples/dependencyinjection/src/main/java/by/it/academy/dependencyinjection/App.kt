package by.it.academy.dependencyinjection

import android.app.Application
import by.it.academy.dependencyinjection.di.DaggerAppComponent

class App : Application() {

    val appComponent = DaggerAppComponent.create()

}
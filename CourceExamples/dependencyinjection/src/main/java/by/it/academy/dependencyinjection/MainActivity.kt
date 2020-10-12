package by.it.academy.dependencyinjection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import javax.inject.Inject
import javax.inject.Named

/**
 * @Inject
 * @Component
 */

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var repo: NewsRepo

    @Inject
    @Named("provideAllNewsRepo1")
    lateinit var allrepo: AllNewsRepoImpl

    @Inject
    lateinit var godRepo: GodRepo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (applicationContext as App).appComponent.inject(this)
        repo.getAllNews()
        allrepo.getAllNews()
    }
}

package nuuday.example.ourapp

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import nuuday.example.todo_data.di.TodoDomainModule
import nuuday.example.todo_domain.Todo
import nuuday.example.todo_domain.usecases.AddTodoUsecase
import nuuday.example.todo_domain.usecases.GetTodosUsecase
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import java.util.Date
import java.util.Random
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        initKoin()

        val addTodo: AddTodoUsecase by inject()

        displayTodos()

        fab.setOnClickListener { view ->

            addTodo.build(Todo(Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(Random().nextInt(10).toLong())),java.util.UUID.randomUUID().toString()))
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(
                    {   displayTodos() },
                    { error -> Log.e("THIS", "What", error) }
               )

        }
    }

    fun initKoin(){
        startKoin {
            androidLogger()
            androidContext(this@MainActivity)
            modules(
                listOf(
                    TodoDomainModule.module
                )
            )
        }
    }

    private fun displayTodos() {
        val getTodos: GetTodosUsecase by inject()

        getTodos.build()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {  hello.text = "TODOS:\n" + getTodosInTextForm(it) },
                { error -> Log.e("THIS", "What", error) }
            )

    }


    private fun getTodosInTextForm(todoList: List<Todo>): String = todoList.fold("",
        { current, addition -> current + "\n " + addition.title })

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}

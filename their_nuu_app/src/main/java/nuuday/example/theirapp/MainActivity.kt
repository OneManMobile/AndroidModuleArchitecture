package nuuday.example.theirapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import nuuday.example.nuu_music_sdk.NuuMusicSDK
import nuuday.example.nuu_music_sdk.Todos
import nuuday.example.todo_domain.Todo
import nuuday.example.todo_domain.TodoRepository
import java.util.Date
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    lateinit var todos: Todos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NuuMusicSDK.init(applicationContext)
        todos = Todos()

        todos
            .addTodo.build(Todo(Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1)), "Do laundry"))
            .andThen(Completable.defer {todos.addTodo.build(Todo(Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(2) ), "Take insulin or die"))})
            .andThen(Single.defer { todos.getTodos.build()})
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { it -> hello.text = "TODOS:\n" + getTodosInTextForm(it) },
                { error -> Log.e("THIS", "What", error) }
            )
    }

    private fun getTodosInTextForm(todoList: List<Todo>): String = todoList.fold("",
        { current, addition -> current + "\n " + addition.title })
}

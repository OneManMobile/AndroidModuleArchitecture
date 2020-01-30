package nuuday.example.todo_domain

import io.reactivex.Completable
import io.reactivex.Single

interface TodoRepository{

    fun getTodos(): Single<List<Todo>>
    fun addTodo(item: Todo): Completable
    fun deleteTodo(item: Todo): Completable
}

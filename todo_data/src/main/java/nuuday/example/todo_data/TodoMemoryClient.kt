package nuuday.example.todo_data

import io.reactivex.Completable
import io.reactivex.Single
import nuuday.example.todo_domain.Todo

interface TodoMemoryClient {

    fun getTodos(): Single<List<Todo>>
    fun addTodo(item: Todo): Completable
    fun deleteTodo(item: Todo): Completable

}

package nuuday.example.todo_data

import io.reactivex.Completable
import io.reactivex.Completable.complete
import io.reactivex.Single
import nuuday.example.todo_domain.Todo

class TodoMemoryClientImp : TodoMemoryClient {

    private val todos: MutableList<TodoMemoryModel> = mutableListOf()

    override fun getTodos(): Single<List<Todo>> =
        Single.just(todos.map { TodoMemoryMapper.fromMemoryModel(it) })

    override fun addTodo(item: Todo): Completable = Completable.create {
        todos.add(TodoMemoryMapper.toMemoryModel(item))
        it.onComplete()
    }

    override fun deleteTodo(item: Todo): Completable = Completable.create {
        todos.remove(TodoMemoryMapper.toMemoryModel(item))
        it.onComplete()
    }
}

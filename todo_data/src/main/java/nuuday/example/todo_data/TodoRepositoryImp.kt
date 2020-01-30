package nuuday.example.todo_data

import io.reactivex.Completable
import io.reactivex.Single
import nuuday.example.todo_domain.Todo
import nuuday.example.todo_domain.TodoRepository

class TodoRepositoryImp(
    private val todoMemoryClient: TodoMemoryClient
) : TodoRepository {
    override fun getTodos(): Single<List<Todo>> {
        return todoMemoryClient.getTodos()
    }

    override fun addTodo(item: Todo): Completable {
        return todoMemoryClient.addTodo(item)
    }

    override fun deleteTodo(item: Todo): Completable {
        return todoMemoryClient.deleteTodo(item)
    }
}

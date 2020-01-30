package nuuday.example.todo_domain.usecases

import io.reactivex.Completable
import nuuday.example.todo_domain.Todo
import nuuday.example.todo_domain.TodoRepository
import nuuday.example.todo_domain.usecases.usecase.UseCaseCompletable

class AddTodoUsecase(
    private val repository: TodoRepository
) : UseCaseCompletable<Todo>() {

    override fun build(params: Todo?): Completable = repository.addTodo(params!!)
}

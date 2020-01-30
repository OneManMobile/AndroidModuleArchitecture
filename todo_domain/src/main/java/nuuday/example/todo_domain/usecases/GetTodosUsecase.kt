package nuuday.example.todo_domain.usecases

import io.reactivex.Single
import nuuday.example.todo_domain.Todo
import nuuday.example.todo_domain.TodoRepository
import nuuday.example.todo_domain.usecases.usecase.UseCaseSingle

class GetTodosUsecase(
    private val repository: TodoRepository
) : UseCaseSingle<Any, List<Todo>>() {

    override fun build(params: Any?): Single<List<Todo>> =
        repository.getTodos().map { it.sortedBy { it.dueDate } }

}

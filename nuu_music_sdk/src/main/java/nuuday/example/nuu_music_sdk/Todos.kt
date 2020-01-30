package nuuday.example.nuu_music_sdk

import nuuday.example.todo_domain.usecases.AddTodoUsecase
import nuuday.example.todo_domain.usecases.DeleteTodoUsecase
import nuuday.example.todo_domain.usecases.GetTodosUsecase
import org.koin.java.KoinJavaComponent.inject

class Todos{

    val addTodo by inject(AddTodoUsecase::class.java)


    val deleteTodo by inject(DeleteTodoUsecase::class.java)


    val getTodos: GetTodosUsecase by inject(GetTodosUsecase::class.java)
}

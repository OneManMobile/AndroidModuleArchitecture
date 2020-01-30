package nuuday.example.todo_data.di

import nuuday.example.todo_data.TodoMemoryClient
import nuuday.example.todo_data.TodoMemoryClientImp
import nuuday.example.todo_data.TodoRepositoryImp
import nuuday.example.todo_domain.TodoRepository
import nuuday.example.todo_domain.usecases.AddTodoUsecase
import nuuday.example.todo_domain.usecases.DeleteTodoUsecase
import nuuday.example.todo_domain.usecases.GetTodosUsecase
import org.koin.dsl.module

object TodoDomainModule{

    val module = module {

        single<TodoRepository>{
            TodoRepositoryImp(todoMemoryClient = get())
        }


        single<TodoMemoryClient>{
            TodoMemoryClientImp()
        }

        // USE CASES

        single<AddTodoUsecase>{
            AddTodoUsecase(repository = get())
        }

        single<DeleteTodoUsecase>{
            DeleteTodoUsecase(repository = get())
        }

        single<GetTodosUsecase>{
            GetTodosUsecase(repository = get())
        }

    }

}


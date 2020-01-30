package nuuday.example.todo_domain.usecases.usecase

import io.reactivex.Single

/**
 * Represents a single use case.
 */
abstract class UseCaseSingle<P : Any, T> {

    abstract fun build(params: P? = null): Single<T>

}

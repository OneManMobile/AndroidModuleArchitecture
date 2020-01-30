package nuuday.example.todo_domain.usecases.usecase

import io.reactivex.Completable

/**
 * Represents a completable use case.
 */
abstract class UseCaseCompletable<P : Any> {

    abstract fun build(params: P? = null): Completable

}

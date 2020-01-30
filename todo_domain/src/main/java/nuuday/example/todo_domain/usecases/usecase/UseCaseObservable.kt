package nuuday.example.todo_domain.usecases.usecase

import io.reactivex.Observable

/**
 * Represents an observable use case.
 */
abstract class UseCaseObservable<P : Any, T> {

    abstract fun build(params: P? = null): Observable<T>

}

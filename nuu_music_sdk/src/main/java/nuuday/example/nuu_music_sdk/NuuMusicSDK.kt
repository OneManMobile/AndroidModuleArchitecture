package nuuday.example.nuu_music_sdk

import android.app.Application
import android.content.Context
import nuuday.example.todo_data.di.TodoDomainModule
import nuuday.example.todo_domain.TodoRepository
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.inject

object NuuMusicSDK {

    fun init(context: Context){
        startKoin {
            androidLogger()
            androidContext(context)
            modules(
                listOf(
                    TodoDomainModule.module
                )
            )
        }
    }

}

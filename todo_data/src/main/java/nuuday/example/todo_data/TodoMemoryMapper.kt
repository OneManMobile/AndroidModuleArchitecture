package nuuday.example.todo_data

import nuuday.example.todo_domain.Todo
import java.util.Date

object TodoMemoryMapper {

    fun fromMemoryModel(memoryItem: TodoMemoryModel): Todo =
        Todo(Date(memoryItem.timestamp), memoryItem.title)

    fun toMemoryModel(item: Todo): TodoMemoryModel = TodoMemoryModel(item.dueDate.time, item.title)
}

package com.cvelez.mvi.data

import arrow.core.Either
import arrow.core.flatMap
import arrow.core.left
import arrow.core.right
import arrow.core.toOption
import com.cvelez.mvi.data.model.TaskListLocal
import io.realm.Realm
import io.realm.RealmConfiguration

interface TaskListDataSource {
    suspend fun getTaskList(): Either<Throwable, List<TaskListLocal>>
}

class TaskListLocalDataSource(
    private val realmConfiguration: RealmConfiguration
) : TaskListDataSource {

    private val realm: Realm?
        get() = try {
            Realm.getInstance(realmConfiguration)
        } catch (e: Exception) {
            null
        }

    override suspend fun getTaskList(): Either<Throwable, List<TaskListLocal>> {
        return try {
            val realm = realm.toOption().toEither { Throwable("Failed to get Realm instance") }
            val taskList = realm.flatMap {
                it.copyFromRealm(
                    it.where(TaskListLocal::class.java)
                        .findAll()
                        .toList()
                ).right()
            }
            taskList
        } catch (e: Exception) {
            e.left()
        }
    }
}
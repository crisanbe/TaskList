package com.cvelez.mvi.data

import com.cvelez.mvi.data.model.TaskListLocal
import io.realm.Realm
import io.realm.RealmConfiguration

interface TaskListDataSource {
    suspend fun getTaskList(): List<TaskListLocal>
}

class TaskListLocalDataSource (
    private val realmConfiguration: RealmConfiguration
) : TaskListDataSource {

    private val realm: Realm
        get() = Realm.getInstance(realmConfiguration)

    override suspend fun getTaskList(): List<TaskListLocal> {
        return realm.use {
            realm.copyFromRealm(
                realm.where(TaskListLocal::class.java)
                    .findAll()
                    .toList()
            )
        }
    }

}
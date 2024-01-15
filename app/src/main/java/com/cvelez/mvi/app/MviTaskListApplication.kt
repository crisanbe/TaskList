package com.cvelez.mvi.app

import android.app.Application
import com.cvelez.mvi.data.model.TaskListLocal
import dagger.hilt.android.HiltAndroidApp
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmList
import java.util.*
import javax.inject.Inject

@HiltAndroidApp
class MviTaskListApplication : Application() {

    @Inject
    lateinit var realmConfiguration: RealmConfiguration

    override fun onCreate() {
        Realm.init(this)
        super.onCreate()
        initWithPreconditionData()
    }

    private fun initWithPreconditionData() {
        //Prefill database with data
        val realm = Realm.getInstance(realmConfiguration)
        val count = realm.where(TaskListLocal::class.java).count()
        if (count == 0L) {
            realm.executeTransaction {
                realm.insertOrUpdate(
                    RealmList(
                        TaskListLocal().apply {
                            id = UUID.randomUUID().toString()
                            text = "Make a breakfast"
                            isChecked = false
                        },
                        TaskListLocal().apply {
                            id = UUID.randomUUID().toString()
                            text = "Clean the room"
                            isChecked = false
                        },
                        TaskListLocal().apply {
                            id = UUID.randomUUID().toString()
                            text = "Create the MVI sample"
                            isChecked = true
                        },
                        TaskListLocal().apply {
                            id = UUID.randomUUID().toString()
                            text = "Upload it to medium"
                            isChecked = true
                        },
                    )
                )
            }
        }
    }
}
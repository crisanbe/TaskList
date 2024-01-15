package com.cvelez.mvi.app

import android.app.Application
import com.cvelez.mvi.coroutines.dispatcherModule
import com.cvelez.mvi.data.di.dataModule
import com.cvelez.mvi.data.di.viewModelModule
import com.cvelez.mvi.data.model.TaskListLocal
import com.cvelez.mvi.domain.repository.repositoryModule
import com.cvelez.mvi.domain.use_case.useCaseModule
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmList
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import java.util.*

class MviTaskListApplication : Application(), KoinComponent {

    private val realmConfiguration: RealmConfiguration by inject()

    override fun onCreate() {
        Realm.init(this)
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MviTaskListApplication)
            modules(
                listOf(
                    appModule,
                    dispatcherModule,
                    dataModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
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
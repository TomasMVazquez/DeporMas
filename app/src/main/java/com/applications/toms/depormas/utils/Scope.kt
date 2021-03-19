package com.applications.toms.depormas.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

interface Scope : CoroutineScope {

    var job: Job

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    fun initScope(){
        job = SupervisorJob()
    }

    fun cancelScope(){
        job.cancel()
    }

    class ImplementJob: Scope{
        override lateinit var job: Job
    }

}
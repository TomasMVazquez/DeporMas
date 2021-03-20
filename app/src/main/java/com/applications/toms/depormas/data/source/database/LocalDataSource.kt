package com.applications.toms.depormas.data.source.database

import com.applications.toms.depormas.data.model.Sport
import kotlinx.coroutines.flow.Flow


interface LocalDataSource {
    suspend fun isEmpty():Boolean
    suspend fun saveSports(sports: List<Sport>)
    suspend fun getAllSports(): List<Sport>
}

package com.assignment.zalora.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RemoteKeys(
    @PrimaryKey
    val repoId: String,
    val prevKey: Int?,
    val nextKey: Int?
)
package com.bangkit.hansai.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
@Entity(tableName = "log")
class LogEntity(
    @field:ColumnInfo(name = "id")
    @PrimaryKey
    var id: Int,

    @field:ColumnInfo(name = "title")
    var title: String,

    @field:ColumnInfo(name = "summary")
    var summary: String,

    @field:ColumnInfo(name = "date")
    var date: Date,

    @field:ColumnInfo(name = "breakfast")
    var breakfast: List<Int>,

    @field:ColumnInfo(name = "lunch")
    var lunch: List<Int>,

    @field:ColumnInfo(name = "dinner")
    var dinner: List<Int>,

    @field:ColumnInfo(name = "base_goal")
    var baseGoal: Double,

    @field:ColumnInfo(name = "current_weight")
    var currentWeight: Double,

    @field:ColumnInfo(name = "calories")
    var calories: Double,

    @field:ColumnInfo(name = "carbs")
    var carbs: Double,

    @field:ColumnInfo(name = "protein")
    var protein: Double,

    @field:ColumnInfo(name = "fat")
    var fat: Double,

    @field:ColumnInfo(name = "last_update")
    var lastUpdate: Date

) : Parcelable
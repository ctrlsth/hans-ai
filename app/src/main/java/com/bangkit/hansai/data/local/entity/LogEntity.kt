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
    var id: String,

    @field:ColumnInfo(name = "logTitle")
    var title: String,

    @field:ColumnInfo(name = "summary")
    var summary: String,

    @field:ColumnInfo(name = "date")
    var date: Date,

    @field:ColumnInfo(name = "breakfast")
    var breakfast: List<String>,

    @field:ColumnInfo(name = "lunch")
    var lunch: List<String>,

    @field:ColumnInfo(name = "dinner")
    var dinner: List<String>,

    @field:ColumnInfo(name = "base_goal")
    var baseGoal: Double,

    @field:ColumnInfo(name = "weight")
    var weight: Double,

    @field:ColumnInfo(name = "totalCalories")
    var totalCalories: Double,

    @field:ColumnInfo(name = "totalCarbs")
    var totalCarbs: Double,

    @field:ColumnInfo(name = "totalProtein")
    var totalProtein: Double,

    @field:ColumnInfo(name = "totalFat")
    var totalFat: Double,
) : Parcelable
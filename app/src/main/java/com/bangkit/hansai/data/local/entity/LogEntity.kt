package com.bangkit.hansai.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
@Entity(tableName = "recipe")
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

    @field:ColumnInfo(name = "calories")
    var calories: Int,

    @field:ColumnInfo(name = "carbs")
    var carbs: Int,

    @field:ColumnInfo(name = "protein")
    var protein: Int,

    @field:ColumnInfo(name = "fat")
    var fat: Int,

    @field:ColumnInfo(name = "last_update")
    var lastUpdate: Date

) : Parcelable
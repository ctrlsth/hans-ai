package com.bangkit.hansai.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
@Entity(tableName = "recipe")
class RecipeEntity(
    @field:ColumnInfo(name = "id")
    @PrimaryKey
    var id: Int,

    @field:ColumnInfo(name = "title")
    var title: String,

    @field:ColumnInfo(name = "description")
    var description: String,

    @field:ColumnInfo(name = "ingredients")
    var ingredients: String,

    @field:ColumnInfo(name = "steps")
    var steps: String,

    @field:ColumnInfo(name = "carbs")
    var carbs: Int,

    @field:ColumnInfo(name = "protein")
    var protein: Int,

    @field:ColumnInfo(name = "fat")
    var fat: Int,

    @field:ColumnInfo(name = "duration")
    var duration: Int,

    @field:ColumnInfo(name = "date")
    var date: Date,

    @field:ColumnInfo(name = "last_update")
    var lastUpdate: Date

) : Parcelable
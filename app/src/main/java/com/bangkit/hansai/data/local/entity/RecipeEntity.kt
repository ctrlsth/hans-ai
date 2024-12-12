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

    @field:ColumnInfo(name = "ingredients")
    var ingredients: String,

    @field:ColumnInfo(name = "steps")
    var steps: String,

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
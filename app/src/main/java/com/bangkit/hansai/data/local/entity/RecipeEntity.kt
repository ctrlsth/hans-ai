package com.bangkit.hansai.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.bangkit.hansai.utils.Converters
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "recipe")
class RecipeEntity(
    @field:ColumnInfo(name = "id")
    @PrimaryKey
    var id: String,

    @field:ColumnInfo(name = "name")
    var name: String,

    @field:ColumnInfo(name = "ingredients")
    var ingredients: String,

    @field:ColumnInfo(name = "instructions")
    var instructions: String,

    @field:ColumnInfo(name = "calories")
    var calories: Double,

    @field:ColumnInfo(name = "carbs")
    var carbs: Double,

    @field:ColumnInfo(name = "protein")
    var protein: Double,

    @field:ColumnInfo(name = "fat")
    var fat: Double,
) : Parcelable
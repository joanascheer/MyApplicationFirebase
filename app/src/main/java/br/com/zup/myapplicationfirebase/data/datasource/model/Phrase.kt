package br.com.zup.myapplicationfirebase.data.datasource.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Phrase (
@SerializedName("file")
    val phrase: String = ""
):Parcelable
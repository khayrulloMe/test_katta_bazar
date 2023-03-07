package uz.gita.test_katta_bazar.data.remote.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class AttributeResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("value")
    val value: String
)
package uz.gita.test_katta_bazar.data.remote.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ImageResponse(
    @SerializedName("height")
    val height: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
)
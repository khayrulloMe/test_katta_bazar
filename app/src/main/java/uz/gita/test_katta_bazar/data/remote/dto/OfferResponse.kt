package uz.gita.test_katta_bazar.data.remote.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class OfferResponse(
    @SerializedName("attributes")
    val attributeResponses: List<AttributeResponse>,
    @SerializedName("brand")
    val brand: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val imageResponse: ImageResponse,
    @SerializedName("merchant")
    val merchant: String,
    @SerializedName("name")
    val name: String
)
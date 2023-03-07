package uz.gita.test_katta_bazar.data.remote.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class TechResponse(
    @SerializedName("offers")
    val offerResponses: List<OfferResponse>
)
package uz.gita.test_katta_bazar.data.moddel

import uz.gita.test_katta_bazar.data.remote.dto.ImageResponse

data class OfferModel(
    val attributeResponses: List<AttributeModel>,
    val brand: String,
    val category: String,
    val id: Int,
    val imageResponse: ImageModel,
    val merchant: String,
    val name: String
)
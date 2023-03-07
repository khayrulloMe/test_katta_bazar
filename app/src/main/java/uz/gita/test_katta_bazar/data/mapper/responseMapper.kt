package uz.gita.test_katta_bazar.data.mapper

import uz.gita.test_katta_bazar.data.moddel.AttributeModel
import uz.gita.test_katta_bazar.data.moddel.ImageModel
import uz.gita.test_katta_bazar.data.moddel.OfferModel
import uz.gita.test_katta_bazar.data.moddel.TechModel
import uz.gita.test_katta_bazar.data.remote.dto.AttributeResponse
import uz.gita.test_katta_bazar.data.remote.dto.ImageResponse
import uz.gita.test_katta_bazar.data.remote.dto.OfferResponse
import uz.gita.test_katta_bazar.data.remote.dto.TechResponse

fun TechResponse.toModel(): TechModel = TechModel(offerResponses = this.offerResponses.map(transform = { it.toModel() }))
fun AttributeResponse.toModel(): AttributeModel = AttributeModel(name = this.name, value = this.value)
fun ImageResponse.toModel(): ImageModel = ImageModel(height = this.height, url = this.url, width = this.width)
fun OfferResponse.toModel(): OfferModel = OfferModel(
    attributeResponses = this.attributeResponses.map(
        transform = {
            AttributeModel(name = it.name, value = it.value)
        }
    ),
    brand = this.brand,
    category = this.category, id
    = this.id,
    imageResponse = this.imageResponse.toModel(),
    merchant = this.merchant,
    name = this.name
)
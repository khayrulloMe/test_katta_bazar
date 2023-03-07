package uz.gita.test_katta_bazar.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import coil.compose.rememberImagePainter
import uz.gita.test_katta_bazar.data.moddel.OfferModel
import uz.gita.test_katta_bazar.presentation.view_model.detail.DetailIntent
import uz.gita.test_katta_bazar.presentation.view_model.detail.impl.DetailViewModelImpl

class DetailScreen(private val item: OfferModel) : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel = getViewModel<DetailViewModelImpl>()
        DetailScreenContent(item, viewModel::onEventDispatcher)
    }

}

@Composable
fun DetailScreenContent(item: OfferModel, onEvent: (DetailIntent) -> Unit) {
    val image = rememberImagePainter(data = item.imageResponse.url, builder = {})
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top, modifier = Modifier.fillMaxSize().background
        (MaterialTheme.colorScheme.background)) {
        Box(
            modifier = Modifier
                .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
                .clip(RoundedCornerShape(20))
                .background(MaterialTheme.colorScheme.primary)
                .padding(8.dp)
                .align(Alignment.Start)
                .clickable {
                    onEvent(DetailIntent.Back)
                },
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "", tint = MaterialTheme.colorScheme.background)
        }
        Image(
            painter = image,
            contentDescription = "",
            modifier = Modifier
                .clip(shape = RoundedCornerShape(20.dp))
                .width(item.imageResponse.width.dp)
                .height(item.imageResponse.height.dp)
        )
        Box(modifier = Modifier.height(50.dp))

        Column(Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment
                    .Bottom
            ) {
                Text(text = "Name", style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.W600), overflow = TextOverflow.Ellipsis)
                Text(text = item.name, style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.W400), overflow = TextOverflow.Ellipsis)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment
                    .Bottom
            ) {
                Text(text = "Brand", style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.W600), overflow = TextOverflow.Ellipsis)
                Text(text = item.brand, style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.W400), overflow = TextOverflow.Ellipsis)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment
                    .Bottom
            ) {
                Text(text = "Merchant", style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.W600), overflow = TextOverflow.Ellipsis)
                Text(text = item.merchant, style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.W400), overflow = TextOverflow.Ellipsis)
            }

            LazyColumn() {
                items(items = item.attributeResponses) { it ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                            .padding(start = 16.dp, top = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment
                            .Bottom
                    ) {
                        Text( overflow = TextOverflow.Ellipsis,
                            text = it.name, style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.W600), modifier = Modifier.padding(
                                end =
                                24.dp
                            )
                        )
                        Text(text = it.value, style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.W400), maxLines = 1, overflow = TextOverflow.Ellipsis)
                    }
                }
            }

        }
    }

}
package uz.gita.test_katta_bazar.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import coil.compose.rememberImagePainter
import uz.gita.test_katta_bazar.data.moddel.OfferModel

class DetailScreen(private val item: OfferModel) : AndroidScreen() {
    @Composable
    override fun Content() {
        DetailScreenContent(item)
    }

}

@Composable
fun DetailScreenContent(item: OfferModel) {
    val image = rememberImagePainter(data = item.imageResponse.url, builder = {})
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

        Image(
            painter = image,
            contentDescription = "",
            modifier = Modifier
                .clip(shape = RoundedCornerShape(20.dp))
                .width(item.imageResponse.width.dp)
                .height(item.imageResponse.height.dp)
        )

        Column(Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 4.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment
                    .Bottom
            ) {
                Text(text = "Name", style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.W400))
                Text(text = item.name, style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.W400))
            }
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 4.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment
                    .Bottom
            ) {
                Text(text = "Brand", style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.W400))
                Text(text = item.brand, style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.W400))
            }

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 4.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment
                    .Bottom
            ) {
                Text(text = "Location", style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.W400))
                Text(text = item.merchant, style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.W400))
            }

            LazyColumn() {
                items(items = item.attributeResponses) { it ->
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 8.dp, vertical = 2.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment
                            .Bottom
                    ) {
                        Text(text = it.name, style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.W400))
                        Text(text = it.name, style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.W400))
                    }
                }
            }

        }
    }

}
package uz.gita.test_katta_bazar.presentation.screen

import android.media.audiofx.DynamicsProcessing.Stage
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.test_katta_bazar.data.moddel.OfferModel
import uz.gita.test_katta_bazar.data.moddel.TechModel
import uz.gita.test_katta_bazar.presentation.view_model.main.MainIntent
import uz.gita.test_katta_bazar.presentation.view_model.main.MainUiState
import uz.gita.test_katta_bazar.presentation.view_model.main.impl.MainViewModelImpl
import uz.gita.test_katta_bazar.presentation.view_model.splash.impl.SplashViewModelImpl

class MainScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel = getViewModel<MainViewModelImpl>()
        val uiState = viewModel.collectAsState().value
        Surface(modifier = Modifier.fillMaxSize()) {
            MainScreenContent(viewModel::onEventDispatcher, uiState)

        }
    }

}

@Composable
fun MainScreenContent(onEvent: (MainIntent) -> Unit, uiState: MainUiState) {
    var job: Job? = null

    var message by remember {
        mutableStateOf("")
    }
    var isLoading by remember {
        mutableStateOf(false)
    }
    var list by remember {
        mutableStateOf((listOf<OfferModel>()))
    }


    when (uiState) {
        is MainUiState.Loading -> {
            Log.d("Shoxrux", "MainScreenContent: ${uiState.isLoading}")
            isLoading = uiState.isLoading
        }
        is MainUiState.Message -> {
            message = uiState.message

        }
        is MainUiState.Success -> {
            isLoading = false
            message = ""
            list = uiState.list.offerResponses

        }


    }


    val refresh = rememberSwipeRefreshState(isRefreshing = isLoading)

    SwipeRefresh(state = refresh, onRefresh = { onEvent(MainIntent.GetDataRequest) }) {

        SuccessContent(list) {
            job?.cancel()
            onEvent(MainIntent.GoToDetails(it))
        }

    }

    AnimatedVisibility(visible = message.isNotEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background), contentAlignment = Alignment.Center
        ) {
            Text(message, style = TextStyle(fontSize = 24.sp, color = MaterialTheme.colorScheme.error))
        }
    }


}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SuccessContent(list: List<OfferModel>, onClick: (item: OfferModel) -> Unit) {
    LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(2), modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(8.dp)) {
        items(list) { item ->
            TechItem(item = item) {
                onClick(it)
            }

        }
    }


}

@Composable
fun TechItem(item: OfferModel, onClick: (item: OfferModel) -> Unit) {

    val image = rememberImagePainter(data = item.imageResponse.url, builder = {})
    Column(modifier = Modifier
        .padding(4.dp)
        .clip(RoundedCornerShape(20.dp))
        .background(MaterialTheme.colorScheme.inversePrimary)
        .padding(4.dp)
        .clickable {
            onClick(item)
        }) {
        Image(
            painter = image,
            contentDescription = "",
            modifier = Modifier
                .clip(shape = RoundedCornerShape(20.dp))
                .width(item.imageResponse.width.dp)
                .height(item.imageResponse.height.dp)

        )
        Text(
            overflow = TextOverflow.Ellipsis,
            text = item.brand,
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(
                top = 4.dp, bottom = 4.dp, start = 4.dp
            ),
            maxLines = 1
        )
        Text(
            overflow = TextOverflow.Ellipsis,
            text = item.merchant,
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.W500),
            modifier = Modifier.padding(
                top = 4.dp, bottom = 4.dp, start = 4.dp
            ),
            maxLines = 1
        )
        Text(
            overflow = TextOverflow.Ellipsis,
            text = item.name,
            style = TextStyle(fontSize = 16.sp),
            modifier = Modifier.padding(top = 4.dp, bottom = 16.dp, start = 4.dp),
            maxLines = 1
        )
    }
}


@Composable
fun Message(message: String) {

}
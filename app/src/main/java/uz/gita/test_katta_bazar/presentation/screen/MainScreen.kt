package uz.gita.test_katta_bazar.presentation.screen

import android.media.audiofx.DynamicsProcessing.Stage
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
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
        MainScreenContent(viewModel::onEventDispatcher, uiState)

    }

}

@Composable
fun MainScreenContent(onEvent: (MainIntent) -> Unit, uiState: MainUiState) {

    LaunchedEffect(key1 = Unit) {
        onEvent(MainIntent.GetDataRequest)
    }

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
            isLoading = uiState.isLoading
        }
        is MainUiState.Message -> {
            message = uiState.message

        }
        is MainUiState.Success -> {

            list = uiState.list.offerResponses

        }

    }
    Loading(isLoading = isLoading)
    Message(message = message)
    SuccessContent(list)


}

@Composable
fun SuccessContent(list: List<OfferModel>) {
    LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(4.dp)) {

        items(list) { item ->
            TechItem(item = item)
        }

    }


}

@Composable
fun TechItem(item: OfferModel) {
    val image = rememberImagePainter(
        data = item.imageResponse.url,
        builder = {
        }
    )
    Column(modifier = Modifier.fillMaxWidth(0.9f)) {
        Image(
            painter = image, contentDescription = "", modifier = Modifier
                .width(item.imageResponse.width.dp)
                .height(item.imageResponse.height.dp)
        )
        Text(text = item.merchant)
    }
}


@Composable
fun Loading(isLoading: Boolean) {
    val loading by remember {
        mutableStateOf(isLoading)
    }
    AnimatedVisibility(visible = loading)
    {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background), contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun Message(message: String) {
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
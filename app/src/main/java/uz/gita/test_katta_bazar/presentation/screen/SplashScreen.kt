package uz.gita.test_katta_bazar.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import uz.gita.test_katta_bazar.R
import uz.gita.test_katta_bazar.presentation.view_model.splash.SplashIntent
import uz.gita.test_katta_bazar.presentation.view_model.splash.impl.SplashViewModelImpl
import uz.gita.test_katta_bazar.utils.InternetBroadCast

class SplashScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel = getViewModel<SplashViewModelImpl>()
        SplashScreenContent()
    }

}

@Composable
fun SplashScreenContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Image(painter = painterResource(id = R.drawable.katta_bozor), contentDescription = "icon")
    }

}
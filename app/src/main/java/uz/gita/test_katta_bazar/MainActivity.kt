package uz.gita.test_katta_bazar

import android.annotation.SuppressLint
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.lifecycleScope
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.test_katta_bazar.navigation.NavigationHandler
import uz.gita.test_katta_bazar.presentation.screen.MainScreen
import uz.gita.test_katta_bazar.presentation.screen.SplashScreen
import uz.gita.test_katta_bazar.ui.theme.Test_katta_bazarTheme
import uz.gita.test_katta_bazar.utils.InternetBroadCast
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigationHandler: NavigationHandler


    @SuppressLint("CoroutineCreationDuringComposition", "FlowOperatorInvokedInComposition")
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {
            Test_katta_bazarTheme {
                var hasInternet by remember {
                    mutableStateOf(true)
                }
                val internetBroadCast = InternetBroadCast {
                    hasInternet = it
                }
                registerReceiver(
                    internetBroadCast,
                    IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
                )
                if (hasInternet) {
                    Navigator(screen = MainScreen(),
                        onBackPressed = {
                            true
                        }
                    ) { navigator ->
                        navigationHandler.navStack
                            .onEach {
                                it.invoke(navigator)
                            }.launchIn(lifecycleScope)
                        SlideTransition(navigator)
                    }
                } else {
                        Image(painter = painterResource(id = R.drawable.no_i), contentDescription = "no", modifier = Modifier.fillMaxSize()
                            .background(Color(0xffefefef)))
                    }
                }
        }
    }
}

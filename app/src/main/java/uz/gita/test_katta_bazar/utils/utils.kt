package uz.gita.test_katta_bazar.utils

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator

object SizeConst {
    val smallest: Dp = 2.dp
    val small: Dp = 4.dp
    val smallMiddle: Dp = 8.dp
    val middle: Dp = 16.dp
    val middleLarge: Dp = 24.dp
    val large: Dp = 28.dp
    val larger: Dp = 32.dp
    val largest: Dp = 48.dp
}


typealias NavigatorArg = Navigator.() -> Unit
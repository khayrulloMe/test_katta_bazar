package uz.gita.test_katta_bazar.utils

import android.widget.Toast
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager

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


class InternetBroadCast(val onInternet:(Boolean)->Unit) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        val connMgr = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val wifi = connMgr
            .getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        val mobile = connMgr
            .getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
            if(wifi!!.isConnected||mobile!!.isConnected){
                onInternet(true)
            }else{

                onInternet(false)

            }





    }


}
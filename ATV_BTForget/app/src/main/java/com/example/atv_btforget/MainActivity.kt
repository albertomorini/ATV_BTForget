package com.example.atv_btforget

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.tv.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Surface
import com.example.atv_btforget.ui.theme.ATV_BTForgetTheme
import java.net.URL
import kotlin.system.exitProcess
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    @SuppressLint("MissingPermission")
    @OptIn(ExperimentalTvMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ATV_BTForgetTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    shape = RectangleShape
                ) {
                    Greeting("Android")
                    PC_APIDisconnectBluetooth()
                    ATV_restartBluetooth()

                }
            }
        }
    }
}


@Composable
@SuppressLint("MissingPermission")
@RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
fun ATV_restartBluetooth(){
    try{
        // Declaring Bluetooth adapter
        val mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        mBluetoothAdapter.disable();
        Thread.sleep(3500); //wait 5sec
        mBluetoothAdapter.enable()

    }catch (e: Exception){
        showError(e.message.toString(),40)
    }

}
@Composable
fun PC_APIDisconnectBluetooth(){
    try {
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        URL("http://10.0.0.3:9988/turnOffBluetooth").readText()
    }catch (e: Exception){
        showError(e.message.toString(),90)
    }

}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello Alby, PC's bluetooth turned off... wait a sec ;)",
        modifier = modifier
    )

}
@Composable
fun showError(message: String, padding : Int){
    Text(
        text= message,
        modifier = Modifier.padding(10.dp,padding.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ATV_BTForgetTheme {
        Greeting("Android")
    }
}
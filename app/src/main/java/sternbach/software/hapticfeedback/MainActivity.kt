package sternbach.software.hapticfeedback

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.VibrationAttributes
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import sternbach.software.hapticfeedback.ui.theme.HapticFeedbackTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //check if we have location permission:

        /*if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            //if not, request it:
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
        }*/
        val vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator
        setContent {
            HapticFeedbackTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        Button(onClick = {
                            val duration: Long = 100_000_000L
                            if (vibrator.hasAmplitudeControl()) {
                                vibrate(vibrator, duration)
                            }
                        }) {
                            Text("Vibrate max")
                        }
                        Button(onClick = {
                            val duration: Long = 100_000_000L
                            if (vibrator.hasAmplitudeControl()) {
                                vibrate(vibrator, duration, VibrationEffect.DEFAULT_AMPLITUDE)
                            }
                        }) {
                            Text("Vibrate default")
                        }
                        Button(onClick = {
                            val duration: Long = 100_000_000L
                            if (vibrator.hasAmplitudeControl()) {
                                vibrate(vibrator, duration, 10, VibrationAttributes.USAGE_ALARM)
                            }
                        }) {
                            Text("Vibrate low")
                        }
                        Button(onClick = { vibrator.cancel() }) {
                            Text("Cancel")
                        }
                    }
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun vibrate(vibrator: Vibrator, duration: Long, amplitude: Int = 255, usage: Int = VibrationAttributes.USAGE_ALARM) {
        vibrator.vibrate(
            VibrationEffect.createOneShot(duration, amplitude),
            VibrationAttributes.createForUsage(usage)
        )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HapticFeedbackTheme {
        Greeting("Android")
    }
}
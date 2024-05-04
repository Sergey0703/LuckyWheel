package com.serhiibaliasnyi.luckywheel

import android.media.SoundPool
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.serhiibaliasnyi.luckywheel.rule_screen.RuleScreen
import com.serhiibaliasnyi.luckywheel.ui.theme.GreenBg
import com.serhiibaliasnyi.luckywheel.ui.theme.LuckyWheelTheme

class MainActivity : ComponentActivity() {
    //lateinit var player: ExoPlayer
    var soundPool: SoundPool? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val spb = SoundPool.Builder()
        spb.setMaxStreams(4)
        soundPool = spb.build()
        soundPool?.load(this, R.raw.lucky_wheel, 1)
        setContent {

            LuckyWheelTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = GreenBg
                ) {
                    RuleScreen(soundPool)
                }
            }
        }
    }
}


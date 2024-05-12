package com.serhiibaliasnyi.luckywheel

import android.media.SoundPool
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.OptIn
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.serhiibaliasnyi.luckywheel.rule_screen.RuleScreen
import com.serhiibaliasnyi.luckywheel.ui.theme.GreenBg
import com.serhiibaliasnyi.luckywheel.ui.theme.GreenMain
import com.serhiibaliasnyi.luckywheel.ui.theme.LuckyWheelTheme

class MainActivity : ComponentActivity() {
    lateinit var player: ExoPlayer
    var soundPool: SoundPool? = null
    @OptIn(UnstableApi::class) override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        player = ExoPlayer.Builder(this).setPauseAtEndOfMediaItems(true).build()
        val playList = getPlayList()
        val spb = SoundPool.Builder()
        spb.setMaxStreams(4)
        soundPool = spb.build()
        soundPool?.load(this, R.raw.lucky_wheel, 1)
        soundPool?.load(this, R.raw.success, 2)
        setContent {

            LuckyWheelTheme {
                // A surface container using the 'background' color from the theme
                val composition by rememberLottieComposition(
                    spec = LottieCompositionSpec.Asset("lottie_success7.json")
                )
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = GreenMain
                ) {
                    //LottieAnimation(composition = composition)
                    RuleScreen(soundPool, composition,player, playList)
                }
            }
        }
    }
    private fun getPlayList(): List<Music> {
        return listOf(
            Music(
                name = "Incy Wincy Spider",
                cover = "assets.image1",
                music = R.raw.track1
            ),
            Music(
                name = "Head Shoulders Knees and Toes",
                cover = "assets.image2",
                music = R.raw.track2
            ),
            Music(
                name = "Twinkle Twinkle Little Star",
                cover = "assets.image3",
                music = R.raw.track3
            ),
            Music(
                name = "Jelly on a Plate",
                cover = "assets.image4",
                music = R.raw.track4
            ),
            Music(
                name = "Sleeping Bunnies",
                cover = "assets.image5",
                music = R.raw.track5
            ),
            Music(
                name = "Wind the Bobbin Up",
                cover = "assets.image6",
                music = R.raw.track6
            ),
            Music(
                name = "If You\'re Happy and You Know It",
                cover = "assets.image7",
                music = R.raw.track7
            ),
            Music(
                name = "The Wheels on the Bus",
                cover = "assets.image8",
                music = R.raw.track8
            ),
            Music(
                name = "Pat a Cake Pat a Cake",
                cover = "assets.image9",
                music = R.raw.track9
            ),
            Music(
                name = "Five Little Monkeys",
                cover = "assets.image10",
                music = R.raw.track10
            ),
            Music(
                name = "I'm a Little Teapot",
                cover = "assets.image11",
                music = R.raw.track11
            ),
            Music(
                name = "Hey Diddle Diddle",
                cover = "assets.image12",
                music = R.raw.track12
            ),
            Music(
                name = "One Two Buckle My Shoe",
                cover = "assets.image13",
                music = R.raw.track13
            ),
            Music(
                name = "Old MacDonald Had a Farm",
                cover = "assets.image14",
                music = R.raw.track14
            ),
            Music(
                name = "This Is the Way the Ladies Ride",
                cover = "assets.image15",
                music = R.raw.track15
            ),
            Music(
                name = "Teddy Bear's Picnic",
                cover = "assets.image16",
                music = R.raw.track16
            ),
            Music(
                name = "The Animals Went in Two by Two",
                cover = "assets.image17",
                music = R.raw.track17
            ),
            Music(
                name = "Row Row Row Your Boat",
                cover = "assets.image18",
                music = R.raw.track18
            ), /*
            Music(
                name = "Old King Cole",
                cover = "assets.image19",
                music = R.raw.track19
            ),
            Music(
                name = "1 2 3 4 5 Once I Caught a Fish Alive",
                cover = "assets.image20",
                music = R.raw.track20
            ),
            Music(
                name = "Hickory Dickory Dock",
                cover = "assets.image21",
                music = R.raw.track21
            ),
            Music(
                name = "Bobby Shaftoe",
                cover = "assets.image22",
                music = R.raw.track22
            ),
            Music(
                name = "Ten in the Bed",
                cover = "assets.image23",
                music = R.raw.track23
            ),
            Music(
                name = "Little Miss Muffet",
                cover = "assets.image24",
                music = R.raw.track24
            ),
            Music(
                name = "Humpty Dumpty",
                cover = "assets.image25",
                music = R.raw.track25
            ),
            Music(
                name = "This Old Man",
                cover = "assets.image26",
                music = R.raw.track26
            ),
            Music(
                name = "Five Little Speckled Frogs",
                cover = "assets.image27",
                music = R.raw.track27
            ),
            Music(
                name = "Baa Baa Black Sheep",
                cover = "assets.image28",
                music = R.raw.track28
            ),
            Music(
                name = "Ring a Ring O' Roses",
                cover = "assets.image29",
                music = R.raw.track29
            ),
            Music(
                name = "With My Foot I Tap Tap Tap",
                cover = "assets.image30",
                music = R.raw.track30
            ),
            Music(
                name = "Jack and Jill",
                cover = "assets.image31",
                music = R.raw.track31
            ),
            Music(
                name = "The Alphabet Song",
                cover = "assets.image32",
                music = R.raw.track32
            ),
            Music(
                name = "Polly Put the Kettle On",
                cover = "assets.image33",
                music = R.raw.track33
            ),
            Music(
                name = "She'll Be Coming Round the Mountain",
                cover = "assets.image34",
                music = R.raw.track34
            ),
            Music(
                name = "One Finger One Thumb",
                cover = "assets.image35",
                music = R.raw.track35
            ),
            Music(
                name = "One Man Went to Mow",
                cover = "assets.image36",
                music = R.raw.track36
            ),
            Music(
                name = "London Bridge Is Falling Down",
                cover = "assets.image37",
                music = R.raw.track37
            ),
            Music(
                name = "The Muffin Man",
                cover = "assets.image38",
                music = R.raw.track38
            ),
            Music(
                name = "Five Little Ducks",
                cover = "assets.image39",
                music = R.raw.track39
            ),
            Music(
                name = "Pop! Goes the Weasel",
                cover = "assets.image40",
                music = R.raw.track40
            ),
            Music(
                name = "Here We Go Looby Loo",
                cover = "assets.image41",
                music = R.raw.track41
            ),
            Music(
                name = "Sing a Song of Sixpence",
                cover = "assets.image42",
                music = R.raw.track42
            ),
            Music(
                name = "Three Blind Mice",
                cover = "assets.image43",
                music = R.raw.track43
            ),
            Music(
                name = "The Grand Old Duke of York",
                cover = "assets.image44",
                music = R.raw.track44
            ),
            Music(
                name = "One Potato Two Potato",
                cover = "assets.image45",
                music = R.raw.track45
            ),
            Music(
                name = "Miss Polly Had a Dolly",
                cover = "assets.image46",
                music = R.raw.track46
            ),
            Music(
                name = "Open Shut Them",
                cover = "assets.image47",
                music = R.raw.track47
            ),
            Music(
                name = "Here We Go Round the Mulberry Bush",
                cover = "assets.image48",
                music = R.raw.track48
            ),
            Music(
                name = "Drunken Sailor",
                cover = "assets.image49",
                music = R.raw.track49
            ),
            Music(
                name = "Mary Had a Little Lamb",
                cover = "assets.image50",
                music = R.raw.track50
            ) */
        )
    }

    /***
     * Data class to represent a music in the list
     */
    data class Music(
        val name: String,
        val music: Int,
        val cover: String,
    )

}





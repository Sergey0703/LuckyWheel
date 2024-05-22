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
        soundPool?.load(this, R.raw.coin, 3)
        soundPool?.load(this, R.raw.broken, 4)
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
                id=1,
                name = "Incy Wincy Spider",
                cover = "assets.image1",
                music = R.raw.track1
            ),
            Music(
                id=2,
                name = "Head Shoulders Knees and Toes",
                cover = "assets.image2",
                music = R.raw.track2
            ),
            Music(
                id=3,
                name = "Twinkle Twinkle Little Star",
                cover = "assets.image3",
                music = R.raw.track3
            ),
            Music(
                id=4,
                name = "Jelly on a Plate",
                cover = "assets.image4",
                music = R.raw.track4
            ),
            Music(
                id=5,
                name = "Sleeping Bunnies",
                cover = "assets.image5",
                music = R.raw.track5
            ),
            Music(
                id=6,
                name = "Wind the Bobbin Up",
                cover = "assets.image6",
                music = R.raw.track6
            ),
            Music(
                id=7,
                name = "If You\'re Happy and You Know It",
                cover = "assets.image7",
                music = R.raw.track7
            ),
            Music(
                id=8,
                name = "The Wheels on the Bus",
                cover = "assets.image8",
                music = R.raw.track8
            ),
            Music(
                id=9,
                name = "Pat a Cake Pat a Cake",
                cover = "assets.image9",
                music = R.raw.track9
            ),
            Music(
                id=10,
                name = "Five Little Monkeys",
                cover = "assets.image10",
                music = R.raw.track10
            ),
            Music(
                id=11,
                name = "I'm a Little Teapot",
                cover = "assets.image11",
                music = R.raw.track11
            ),
            Music(
                id=12,
                name = "Hey Diddle Diddle",
                cover = "assets.image12",
                music = R.raw.track12
            ),
            Music(
                id=13,
                name = "One Two Buckle My Shoe",
                cover = "assets.image13",
                music = R.raw.track13
            ),
            Music(
                id=14,
                name = "Old MacDonald Had a Farm",
                cover = "assets.image14",
                music = R.raw.track14
            ),
            Music(
                id=15,
                name = "This Is the Way the Ladies Ride",
                cover = "assets.image15",
                music = R.raw.track15
            ),
            Music(
                id=16,
                name = "Teddy Bear's Picnic",
                cover = "assets.image16",
                music = R.raw.track16
            ),
            Music(
                id=17,
                name = "The Animals Went in Two by Two",
                cover = "assets.image17",
                music = R.raw.track17
            ),
            Music(
                id=18,
                name = "Row Row Row Your Boat",
                cover = "assets.image18",
                music = R.raw.track18
            ),
            Music(
                id=19,
                name = "Old King Cole",
                cover = "assets.image19",
                music = R.raw.track19
            ),
            Music(
                id=20,
                name = "1 2 3 4 5 Once I Caught a Fish Alive",
                cover = "assets.image20",
                music = R.raw.track20
            ),
            Music(
                id=21,
                name = "Hickory Dickory Dock",
                cover = "assets.image21",
                music = R.raw.track21
            ),
            Music(
                id=22,
                name = "Bobby Shaftoe",
                cover = "assets.image22",
                music = R.raw.track22
            ),
            Music(
                id=23,
                name = "Ten in the Bed",
                cover = "assets.image23",
                music = R.raw.track23
            ),
            Music(
                id=24,
                name = "Little Miss Muffet",
                cover = "assets.image24",
                music = R.raw.track24
            ),
            Music(
                id=25,
                name = "Humpty Dumpty",
                cover = "assets.image25",
                music = R.raw.track25
            ),
            Music(
                id=26,
                name = "This Old Man",
                cover = "assets.image26",
                music = R.raw.track26
            ),
            Music(
                id=27,
                name = "Five Little Speckled Frogs",
                cover = "assets.image27",
                music = R.raw.track27
            ),
            Music(
                id=28,
                name = "Baa Baa Black Sheep",
                cover = "assets.image28",
                music = R.raw.track28
            ),
            Music(
                id=29,
                name = "Ring a Ring O' Roses",
                cover = "assets.image29",
                music = R.raw.track29
            ),
            Music(
                id=30,
                name = "With My Foot I Tap Tap Tap",
                cover = "assets.image30",
                music = R.raw.track30
            ),
            Music(
                id=31,
                name = "Jack and Jill",
                cover = "assets.image31",
                music = R.raw.track31
            ),
            Music(
                id=32,
                name = "The Alphabet Song",
                cover = "assets.image32",
                music = R.raw.track32
            ),
            Music(
                id=33,
                name = "Polly Put the Kettle On",
                cover = "assets.image33",
                music = R.raw.track33
            ),
            Music(
                id=34,
                name = "She'll Be Coming Round the Mountain",
                cover = "assets.image34",
                music = R.raw.track34
            ),
            Music(
                id=35,
                name = "One Finger One Thumb",
                cover = "assets.image35",
                music = R.raw.track35
            ),
            Music(
                id=36,
                name = "One Man Went to Mow",
                cover = "assets.image36",
                music = R.raw.track36
            ),
            Music(
                id=37,
                name = "London Bridge Is Falling Down",
                cover = "assets.image37",
                music = R.raw.track37
            ),
            Music(
                id=38,
                name = "The Muffin Man",
                cover = "assets.image38",
                music = R.raw.track38
            ),
            Music(
                id=39,
                name = "Five Little Ducks",
                cover = "assets.image39",
                music = R.raw.track39
            ),
            Music(
                id=40,
                name = "Pop! Goes the Weasel",
                cover = "assets.image40",
                music = R.raw.track40
            ),
            Music(
                id=41,
                name = "Here We Go Looby Loo",
                cover = "assets.image41",
                music = R.raw.track41
            ),
            Music(
                id=42,
                name = "Sing a Song of Sixpence",
                cover = "assets.image42",
                music = R.raw.track42
            ),
            Music(
                id=43,
                name = "Three Blind Mice",
                cover = "assets.image43",
                music = R.raw.track43
            ),
            Music(
                id=44,
                name = "The Grand Old Duke of York",
                cover = "assets.image44",
                music = R.raw.track44
            ),
            Music(
                id=45,
                name = "One Potato Two Potato",
                cover = "assets.image45",
                music = R.raw.track45
            ),
            Music(
                id=46,
                name = "Miss Polly Had a Dolly",
                cover = "assets.image46",
                music = R.raw.track46
            ),
            Music(
                id=47,
                name = "Open Shut Them",
                cover = "assets.image47",
                music = R.raw.track47
            ),
            Music(
                id=48,
                name = "Here We Go Round the Mulberry Bush",
                cover = "assets.image48",
                music = R.raw.track48
            ),
            Music(
                id=49,
                name = "Drunken Sailor",
                cover = "assets.image49",
                music = R.raw.track49
            ),
            Music(
                id=50,
                name = "Mary Had a Little Lamb",
                cover = "assets.image50",
                music = R.raw.track50
            )
        )
    }

    /***
     * Data class to represent a music in the list
     */
    data class Music(
        val id: Int,
        val name: String,
        val music: Int,
        val cover: String,
    )

}





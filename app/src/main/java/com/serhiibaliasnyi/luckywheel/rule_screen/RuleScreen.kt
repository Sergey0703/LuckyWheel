package com.serhiibaliasnyi.luckywheel.rule_screen

import android.media.AudioManager
import android.media.SoundPool
import android.net.Uri
import android.util.Log
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.serhiibaliasnyi.luckywheel.MainActivity
import com.serhiibaliasnyi.luckywheel.R
import com.serhiibaliasnyi.luckywheel.ui.theme.GreenBackground
import com.serhiibaliasnyi.luckywheel.ui.theme.GreenMain
import com.serhiibaliasnyi.luckywheel.ui.theme.MainActionColor
import com.serhiibaliasnyi.luckywheel.ui.theme.Red
import kotlinx.coroutines.delay
import java.util.Collections
import kotlin.math.roundToInt
import kotlin.time.Duration.Companion.seconds

@Composable
fun RuleScreen(sound: SoundPool?, composition:LottieComposition?, player: ExoPlayer, playList: List<MainActivity.Music>) {
    val quantytyOfSectors:Int=8


    var playListShuffle:MutableList<MainActivity.Music> = remember{
        mutableStateListOf<MainActivity.Music>()
       // Log.d("rul", "playListShuffle remember ")
    }
    //val data = remember {
    //    mutableStateListOf<Int>()
    //}
    Log.d("rul", "Recomposition "+playListShuffle.toList())
    val list = remember {
        mutableListOf<String>()
    }
    Log.d("rul", "RecompositionList "+list)
    var buttonText1:String by remember {
        mutableStateOf("")
    }
    var currentValue by remember { mutableStateOf(0L) }
    var isPlaying by remember { mutableStateOf(false) }

    val numberOfTrack = remember {
        mutableStateOf(-1)
    }

   // val playingSongIndex = remember {
   //     mutableIntStateOf(0)
   // }

    LaunchedEffect(numberOfTrack.value) {
       // Log.d("counter", "Launch1")
   //     playingSongIndex.intValue = numberOfTrack.value - 1
        //  player.seekTo(numberOfTrack.value-1, 0)
    }

    LaunchedEffect(Unit) {
      //   initSongs(playListShuffle,quantytyOfSectors,playList,player )
    /*
        playListShuffle.clear()
        getRandomElements(quantytyOfSectors,playList).forEach {
           playListShuffle.add(it)
       }

        Log.d("rul","Launch="+ playListShuffle.toList())

        playListShuffle.forEach {
            val path = "android.resource://" + "com.serhiibaliasnyi.luckywheel" + "/" + it.music
            val mediaItem = MediaItem.fromUri(Uri.parse(path))
            player.addMediaItem(mediaItem)
            list.add(it.name)

        }
        Log.d("rul","Launch2="+ playListShuffle.toList())
        Log.d("rul", "Launch2s="+list)
        player.prepare()
        */
    }

   // val currentPosition by remember {
   //     mutableLongStateOf(0)
   // }

   // val totalDuration by remember {
   //     mutableLongStateOf(0)
   // }



    DisposableEffect(Unit) {
        val listener = object : Player.Listener {
            override fun onIsPlayingChanged(isPlaying_: Boolean) {
                isPlaying = isPlaying_
            }
        }
        player.addListener(listener)
        onDispose {
            player.removeListener(listener)
        }
    }
    if (isPlaying) {
        LaunchedEffect(Unit) {
            while (true) {
                currentValue = player.currentPosition
              //  Log.d("rul", currentValue.toString())
                if(currentValue>5000){
                    player.pause()
                    Log.d("rul", "pause="+currentValue.toString())
                    currentValue =0;
                  //  currentPosition.longValue = 0
                //    if (player.isPlaying) {
                //    player.seekToNextMediaItem()
                 //   currentValue=0;
                 //   }
                }
                delay(1.seconds )
                Log.d("rul", "play="+currentValue.toString())
                // delay(1000 )
               // currentPosition.longValue = currentValue
               // sliderPosition.longValue = currentPosition.longValue
            }
        }
    }

    var isPlayingLottie by remember {
        mutableStateOf(false)
    }
    val animSpec= LottieClipSpec.Progress(
        0f,
        0.8f
    )
    var rotationValue by remember {
        mutableStateOf(0f)
    }

    var number by remember{
          mutableStateOf(0)
    }
    val angle: Float by animateFloatAsState(
        targetValue = rotationValue,
        animationSpec = tween(
            durationMillis = 2000,
            easing = LinearOutSlowInEasing
        ),
        finishedListener = {
             number=((360f-(it%360))/(360f/quantytyOfSectors)).toInt()+1
             Log.d("rul","Before song="+ playListShuffle.toList().toString())
             var song:MainActivity.Music=playListShuffle.get(number-1)
             Log.d("rul","Song="+ song.name)
          //   var newSongsUtil:MutableList<MainActivity.Music> = playListShuffle
          //       newSongsUtil.removeAt(number-1)
           //  Log.d("rul","Str="+ newSongsUtil.removeAt(number-1))
           //  Log.d("rul","After="+ newSongsUtil)
           //     newSongsUtil=getRandomElements(2,newSongsUtil)



           //  number = ((it + (360 * 2)) / (360 /quantytyOfSectors)).roundToInt() + 1
           // Log.d("rul","it="+it.toString() +" it%360="+it%360 +" (360f-(it%360))="+(360f-(it%360))+" number="+ number.toString())
          //   sound?.play(2, 1F, 1F, 0, 0, 1F)
          //   isPlayingLottie=true
           // firstLaunch.value = false;
            if (player.isPlaying) {
                player.pause()
            } else {

               // if (player.currentMediaItemIndex != numberOfTrack.value - 1) {
              //  if (numberOfTrack.value == 0) {
              //          numberOfTrack.value = 1
              //    player.seekTo(0, C.TIME_UNSET);
              //     } else {
              //    player.seekTo(numberOfTrack.value - 1, C.TIME_UNSET);
                // }
               // }


               // var newSongs=(0..quantytyOfSectors-1).random()
              //  numbers.removeAt(1)
               // buttonText1=list.get(number-1)
                buttonText1=song.name
                player.seekTo(number-1, C.TIME_UNSET);
                player.setPlayWhenReady(true);
                player.play()

            }
           
        }
    )


    Column(
        modifier=Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ){


        Text(modifier= Modifier
            .fillMaxWidth()
            .height(100.dp)
            .wrapContentWidth()
            .wrapContentHeight(),
            text=number.toString(),
            fontWeight = FontWeight.Bold,
            fontSize = 35.sp,
            color= Color.White
            )
        Box(modifier= Modifier
            .weight(1f)
            .fillMaxSize()){
            Image(
                //painter= painterResource(id = R.drawable.lucky_wheel_bg),
                painter= painterResource(id = R.drawable.rulette5),
                contentDescription = "lucky wheel",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
                    .rotate(angle)
            )
        }
//========================================================
        OutlinedButton(border= BorderStroke(2.dp, White),
            onClick = {
            sound?.play(1, 1F, 1F, 0, 0, 1F)
        },
            colors=ButtonDefaults.buttonColors(GreenMain),

            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ){
            Text(text=buttonText1,
                color= White,
                fontWeight = FontWeight.Bold,
                fontSize = 35.sp
            )
        }

        OutlinedButton(border= BorderStroke(2.dp, White),
        onClick = {
            sound?.play(1, 1F, 1F, 0, 0, 1F)
        },
            colors=ButtonDefaults.buttonColors(GreenBackground),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ){
            Text(text="Start",
                color= White,
                fontWeight = FontWeight.Bold,
                fontSize = 35.sp
            )
        }
        OutlinedButton(border= BorderStroke(2.dp, White),
            onClick = {
            sound?.play(1, 1F, 1F, 0, 0, 1F)
        },
            colors=ButtonDefaults.buttonColors(GreenMain),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ){
            Text(text="Start",
                color= White,
                fontWeight = FontWeight.Bold,
                fontSize = 35.sp
            )
        }

        OutlinedButton(border= BorderStroke(2.dp, White),
            onClick = {
            buttonText1=""
                initSongs(playListShuffle,quantytyOfSectors,playList,player )
            //Log.d("rul","playListShuffleButton="+playListShuffle)
            isPlayingLottie=false
            rotationValue=((0..360).random().toFloat()+720)+angle
         //   Log.d("rul", "angle="+(angle%360).toString() +" rotationValue "+rotationValue.toString())
            sound?.play(1, 1F, 1F, 0, 0, 1F)

            //numberOfTrack.value =numberOfTrack.value+1

        },
            colors=ButtonDefaults.buttonColors(GreenMain),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
            ){
            Text(text="Start",
                color= MainActionColor,
                fontWeight = FontWeight.Bold,
                fontSize = 35.sp
                )

        }
    }
    LottieAnimation(composition = composition,
        isPlaying = isPlayingLottie,
        speed = 1.5f,
        iterations = 2,
        clipSpec = animSpec
    )
}

fun  getRandomElements(amount:Int,  list:List<MainActivity.Music> ): MutableList<MainActivity.Music> {
     var returnList:ArrayList<MainActivity.Music> = ArrayList(list);
     var squeezeList:MutableList<MainActivity.Music> =list.toMutableStateList()
    Log.d("rul","In1="+squeezeList.toList())
       Collections.shuffle(returnList); // тут делаем рандом
    if (returnList.size > amount) { // отрезаем не нужную часть
        Log.d("rul", "delSize")
        // тут отрезаем не нужную часть
        //  list.subList(returnList.size - amount, returnList.size).clear()
       // list.subList(0, returnList.size)
        squeezeList= returnList.subList(0,amount)
    }
   // Log.d("rul","In="+list)
    Log.d("rul","In2="+squeezeList.toList())
        return squeezeList;
    }

fun initSongs(playListShuffle:MutableList<MainActivity.Music>,quantytyOfSectors:Int,playList:List<MainActivity.Music>,player:ExoPlayer ){
    // playListShuffle = getRandomElements(quantytyOfSectors,playList)
    playListShuffle.clear()
    getRandomElements(quantytyOfSectors,playList).forEach {
        playListShuffle.add(it)
    }
    // playListShuffle = playList as SnapshotStateList<MainActivity.Music>
    //playListShuffle = playList.toMutableStateList()
    //  playListShuffle.add(playList[0])

    Log.d("rul","Launch="+ playListShuffle.toList())
    //   Log.d("counter", "Launch0=" + player.currentMediaItemIndex.toString())
    //playList.forEach {
    player.clearMediaItems()
    playListShuffle.forEach {
        val path = "android.resource://" + "com.serhiibaliasnyi.luckywheel" + "/" + it.music
        val mediaItem = MediaItem.fromUri(Uri.parse(path))
        player.addMediaItem(mediaItem)
        //list.add(it.name)

    }
    Log.d("rul","Launch2="+ playListShuffle.toList())
    //Log.d("rul", "Launch2s="+list)
    player.prepare()
}
//fun  getRandomElementsString(amount:Int,  list:MutableList<String> ):MutableList<String> {
//    var returnList:ArrayList<String> = ArrayList(list);
//    var squeezeList:MutableList<String> =list
//
//    Collections.shuffle(returnList); // тут делаем рандом
//    if (returnList.size > amount) { // отрезаем не нужную часть
//        Log.d("rul", "delString")
//        // тут отрезаем не нужную часть
//        //  list.subList(returnList.size - amount, returnList.size).clear()
//        // list.subList(0, returnList.size)
//        squeezeList= returnList.subList(0,amount)
//    }
//    return squeezeList;
//}
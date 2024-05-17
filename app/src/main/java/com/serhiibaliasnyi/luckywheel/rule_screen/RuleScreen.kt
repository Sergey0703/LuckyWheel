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
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
    val musicDurationMs=10000;
    val alphaDisabled=0.5f
    val strokeWidth=3.dp;

    var visibleCount by remember{
        mutableStateOf(1f)
    }
    var visibleWinImage by remember{
        mutableStateOf(0f)
    }
    var alphaStartButton:Float by remember {
         mutableStateOf(1f)
     }
    var alphaButtons:Float by remember {
        mutableStateOf(alphaDisabled)
    }

    var playListShuffle:MutableList<MainActivity.Music> = remember{
        mutableStateListOf<MainActivity.Music>()
    }
    var  listUtilSongs:MutableList<MainActivity.Music> = remember{
        mutableStateListOf<MainActivity.Music>()
    }

    var songId:Int by remember{
        mutableStateOf(-1)
    }

    var winCount:Int by remember{
        mutableStateOf(0)
    }
    //val data = remember {
    //    mutableStateListOf<Int>()
    //}
    Log.d("rul", "Recomposition "+playListShuffle.toList())
    Log.d("rul", "RecompositionUtil "+listUtilSongs.toList())
    val list = remember {
        mutableListOf<String>()
    }

    var buttonText1:String by remember {
        mutableStateOf("")
    }


    var buttonText2:String by remember {
        mutableStateOf("")
    }
    var buttonText3:String by remember {
        mutableStateOf("")
    }

    var borderColour1:Int by remember {
        mutableStateOf(0)
    }
    var borderColour2:Int by remember {
        mutableStateOf(0)
    }
    var borderColour3:Int by remember {
        mutableStateOf(0)
    }
    var buttonTextStart:String by remember {
        mutableStateOf("Start")
    }
    var isButtonStartEnabled:Boolean by remember{
        mutableStateOf(true)
    }
    var isButtonsEnabled:Boolean by remember{
        mutableStateOf(false)
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
                if(currentValue>musicDurationMs){
                    player.pause()
                    Log.d("rul", "pause="+currentValue.toString())
                    currentValue =0;
                    isButtonStartEnabled=true
                    isButtonsEnabled=false
                    buttonTextStart="Start"
                    alphaStartButton=1f
                    alphaButtons=alphaDisabled
                    if(winCount>0) winCount--
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
             songId=song.id
           //  listUtilSongs=getUtilSongs(song, playList)
            listUtilSongs.clear()
             getUtilSongs( song, playList).forEach {
                 listUtilSongs.add(it)
             }
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
                buttonText1=listUtilSongs.get(0).name
                buttonText2=listUtilSongs.get(1).name
                buttonText3=listUtilSongs.get(2).name
                isButtonsEnabled=true
                alphaButtons=1f
                player.seekTo(number-1, C.TIME_UNSET);
                player.setPlayWhenReady(true);
                player.play()

            }
           
        }
    )
    if(winCount==5){
        visibleCount=0f
        visibleWinImage=1f
        isPlayingLottie=true
        winCount=0
        sound?.play(2, 1F, 1F, 0, 0, 1F)
        buttonTextStart="Start new Game"
    }

    Column(
        modifier=Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Box( modifier = Modifier,
            contentAlignment = Alignment.Center,

        ) {
            Image(
                alignment = Alignment.Center,
                painter= painterResource(id = R.drawable.crown),
                contentDescription = "crown",
                modifier = Modifier
                    .padding(0.dp, 20.dp, 0.dp, 0.dp)
                    .width(100.dp)
                    .height(100.dp)
                    .alpha(visibleWinImage)

            )

            Text(
                modifier = Modifier
                    //.fillMaxWidth()
                    .height(100.dp)
                    .padding(0.dp, 20.dp, 0.dp, 0.dp)
                   // .wrapContentWidth()
                   // .wrapContentHeight()

                    .alpha(visibleCount),
                textAlign = TextAlign.Center,
                text = winCount.toString(),
                fontWeight = FontWeight.Bold,
                fontSize = 35.sp,
                color = Color.White
            )

        }
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
        OutlinedButton(border= BorderStroke(strokeWidth,
            if (borderColour1==0){ White}
            else if(borderColour1==1) {
                MainActionColor}
            else{Color.Red}
        ),

            onClick = {
           // Log.d("rul", "listUtil="+listUtilSongs.toList().toString())
            player.pause()
            currentValue =0;
            buttonTextStart="Start"
            isButtonStartEnabled=true
            alphaStartButton=1f
            isButtonsEnabled=false
            alphaButtons=alphaDisabled
            if(songId==listUtilSongs.get(0).id){
                winCount++;
                borderColour1=1
            }else{
                if(winCount>0) winCount--
                borderColour1=2
            }

           },
            enabled = isButtonsEnabled,
            colors=ButtonDefaults.buttonColors(GreenMain),

            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .alpha(alphaButtons)
                //.border(
                //    width = 1.dp,
                //    color = if (true) Color.Red else Color.Gray,
                  //  shape = RoundedCornerShape(16.dp)
               // )
        ){
            Text(text=buttonText1,
                color= White,
                fontWeight = FontWeight.Bold,
                fontSize = 35.sp
            )
        }

        OutlinedButton(border= BorderStroke(strokeWidth,
            if (borderColour2==0){ White}
            else if(borderColour2==1) {
            MainActionColor}
            else{Color.Red}),
        onClick = {
            player.pause()
            currentValue =0;
            buttonTextStart="Start"
            alphaStartButton=1f
            isButtonStartEnabled=true
            isButtonsEnabled=false
            alphaButtons=alphaDisabled
            if(songId==listUtilSongs.get(1).id){
                winCount++;
                borderColour2=1
            }else{
                if(winCount>0) winCount--
                borderColour2=2
            }

        },
            enabled = isButtonsEnabled,
            colors=ButtonDefaults.buttonColors(GreenMain),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .alpha(alphaButtons)
        ){
            Text(text=buttonText2,
                color= White,
                fontWeight = FontWeight.Bold,
                fontSize = 35.sp
            )
        }
        OutlinedButton(border= BorderStroke(strokeWidth,
            if (borderColour3==0){ White}
            else if(borderColour3==1) {
                MainActionColor}
            else{Color.Red}),
            onClick = {
                player.pause()
                currentValue =0;
                buttonTextStart="Start"
                alphaStartButton=1f
                isButtonStartEnabled=true
                isButtonsEnabled=false
                alphaButtons=alphaDisabled
                if(songId==listUtilSongs.get(2).id){
                    winCount++;
                    borderColour3=1
                }else{
                    if(winCount>0) winCount--
                    borderColour3=2
                }

        },
            enabled = isButtonsEnabled,
            colors=ButtonDefaults.buttonColors(GreenMain),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .alpha(alphaButtons)
        ){
            Text(text=buttonText3,
                color= White,
                fontWeight = FontWeight.Bold,
                fontSize = 35.sp
            )
        }

        OutlinedButton(border= BorderStroke(strokeWidth, White),

            onClick = {
            visibleCount=1f
            visibleWinImage=0f
            buttonText1=""
            buttonText2=""
            buttonText3=""
            buttonTextStart=""
            alphaStartButton=alphaDisabled
            isButtonsEnabled=false
            alphaButtons=alphaDisabled
            isButtonStartEnabled=false
            songId=-1
            borderColour1=0
            borderColour2=0
            borderColour3=0
            initSongs(playListShuffle,quantytyOfSectors,playList,player )
            //Log.d("rul","playListShuffleButton="+playListShuffle)
            isPlayingLottie=false
            rotationValue=((0..360).random().toFloat()+720)+angle
         //   Log.d("rul", "angle="+(angle%360).toString() +" rotationValue "+rotationValue.toString())
            sound?.play(1, 1F, 1F, 0, 0, 1F)

            //numberOfTrack.value =numberOfTrack.value+1

        },
            enabled = isButtonStartEnabled,
            colors=ButtonDefaults.buttonColors(GreenMain),
           //modifier = if (true) Modifier else Modifier.alpha(0.5F)
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .alpha(alphaStartButton)
            ){
            Text(text=buttonTextStart,
                color= MainActionColor,
                fontWeight = FontWeight.Bold,
                fontSize = 35.sp
                )

        }
    }
    LottieAnimation(composition = composition,
        isPlaying = isPlayingLottie,
        speed = 1.5f,
        iterations = 3,
        clipSpec = animSpec
    )


}

fun  getUtilSongs(song : MainActivity.Music,list:List<MainActivity.Music> ): MutableList<MainActivity.Music> {

    var squeezeListUtil:MutableList<MainActivity.Music> =list.toMutableStateList()
    squeezeListUtil.remove(song)
    var returnListUtil:ArrayList<MainActivity.Music> = ArrayList(squeezeListUtil);
    //Log.d("rul","In1="+squeezeList.toList())
    Collections.shuffle(returnListUtil); // тут делаем рандом
    returnListUtil.add(0,song)
    if (returnListUtil.size > 3) { // отрезаем не нужную часть

        // тут отрезаем не нужную часть
        //  list.subList(returnList.size - amount, returnList.size).clear()
        // list.subList(0, returnList.size)
        Collections.shuffle(returnListUtil.subList(0,3))
        squeezeListUtil= returnListUtil

        // squeezeList= returnList.subList(returnList.size - amount, returnList.size)
    }
    // Log.d("rul","In="+list)
    Log.d("rul","InThree="+squeezeListUtil.toList())
    return squeezeListUtil;
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
       // squeezeList= returnList.subList(returnList.size - amount, returnList.size)
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
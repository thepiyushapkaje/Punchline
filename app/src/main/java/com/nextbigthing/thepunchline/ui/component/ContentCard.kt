package com.nextbigthing.thepunchline.ui.component

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nextbigthing.thepunchline.R
import com.nextbigthing.thepunchline.ui.theme.AppBackgroundColor
import com.nextbigthing.thepunchline.ui.theme.KanitBlack
import com.nextbigthing.thepunchline.ui.theme.PalletTint

@Composable
fun CenteredCard(
    title: String,
    description: String,
    onNextArticleClick: () -> Unit,
    context: Context
) {
    val clipboard =
        LocalContext.current.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Card(
            modifier = Modifier.padding(16.dp).background(AppBackgroundColor),
            onClick = onNextArticleClick
        ) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(Alignment.CenterVertically, false),
                    fontSize = if (title.length > 60) 32.sp else if (title.length > 40) 38.sp else 48.sp,
                    lineHeight = if (title.length > 60) 32.sp else if (title.length > 40) 38.sp else 48.sp,
                    textAlign = TextAlign.Left,
                    fontFamily = KanitBlack,
                    softWrap = true,
                    color = PalletTint
                )
                Text(
                    text = description,
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = if (title.length > 60) 26.sp else if (title.length > 40) 32.sp else 42.sp,
                    lineHeight = if (title.length > 60) 26.sp else if (title.length > 40) 32.sp else 42.sp,
                    textAlign = TextAlign.Left,
                    fontFamily = KanitBlack,
                    color = Color.Gray,
                    softWrap = true
                )
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp, 0.dp, 20.dp, 50.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    val textToCopy = title + "\n" + description
                    val clip = ClipData.newPlainText("label", textToCopy)
                    clipboard.setPrimaryClip(clip)
                    Toast.makeText(context, "Joke Copied Successfully", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier
                    .height(50.dp)
                    .weight(.2f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.copy),
                    tint = Color.Black,
                    contentDescription = "copy"
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(
                onClick = onNextArticleClick,
                modifier = Modifier
                    .height(50.dp) // Set only height
                    .weight(.8f), // Button takes 1/2 of remaining space
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
            ) {
                Text(
                    "Read Another Joke",
                    fontFamily = KanitBlack
                )
            }
        }

    }
}
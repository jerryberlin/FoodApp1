package com.example.food1.presentation.screen.home.component

import android.util.Log
import android.widget.TextView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.Coil
import coil.compose.rememberImagePainter
import com.example.food1.domain.model.Result
import com.example.food1.domain.model.ResultX
import com.example.food1.ui.theme.Shapes
import org.jsoup.Jsoup
import org.w3c.dom.Text

@Composable
fun RecipeListItem(
    result: ResultX,
    onItemClick: (ResultX) -> Unit
) {

    val painter = rememberImagePainter(result.image)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(200.dp)
            .clickable { onItemClick(result) },
        elevation = 4.dp,
        border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.3f)),
        shape = RoundedCornerShape(10.dp)
    ) {
       Row(
           modifier = Modifier.fillMaxSize()
       ) {
           Surface() {
               Image(
                   painter = painter,
                   contentDescription = "Image",
                   contentScale = ContentScale.Crop,
                   modifier = Modifier.size(width = 170.dp, height = 200.dp)
               )
           }
           Column(
               modifier = Modifier
                   .padding(5.dp)
                   .fillMaxSize()
                   .weight(1f)
           ) {
               Text(
                   text = result.title,
                   style = MaterialTheme.typography.subtitle1,
                   overflow = TextOverflow.Ellipsis,
                   maxLines = 2,
                   modifier = Modifier.weight(0.1f)
               )
               Text(
                   text = parseHtml(description = result.summary),
                   style = MaterialTheme.typography.subtitle2,
                   overflow = TextOverflow.Ellipsis,
                   modifier = Modifier.weight(0.3f)
               )
               Row() {

               }
           }

       }
    }

}

fun parseHtml(description: String): String{
    return Jsoup.parse(description).text()
}
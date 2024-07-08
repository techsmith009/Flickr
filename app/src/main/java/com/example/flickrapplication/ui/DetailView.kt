@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.flickrapplication.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.flickrapplication.R
import com.example.flickrapplication.model.DetailViewData

@Composable
fun DetailView(data : DetailViewData?, navController: NavController){
    val scrollState = rememberScrollState()

 Column(modifier = Modifier.verticalScroll(scrollState)) {
     CenterAlignedTopAppBar(title = { Text("FLICKR-IMAGES") },
         navigationIcon = {
                     IconButton(onClick = { navController.navigateUp() }) {
                         Icon(
                             imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                             contentDescription = "Back",
                             tint = MaterialTheme.colorScheme.secondary
                         )
                     }
         },
         colors = TopAppBarDefaults.topAppBarColors(
             containerColor = MaterialTheme.colorScheme.primaryContainer,
             titleContentColor = MaterialTheme.colorScheme.primary,
         ))
     data?.let {
         AsyncImage(
             model = data.media,
             contentDescription = "",
             contentScale = ContentScale.FillWidth,
             modifier = Modifier
                 .fillMaxWidth()
                 .wrapContentHeight()
         )
         Text(text = "${stringResource(id = R.string.title)} ", fontWeight = FontWeight.Bold )
         Text(text = data.title)

         Text(text = "${stringResource(id = R.string.author)} ", fontWeight = FontWeight.Bold)
         Text(text = data.author)

         Text(text = "${stringResource(id = R.string.description)} ", fontWeight = FontWeight.Bold)
         Text(text = data.description)

         Text(text = "${stringResource(id = R.string.publishDate)} ", fontWeight = FontWeight.Bold)
         Text(text = data.publishDate)
     }
 }
}
package com.example.flickrapplication.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flickrapplication.theme.FlickrApplicationTheme
import com.example.flickrapplication.viewmodel.FlickrViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.flickrapplication.R
import com.example.flickrapplication.model.Item

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GridView(
    viewModel : FlickrViewModel = viewModel(),
    navigateToDetail : (Item) -> Unit
) {
        Column {
            CenterAlignedTopAppBar(title = { Text("FLICKR-IMAGES") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ))
            SearchField(viewModel)
            viewModel.displayGridContents.collectAsState().value?.let { flickrContents ->
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Adaptive(130.dp),
                    verticalItemSpacing = 4.dp,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    content = {
                        items(flickrContents.items.size) { position ->
                            val item = flickrContents.items[position]
                            AsyncImage(
                                model = item.media?.m,
                                contentScale = ContentScale.Crop,
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                                    .clickable { navigateToDetail.invoke(item) }
                            )
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }
            viewModel.showProgressIndicator.collectAsState().value?.let {
                if(it){
                    IndeterminateCircularIndicator()
                }
            }
            viewModel.displayNetworkError.collectAsState().value.let{
                    DisplayErrorDialog(it)
            }
    }
}

@Composable
 fun SearchField(viewModel: FlickrViewModel){
    var textFieldValue by remember { mutableStateOf(TextFieldValue("")) }
    Column(modifier = Modifier.padding(top = 1.dp, bottom = 1.dp)) {
        OutlinedTextField(
            value = textFieldValue,
            label = { Text(text = stringResource(id = R.string.enter_text_here)) },
            trailingIcon = { Icon(imageVector =  Icons.Rounded.Search, contentDescription = "") },
            onValueChange = {
                textFieldValue = it
                viewModel.getFlickrContents(textFieldValue.text)
            }
        )
    }
 }

@Composable
fun IndeterminateCircularIndicator(){
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center){
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }
}

@Composable
private fun DisplayErrorDialog(value: Boolean){
    val openAlertDialog = remember { mutableStateOf(value) }
    if(openAlertDialog.value){
        AlertDialog(
            icon = { Icon(imageVector =  Icons.Rounded.Refresh, contentDescription = "")} ,
            title = { Text(text = stringResource(id = R.string.error_title))},
            text = { Text(text = stringResource(id = R.string.generic_error_message))},
            onDismissRequest = { openAlertDialog.value = false}, confirmButton = {},)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FlickrApplicationTheme {
        GridView(
            navigateToDetail = {}
        )
    }
}
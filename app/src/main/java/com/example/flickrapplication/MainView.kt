
package com.example.flickrapplication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.flickrapplication.model.DetailViewData
import com.example.flickrapplication.ui.DetailView
import com.example.flickrapplication.ui.GridView

@Composable
fun MainView(){

        Column(modifier = Modifier.padding(start = 4.dp, end = 4.dp)
        ) {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = NavGraphRoute.gridview.name
            ) {
                composable(route = NavGraphRoute.gridview.name){
                    GridView(navigateToDetail = { item ->
                        navController.navigate(route = "${NavGraphRoute.detailview.name}?title=${item.title}&media=${item.media?.m}&author=${item.author}&description=${item.description}&publishDate=${item.published}")
                    })
                }
                composable(route = "${NavGraphRoute.detailview.name}?title={title}&media={media}&author={author}&description={description}&publishDate={publishDate}"){
                        backStackEntry ->
                    val detailViewData = DetailViewData(
                        title =  backStackEntry.arguments?.getString("title").orEmpty(),
                        media =  backStackEntry.arguments?.getString("media").orEmpty(),
                        author = backStackEntry.arguments?.getString("author").orEmpty(),
                        description = backStackEntry.arguments?.getString("description").orEmpty(),
                        publishDate = backStackEntry.arguments?.getString("publishDate").orEmpty()
                    )
                    DetailView(detailViewData, navController)
                }
            }
        }
    }
package com.example.apitest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.apitest.model.Product
import com.example.apitest.ui.theme.APITestTheme
import com.valentinilk.shimmer.shimmer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            APITestTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    TestApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun TestApp(
    viewModel: ProductViewModel = hiltViewModel(),
) {
    val products:List<Product> by viewModel.products.observeAsState(arrayListOf())
    val isLoading:Boolean by viewModel.isLoading.observeAsState(false)
    var barcode by remember { mutableStateOf("3017624010701") }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("测试") },
                actions = {
                    IconButton(
                        onClick = {
                            viewModel.addProduct(barcode)
                        }
                    ) {
                        Icon(Icons.Filled.Add, "Add")
                    }
                    TextField(
                        value = barcode,
                        onValueChange = {
                            barcode = it
                        },
                        trailingIcon = @Composable {
                            Image(imageVector = Icons.Filled.Clear, // 清除图标
                                contentDescription = null,
                                modifier = Modifier.clickable { barcode = "" }) // 给图标添加点击事件，点击就清空text
                        },
                        placeholder = @Composable { Text(text = "This is placeholder") },
                    )
                }
            )
        }
    ) {
        LazyColumn {
            var itemCount = products.size
            if(isLoading) itemCount++

            items(count = itemCount){index ->
                var auxIndex = index
                if(isLoading){
                    if (auxIndex == 0){
                        return@items LoadingCard()
                    }
                    auxIndex--
                }
                val product = products[auxIndex]
                Card(
                    shape = RoundedCornerShape(8.dp),
                    elevation = 1.dp,
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .fillMaxWidth()
                ){
                    Row(modifier = Modifier.padding(8.dp)) {
                        Image(
                            modifier = Modifier.size(50.dp),
                            painter = rememberImagePainter(
                                data = product.image_url,
                                builder = {
                                    placeholder(R.drawable.ic_launcher_foreground)
                                    error(R.drawable.ic_launcher_foreground)
                                },
                            ),
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                        Spacer()
                        Column (
                            Modifier.weight(1f),
                        ){
                            Text(product.product_name)
                            Text(product.brands)
                        }
                        Spacer()
                        IconButton(
                            onClick = {
                                viewModel.deleteProduct(product)
                            }
                        ){
                            Icon(Icons.Filled.Delete,"delete")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LoadingCard(){
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 1.dp,
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .fillMaxWidth()
            .testTag("loadingCard")
    ){
        Row(modifier = Modifier.padding(8.dp)) {
            ImageLoading()
            Spacer()
            Column {
                Spacer()
                Box(modifier = Modifier.shimmer()){
                    Column {
                        Box(
                            modifier = Modifier
                                .height(15.dp)
                                .fillMaxWidth()
                                .background(Color.Gray)
                        )
                        Spacer()
                        Box(
                            modifier = Modifier
                                .height(15.dp)
                                .fillMaxWidth()
                                .background(Color.Gray)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Spacer(size:Int=8) = Spacer(modifier = Modifier.size(size.dp))

@Composable
fun ImageLoading(){
    Box(modifier = Modifier.shimmer()){
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(Color.Gray)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    APITestTheme {
    }
}
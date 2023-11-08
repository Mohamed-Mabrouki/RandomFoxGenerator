package com.example.ktor

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.ktor.ui.theme.KtorTheme
import io.ktor.client.request.get
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi

val client = Client().client

class MainActivity : ComponentActivity() {
	@OptIn(DelicateCoroutinesApi::class, ExperimentalCoroutinesApi::class)
	@SuppressLint("CoroutineCreationDuringComposition")
	override fun onCreate(savedInstanceState: Bundle?) {
		val viewModel = MainViewModel()

		super.onCreate(savedInstanceState)
		setContent {
			KtorTheme { // A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background,
				) {
					val image = viewModel.fox.collectAsState().value.image
					Card(
					    shape = CardDefaults.elevatedShape,
                        elevation = CardDefaults.cardElevation(4.dp),
						modifier = Modifier
							.fillMaxSize()
							.padding(10.dp),

					) {
						Column(
							horizontalAlignment = Alignment.CenterHorizontally,
							verticalArrangement = Arrangement.SpaceAround,
							modifier = Modifier.fillMaxSize(),
						) {
							SubcomposeAsyncImage(
								model = image,
								loading = {
									CircularProgressIndicator()
								},
								contentDescription = null,
								modifier = Modifier.clip(RoundedCornerShape(10.dp)),
							)
							Button(onClick = { viewModel.performBackgroundTask() }) {
								Text(text = "Generate")
							}
						}
					}
				}
			}
		}
	}
}

package com.example.ktor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
	private var _fox = MutableStateFlow<Fox>(Fox())
	var fox = _fox.asStateFlow()

	init {
		viewModelScope.launch(Dispatchers.IO) {
			performBackgroundTask()
		}
	}

	fun performBackgroundTask() { // Perform your background task (e.g., using a coroutine)
		viewModelScope.launch {
			val result = getFox()
			_fox.value = result
			fox = _fox.asStateFlow()
		}
	}

	private suspend fun getFox(): Fox {
		val response = client.get("https://randomfox.ca/floof/") {
			contentType(ContentType.Application.Json)
		}.body<Fox>()
		return response
	}
}

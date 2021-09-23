package ru.unit6.course.android.retrofit.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import ru.unit6.course.android.retrofit.data.api.ApiHelper
import ru.unit6.course.android.retrofit.data.api.RetrofitBuilder
import ru.unit6.course.android.retrofit.data.model.User
import ru.unit6.course.android.retrofit.data.repository.MainRepository


class UserViewModel : ViewModel() {
    private val apiHelper = ApiHelper(RetrofitBuilder.apiService)
    private val mainRepository: MainRepository = MainRepository(apiHelper)
    val liveData = MutableLiveData<User?>(null)


    fun loadDataOfUser(id: String) {
        viewModelScope.launch(IO) {
            liveData.postValue(mainRepository.getUser(id))

        }
    }
}
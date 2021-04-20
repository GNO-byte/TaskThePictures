package com.gno.taskthepictures.list

import androidx.lifecycle.MutableLiveData
import com.gno.taskthepictures.BaseViewModel
import com.gno.taskthepictures.retrofit.Api
import kotlinx.coroutines.launch

class ListViewModel : BaseViewModel() {

    val PictureListiveData = MutableLiveData<ArrayList<String>>()

    private var commonListData = ArrayList<String>()
    private var firstLoad = true

    fun getData() {
        scope.launch() {
            if (firstLoad) {
                commonListData.addAll(Api.retrofitService.getDataList())
                firstLoad = false
            }
            commonListData.addAll(Api.retrofitService.getDataList())

            var newListData: ArrayList<String> = ArrayList(commonListData)
            PictureListiveData.postValue(newListData)
        }
    }
}



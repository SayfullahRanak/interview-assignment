package com.setel.assignment.ui.restaurants

import androidx.lifecycle.MutableLiveData
import com.github.tamir7.contacts.Contact
import com.github.tamir7.contacts.Contacts
import com.setel.assignment.data.repo.MainRepo
import com.setel.utils.base.BaseViewModel
import com.setel.utils.tools.log
import com.setel.assignment.data.entities.Restaurant
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject


class RestaurantDetailViewModel @Inject constructor(
    private val mainRepo: MainRepo

) : BaseViewModel() {

    val error: MutableLiveData<String?> = MutableLiveData()
    val output: MutableLiveData<List<Contact>> = MutableLiveData()
    val restaurantList: MutableLiveData<List<Restaurant>> = MutableLiveData()


}
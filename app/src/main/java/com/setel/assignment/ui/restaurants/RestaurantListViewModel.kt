package com.setel.assignment.ui.restaurants

import androidx.lifecycle.MutableLiveData
import com.github.tamir7.contacts.Contact
import com.github.tamir7.contacts.Contacts
import com.setel.assignment.data.repo.MainRepo
import com.setel.utils.base.BaseViewModel
import com.setel.utils.tools.log
import com.setel.assignment.data.entities.Restaurant
import com.setel.assignment.data.entities.RestaurantList

import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject


class RestaurantListViewModel @Inject constructor(
    private val mainRepo: MainRepo

) : BaseViewModel() {

    val error: MutableLiveData<String?> = MutableLiveData()
    val output: MutableLiveData<List<Contact>> = MutableLiveData()
    val restaurantList: MutableLiveData<RestaurantList> = MutableLiveData()
    val currentTime : MutableLiveData<Int> = MutableLiveData()

    fun loadContacts(word: String?) {

        contactsLoader(word)
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe({ contacts ->
                contacts.size.toString().log("size___")
                output.postValue(contacts.sortedBy { it.displayName })
            }, {
                it.toString().log("size___")
                error.postValue(it.message)
            })

    }





    private fun contactsLoader(word: String?): Observable<List<Contact>> {
        val q = Contacts.getQuery()
        if (word != null)
            q.whereContains(Contact.Field.DisplayName, word)
        q.hasPhoneNumber()
        return Observable.fromCallable {
            q.find()
        }
    }


    fun getRestaurants() {

        compositeDisposable.add(
            mainRepo.getRestaurantList()
                .subscribeOn(io.reactivex.schedulers.Schedulers.io()).observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread()).subscribe(
                    {

                        if (it.isSuccessful) {
                            restaurantList.postValue(it.body())
//                            currentTime.postValue(it.body()?.timestamp)
                        } else {
                            error.postValue("Error Loading")
                        }
                    },
                    {
                        error.postValue(it.message)
                    }
                ))
    }

}
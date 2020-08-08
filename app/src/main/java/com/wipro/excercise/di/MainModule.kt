package com.wipro.excercise.di

import androidx.lifecycle.ViewModelProviders
import com.wipro.excercise.data.DataItem
import com.wipro.excercise.data.Response
import com.wipro.excercise.ui.main.MainFragment
import com.wipro.excercise.ui.main.MainViewModel
import dagger.Module
import dagger.Provides
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import kotlin.random.Random

@Module
class MainModule {

    @Provides
    fun getObservable(): Observable<Response> {
        return Observable.create<Response> {
            if (Random.nextInt(0, 2) == 0) {
                it.onNext(
                    Response(
                        "Title",
                        dummyList()
                    )
                )
            } else {
                it.onError(Throwable("Error"))
            }
        }.observeOn(AndroidSchedulers.mainThread())
            .delaySubscription(5, TimeUnit.SECONDS)
    }

    @Provides
    fun mainViewModel(fragment: MainFragment): MainViewModel {
        return ViewModelProviders.of(fragment).get(MainViewModel::class.java)
    }

    private fun dummyList(): List<DataItem> {
        return listOf(
            DataItem(
                "title",
                "description",
                "http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg"
            ),
            DataItem(
                "title",
                "description",
                "http://images.findicons.com/files/icons/662/world_flag/128/flag_of_canada.png"
            ),
            DataItem(
                "title",
                "description",
                "http://1.bp.blogspot.com/_VZVOmYVm68Q/SMkzZzkGXKI/AAAAAAAAADQ/U89miaCkcyo/s400/the_golden_compass_still.jpg"
            ),
            DataItem(
                "title",
                "description",
                "http://fyimusic.ca/wp-content/uploads/2008/06/hockey-night-in-canada.thumbnail.jpg"
            ),
            DataItem(
                "title",
                "description",
                "http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg"
            ),
            DataItem(
                "title",
                "description",
                "http://images.findicons.com/files/icons/662/world_flag/128/flag_of_canada.png"
            ),
            DataItem(
                "title",
                "description",
                "http://1.bp.blogspot.com/_VZVOmYVm68Q/SMkzZzkGXKI/AAAAAAAAADQ/U89miaCkcyo/s400/the_golden_compass_still.jpg"
            ),
            DataItem(
                "title",
                "description",
                "http://fyimusic.ca/wp-content/uploads/2008/06/hockey-night-in-canada.thumbnail.jpg"
            ),
            DataItem(
                "title",
                "description",
                "http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg"
            ),
            DataItem(
                "title",
                "description",
                "http://images.findicons.com/files/icons/662/world_flag/128/flag_of_canada.png"
            ),
            DataItem(
                "title",
                "description",
                "http://1.bp.blogspot.com/_VZVOmYVm68Q/SMkzZzkGXKI/AAAAAAAAADQ/U89miaCkcyo/s400/the_golden_compass_still.jpg"
            ),
            DataItem(
                "title",
                "description",
                "http://fyimusic.ca/wp-content/uploads/2008/06/hockey-night-in-canada.thumbnail.jpg"
            ),
            DataItem(
                "title",
                "description",
                "http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg"
            ),
            DataItem(
                "title",
                "description",
                "http://images.findicons.com/files/icons/662/world_flag/128/flag_of_canada.png"
            ),
            DataItem(
                "title",
                "description",
                "http://1.bp.blogspot.com/_VZVOmYVm68Q/SMkzZzkGXKI/AAAAAAAAADQ/U89miaCkcyo/s400/the_golden_compass_still.jpg"
            ),
            DataItem(
                "title",
                "description",
                "http://fyimusic.ca/wp-content/uploads/2008/06/hockey-night-in-canada.thumbnail.jpg"
            )
        )
    }
}
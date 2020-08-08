package com.wipro.excercise

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.wipro.excercise.data.DataItem
import com.wipro.excercise.data.Response
import com.wipro.excercise.ui.main.MainViewModel
import io.reactivex.Observable
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    private val testObject: MainViewModel = MainViewModel()

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Test
    fun testFetchData() {
        testObject.refresh(Observable.create {
            it.onNext(
                Response(
                    "Title",
                    listOf(
                        DataItem(
                            "title",
                            "description",
                            "http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg"
                        )
                    )
                )
            )
        }) {}
        assert(testObject.liveList.value?.rows?.size == 1)
    }

    @Test
    fun testFetchDataFailed() {
        testObject.refresh(Observable.create { it.onError(Throwable()) }) {}
        assert(testObject.liveList.value == null)
    }
}

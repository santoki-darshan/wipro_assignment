package com.wipro.excercise

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.wipro.excercise.data.DataItem
import com.wipro.excercise.data.Response
import com.wipro.excercise.networking.APIService
import com.wipro.excercise.ui.main.MainViewModel
import io.mockk.*
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    private val testObject: MainViewModel = MainViewModel()

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val apiService = mockk<APIService>()

    @get:Rule
    val rx = TrampolineSchedulerRule()

    @Test
    fun testFetchData() {
        every { apiService.fetchList() } returns Single.just(
            Response(
                "Title", listOf(
                    DataItem(
                        "title",
                        "description",
                        "http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg"
                    )
                )
            )
        )
        testObject.refresh(apiService) {}
        verify { apiService.fetchList() }
        assert(testObject.liveList.value?.rows?.size == 1)
    }

    @Test
    fun testFetchDataFailed() {
        every { apiService.fetchList() } returns Single.error(Throwable())
        val errorCallback = mockk<() -> Unit>()
        every { errorCallback.invoke() } just Runs
        testObject.refresh(apiService, errorCallback)
        verifyAll {
            apiService.fetchList()
            errorCallback.invoke()
        }
        assert(testObject.liveList.value == null)
    }
}

package com.wipro.excercise.di

import com.wipro.excercise.ui.main.MainFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [MainModule::class])
interface MainComponent {

    fun inject(mainFragment: MainFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance mainFragment: MainFragment): MainComponent
    }
}
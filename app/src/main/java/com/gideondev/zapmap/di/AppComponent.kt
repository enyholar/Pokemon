package com.gideondev.zapmap.di
import com.gideondev.zapmap.base.BaseActivity
import com.gideondev.zapmap.base.BaseFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        NetworkModule::class,
        RepositoryModule::class,
        ActivityViewModelModule::class,
        RxJavaModule::class
    ]
)
interface AppComponent {
    fun inject(baseActivity: BaseActivity)
    fun inject(fragment: BaseFragment)
}
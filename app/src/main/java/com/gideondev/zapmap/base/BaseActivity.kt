package com.gideondev.zapmap.base

import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.gideondev.zapmap.di.viewModelFactory.ViewModelFactory
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureDagger()
        configureDesign()
   //     onBackPressed()
    }
    private fun configureDagger() = (this.application as BaseApplication).appComponent.inject(this)


    abstract fun configureDesign()


}

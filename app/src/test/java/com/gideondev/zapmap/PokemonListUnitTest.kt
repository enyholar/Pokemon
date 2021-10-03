package com.gideondev.zapmap

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.os.Build
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.gideondev.zapmap.base.BaseTest
import com.gideondev.zapmap.model.PokeMonListResponse
import com.gideondev.zapmap.viewModel.PokeViewModel
import junit.framework.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.net.HttpURLConnection
import kotlin.test.assertEquals


/**
 * Created by Gideon Oyediran on 03/10/2021.
 * gideonoyediran@gmail.com
 */

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class PokemonListUnitTest : BaseTest() {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule() // Force tests to be executed synchronously

    // FOR DATA
    private lateinit var activity: FragmentActivity
    private lateinit var viewModel: PokeViewModel
    private val EXPECTED_RESULT_LIST = "20"


    // OVERRIDING
    override fun isMockServerEnabled(): Boolean = true

    @Before
    override fun setUp(){
        super.setUp()
        this.activity = Robolectric.setupActivity(FragmentActivity::class.java)
        this.viewModel = ViewModelProviders.of(this.activity, viewModelFactory)[PokeViewModel::class.java]
    }

    // TESTS
    @Test
    fun getPokemonList_whenSuccess() {
        // Prepare data
        this.mockHttpResponse("getfirst20OfPokemonData_whenSuccess.json", HttpURLConnection.HTTP_OK)
        // Pre-test
        assertEquals(null, this.viewModel.pokeMonListResponse.value, null)
        // Execute View Model
        this.viewModel.getPokeMonListFromServer(0,20)
        // Checks
        assertEquals("Pokemon list must be 20",EXPECTED_RESULT_LIST, this.viewModel.pokeMonListResponse.value?.pokemon?.size.toString(), )
        assertEquals("Should be reset to 'false' because stream ended",false, this.viewModel.isLoading.value, )
        assertEquals("No error must be founded", this.viewModel.errorMessage.value,null )
    }

    @Test
    fun getPokemonList_whenError(){
        // Prepare data
        this.mockHttpResponse("getfirst20OfPokemonData_whenSuccess.json", HttpURLConnection.HTTP_BAD_GATEWAY)
        // Pre-test
        assertEquals("Pokemon list should be null because stream not started yet",null, this.viewModel.pokeMonListResponse.value )
        // Execute View Model
        this.viewModel.getPokeMonListFromServer(0,0)
        // Checks
        assertEquals("Pokemon list must be null because of http error",null, this.viewModel.pokeMonListResponse.value)
        assertEquals(false, this.viewModel.isLoading.value, "Should be reset to 'false' because stream ended")
        assertNotEquals("Error value must not be empty",null, this.viewModel.errorMessage.value)
    }
}
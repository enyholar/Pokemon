package com.gideondev.zapmap.viewModel
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable


open class BaseViewModel : ViewModel() {

    val showProgress: ObservableField<Boolean> = ObservableField()
    val networkError: ObservableField<Boolean> = ObservableField()
    val commonMessage: MutableLiveData<String> = MutableLiveData()
    val dataLoaded: ObservableField<Boolean> = ObservableField()
    val isLoading: MutableLiveData<Boolean?> = MutableLiveData()
    val errorMessage: MutableLiveData<String?> = MutableLiveData()
  //  val user: MutableLiveData<User?> = MutableLiveData()
//    val registerResponse: MutableLiveData<RegisterResponse?> = MutableLiveData()
//    val loginResponse: MutableLiveData<LoginResponse?> = MutableLiveData()
//    val userResponse: MutableLiveData<UserBioResponse?> = MutableLiveData()
//    val allFoodMenuResponse: MutableLiveData<AllFoodMenuResponse?> = MutableLiveData()
//    val allFoodStoreResponse: MutableLiveData<AllFoodStoreResponse?> = MutableLiveData()
//    val allVendorCategoryResponse: MutableLiveData<List<VendorCategoryItem>?> = MutableLiveData()
//    val featureMenuResponse: MutableLiveData<FeatureMenuResponse?> = MutableLiveData()
//    val vendorDetailResponse: MutableLiveData<VendorDetailResponse?> = MutableLiveData()
//    val menuResponse: MutableLiveData<MenuResponse?> = MutableLiveData()
//    val vendorByCategoryIdResponse: MutableLiveData<MutableList<VendorByCategoryItem>?> = MutableLiveData()
//    val userOrderedByIdResponse: MutableLiveData<UserOrderedFoodResponse> = MutableLiveData()
//    val successResponse: MutableLiveData<SuccessResponse> = MutableLiveData()
//    val drinkCategoriesResponse: MutableLiveData<DrinkCategoriesResponse> = MutableLiveData()
//    val drinkResponse: MutableLiveData<DrinkResponse> = MutableLiveData()
//    val changePasswordResponse: MutableLiveData<ForgotPasswordResponse> = MutableLiveData()


    protected var disposable: CompositeDisposable = CompositeDisposable()


    override fun onCleared() {
        this.disposable.dispose()
        super.onCleared()
    }


    init {
        showProgress.set(false)
        networkError.set(false)
    }

    open fun tryAgainFunction() {

    }

    open fun showProgressBar() {
        showProgress.set(true)
    }

    open fun hideProgressBar() {
        showProgress.set(false)

    }

    open fun showConnectionError() {
        hideProgressBar()
        networkError.set(true)
    }

    open fun hideConnectionError() {
        networkError.set(false)
    }
}
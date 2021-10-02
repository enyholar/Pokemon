package com.gideondev.zapmap.network
import com.gideondev.zapmap.model.PokeMonListResponse
import com.gideondev.zapmap.model.details.PokemonDetailsResponse
import io.reactivex.Observable
import retrofit2.http.*


interface PokeApiInterface {

    @GET("pokemon")
    fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,

    ): Observable<PokeMonListResponse>

    @GET("pokemon/{id}")
    fun getPokemonDetails(
        @Path("id") id: Int,

    ): Observable<PokemonDetailsResponse>

//    @POST("register")
//    fun registerUser(
//        @Body request: RegisterRequest
//    ): Observable<RegisterResponse>
//
//
//    @POST("login")
//    fun loginUser(
//        @Body request: LoginRequest
//    ): Observable<LoginResponse>
//
//    @POST("fetchrestauranttypes")
//    fun getVendorCategory(
//        @Body request: VendorRequest
//    ): Observable<List<VendorCategoryItem>>
//
//    @POST("fetchallrestaurants")
//    fun getAllRestuarant(
//        @Body request: VendorRequest
//    ): Observable<MutableList<VendorByCategoryItem>>
//
//    @POST("addrating")
//    fun addRating(@Body request: RatingRequest
//    ): Observable<SuccessResponse>
//
//    @POST("fetchfoodstores")
//    fun getAllFoodStore(
//    ): Observable<AllFoodStoreResponse>
//
//    @POST("fetchfeaturedmenu")
//    fun getFetchFeatureMenu(
//    ): Observable<FeatureMenuResponse>
//
//    @POST("fetchmenubyid")
//    fun getFetchMenuById(
//        @Body request: MenuByIdRequest
//    ): Observable<MenuResponse>
//
//    @POST("fetchmenubyrestaurantid")
//    fun fetchVendorDetailByRestaurantId(
//        @Body request: MenuByRestaurantRequest
//    ): Observable<VendorDetailResponse>
//
//    @POST("fetchrestaurantbytype")
//    fun getVendorByCategory(
//        @Body request: VendorByTypeRequest
//    ): Observable<MutableList<VendorByCategoryItem>>
//
//    @POST(" fetchrestaurantbytypewithlimit")
//    fun getVendorByCategoryWithLimit(
//        @Body request: VendorByTypeRequest
//    ): Observable<MutableList<VendorByCategoryItem>>
//
//    @POST("fetchuserorder")
//    fun getOrderedHistoryById(
//        @Body request: UserOrderedRequest
//    ): Observable<UserOrderedFoodResponse>
//
//    @POST("adduserorder")
//    fun postUserOrderFood(
//        @Body request: FoodOrderRequest
//    ): Observable<SuccessResponse>
//
//    @POST("searchrestaurant")
//    fun searchForVendor(
//        @Body request: SearchRequest
//    ):Observable<MutableList<VendorByCategoryItem>>
//
//    @GET
//    fun verifyPayment(
//        @Header("Authorization") authorization: String?,
//        @Url url: String?
//    ): Call<PaymentResponse?>?
//
//    @POST
//    fun saveCard(
//        @Header("Content-Type") content_type: String?,
//        @Body request: SaveCardRequest?, @Url url: String?
//    ): Call<AddCardDetailsResponse?>?
//
//    @POST
//    fun getAllCard(
//        @Header("Content-Type") content_type: String?,
//        @Body request: GetUserCardRequest?, @Url url: String?
//    ): Call<UserCardResponse?>?
//
//    @POST
//    fun chargeRider(
//        @Header("Authorization") authorization: String?,
//        @Header("Content-Type") contentType: String?,
//        @Body paymentChargeRequest: PaymentChargeRequest?,
//        @Url url: String?
//    ): Call<ChargeRiderResponse?>?
//
//    @GET
//    fun getDistance(@Url url: String?): Call<LocationMapResponse?>?
//
//    @POST("fetchdrinkcategories")
//    fun fetchDrinkCategory(
//    ):Observable<DrinkCategoriesResponse>
//
//    @POST("fetchdrinkbycategoryswithlimit")
//    fun fetchDrinkByCategoryWithLimit(
//        @Body request: DrinkCategoryRequest?
//    ):Observable<DrinkResponse>
//
//    @POST("fetchalldrinksbycategory")
//    fun fetchDrinkByCategoryWithNoLimit(
//        @Body request: DrinkCategoryRequest?
//    ):Observable<DrinkResponse>
//
//    @POST("forgotpassword")
//    fun forgotPassword(
//        @Body request: ForgotPasswordRequest?
//    ):Observable<ForgotPasswordResponse>
//
//    @POST("editprofile")
//    fun editUserProfile(
//        @Body request: EditUserRequest?
//    ):Observable<ForgotPasswordResponse>
//
//    @POST("fetchuserbio")
//    fun fetchUserProfile(
//        @Body request: FetchUserRequest?
//    ):Observable<UserBioResponse>
}
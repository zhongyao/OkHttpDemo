package com.hongri.okhttpdemo.retrofit;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author：hongri
 * @date：3/21/22
 * @description： Retrofit调用示例
 */
public interface RetrofitApi {

    String BASE_URL = "www.shadow.com";
    /**
     * Get请求：
     * www.shadow.com/account/session
     */
    @GET("account/session")
    Call<ResponseBody> getSession();

    /**
     * Get请求【带参】：
     * www.shadow.com/account/book?pageIndex=1&pageSize=20"
     */
    @GET("account/book")
    Call<ResponseBody> getBook(@Query("pageIndex") String pageIndex, @Query("pageSize") String pageSize);

    /**
     * Get请求【带参】 -- 返回值是Observable：
     * www.shadow.com/account/book?pageIndex=1&pageSize=20"
     */
    @GET("account/book")
    Observable<ResponseBody> getHistoryBook(@Query("pageIndex") String pageIndex, @Query("pageSize") String pageSize);

    /**
     * Get请求【动态修改path】：
     * www.shadow.com/account/bookname"
     */
    @GET("account/{bookName}")
    Call<ResponseBody> getBookInfo(@Path("bookName") String bookName);

    /**
     * Get请求【Header赋值 -- 【固定值】单个】：
     * www.shadow.com/account/book
     */
    @Headers("Content-Type: javascript")
    @GET("account/book")
    Call<ResponseBody> getBook();

    /**
     * Get请求【Header赋值 -- 【固定值】多个】：
     * www.shadow.com/account/book
     */
    @Headers({"Content-Type: javascript", "User-Agent: YourAgent"})
    @GET("account/book")
    Call<ResponseBody> getBook2();

    /**
     * Get请求【Header赋值 -- 【动态赋值】多个】：
     * www.shadow.com/account/book
     */
    @GET("account/book")
    Call<ResponseBody> getBook3(@Header("Content-Type") String contentType, @Header("User-Agent") String userAgent);


    /**
     * Post请求【包含参数】
     * www.shadow.com/account/bookList
     */
    @FormUrlEncoded//[form]提交
    @POST("account/bookList") //等价于 @PUT("account/bookList")
    Call<ResponseBody> getBookList(@Field("page") String page, @Field("type") String type);

    /**
     * Post请求【包含参数/动态path】
     * www.shadow.com/account/bookName/bookList
     * @return
     */
    @FormUrlEncoded//[form]提交
    @POST("account/{bookName}/bookList")
    Call<ResponseBody> getBookList(@Path("bookName") String bookName, @Field("page") String page, @Field("type") String type);

    /**
     * Post实现表单[form]提交:
     * www.shadow.com/account/bookName/bookList
     */
    @FormUrlEncoded
    @POST("account/bookName/bookList")
    Call<ResponseBody> getBookList(@FieldMap Map<String, Object> map);

    /**
     * Post实现Json提交
     */
    @POST("account/bookName/bookList")
    Call<ResponseBody> getBookListPost(@Body Map<String, Object> map);

}

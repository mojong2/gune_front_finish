package com.example.test30

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface BoardInterface {

    @FormUrlEncoded
    @POST("InsertBoard.php")
    fun insertBoard(
        @Field("boardTitle") boardTitle: String,
        @Field("boardContent") boardContent: String,
        @Field("boardType") boardType: String,
        @Field("boardStatus") boardStatus: String,
        @Field("userId") userId: String,
        @Field("boardCrtu") boardCrtu: String,
        @Field("boardCrtd") boardCrtd: String
    ): Call<String>

    @GET("SelectBoardListImp.php")
    fun selectBoardListImp(

    ): Call<String>

    @FormUrlEncoded
    @POST("SelectBoardListNor.php")
    fun selectBoardListNor(
        @Field("DAY") DAY: String
    ): Call<String>

}
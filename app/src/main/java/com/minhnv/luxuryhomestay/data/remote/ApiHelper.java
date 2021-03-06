package com.minhnv.luxuryhomestay.data.remote;

import com.minhnv.luxuryhomestay.data.model.Booking;
import com.minhnv.luxuryhomestay.data.model.City;
import com.minhnv.luxuryhomestay.data.model.Comment;
import com.minhnv.luxuryhomestay.data.model.Favorite;
import com.minhnv.luxuryhomestay.data.model.Homestay;
import com.minhnv.luxuryhomestay.data.model.HomestayPrice;
import com.minhnv.luxuryhomestay.data.model.ImageDetail;
import com.minhnv.luxuryhomestay.data.model.ListVinHomes;
import com.minhnv.luxuryhomestay.data.model.Luxury;
import com.minhnv.luxuryhomestay.data.model.Story;
import com.minhnv.luxuryhomestay.data.model.UserInfo;
import com.minhnv.luxuryhomestay.data.model.UserResponse;
import com.minhnv.luxuryhomestay.data.model.VinHome;

import java.util.List;

import io.reactivex.Observable;

public interface ApiHelper {
    Observable<String> doServerSignUp(UserResponse.ServerSignUpRequest request);

    Observable<String> doServerSignIn(UserResponse.ServerSignInRequest request);

    Observable<List<Homestay>> doLoadHomeStay(UserResponse.ServerListHomeStays request);

    Observable<List<City>> doLoadCity();

    Observable<List<Homestay>> doLoadListHomeStayRating(UserResponse.ServerListHomeStaysRating request);

    Observable<List<HomestayPrice>> doLoadListHomeStayPriceAsc();

    Observable<List<Homestay>> doSearchHomeStayFollowRating(UserResponse.ServerSearchHomeStaysFollowRating rating);

    Observable<String> doServerBooking(UserResponse.ServerBooking booking);

    Observable<List<Booking>> doLoadListBooking(UserResponse.ServerListBooking booking);

    Observable<String> doServerDeleteBooking(UserResponse.ServerDeleteBooking booking);

    Observable<List<Luxury>> doLoadListLuxury();

    Observable<String> doServerPostFavorite(UserResponse.ServerAddFavorite favorite);

    Observable<List<Favorite>> doLoadListFavorite(UserResponse.ServerListBooking favorite);

    Observable<String> doDeleteFavorite(UserResponse.ServerDeleteBooking favorite);

    Observable<String> doAddLoveLuxury(UserResponse.ServerAddLove love);

    Observable<List<VinHome>> doLoadCityVinHomes();

    Observable<List<ListVinHomes>> doLoadListHomeStayVinHomes(UserResponse.ServerLoadHomeStayVinHomes homes);

    Observable<List<Story>> doLoadListStory();

    Observable<String> doDeleteStories();

    Observable<List<ImageDetail>> doLoadListImageDetail(UserResponse.ServerGetImageDetail detail);

    Observable<List<UserInfo>> doLoadInformationUser(UserResponse.ServerGetUser user);

    Observable<String> doAddCommentHomestay(UserResponse.ServerComment comment);

    Observable<List<Comment>> doGetListCmt(UserResponse.ServerGetListCmt cmt);
}

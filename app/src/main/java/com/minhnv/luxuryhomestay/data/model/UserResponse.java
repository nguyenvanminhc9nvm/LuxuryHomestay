package com.minhnv.luxuryhomestay.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserResponse {
    public static class ServerSignUpRequest {
        @Expose
        @SerializedName("password")
        private String password;
        @Expose
        @SerializedName("phonenumber")
        private String phonenumber;
        @Expose
        @SerializedName("address")
        private String address;

        public ServerSignUpRequest(String password, String phonenumber, String address) {
            this.password = password;
            this.phonenumber = phonenumber;
            this.address = address;
        }
    }

    public static class ServerSignInRequest {
        @Expose
        @SerializedName("phonenumber")
        private String phonenumber;
        @Expose
        @SerializedName("password")
        private String password;

        public ServerSignInRequest(String phonenumber, String password) {
            this.phonenumber = phonenumber;
            this.password = password;
        }
    }

    public static class ServerListHomeStays {
        @Expose
        @SerializedName("idhomestay")
        private Integer idhomestay;

        public ServerListHomeStays(Integer idhomestay) {
            this.idhomestay = idhomestay;
        }
    }

    public static class ServerListHomeStaysRating {
        @Expose
        @SerializedName("rating")
        private Integer rating;

        public ServerListHomeStaysRating(Integer rating) {
            this.rating = rating;
        }
    }

    public static class ServerSearchHomeStaysFollowRating {
        @Expose
        @SerializedName("rating")
        private int rating;

        public ServerSearchHomeStaysFollowRating(int rating) {
            this.rating = rating;
        }
    }

    public static class ServerBooking {
        @SerializedName("datestart")
        private String dateStart;
        @SerializedName("dateend")
        private String dateEnd;
        @SerializedName("countmember")
        private String countMember;
        @SerializedName("namehomestay")
        private String nameHomeStay;
        @SerializedName("address")
        private String address;

        public ServerBooking(String dateStart, String dateEnd, String countMember, String nameHomeStay, String address) {
            this.dateStart = dateStart;
            this.dateEnd = dateEnd;
            this.countMember = countMember;
            this.nameHomeStay = nameHomeStay;
            this.address = address;
        }
    }

    public static class ServerDeleteBooking{
        @SerializedName("id")
        private Integer id;

        public ServerDeleteBooking(Integer id) {
            this.id = id;
        }
    }

    public static class ServerPostLuxury{
        @SerializedName("image")
        private String image;
        @SerializedName("detail")
        private String detail;
        @SerializedName("username")
        private String username;
        @SerializedName("address")
        private String address;

        public ServerPostLuxury(String image, String detail, String username, String address) {
            this.image = image;
            this.detail = detail;
            this.username = username;
            this.address = address;
        }
    }

    public static class ServerAddFavorite{
        @SerializedName("namehomestay")
        private String nameHomeStay;
        @SerializedName("image")
        private String image;
        @SerializedName("address")
        private String address;

        public ServerAddFavorite(String nameHomeStay, String image, String address) {
            this.nameHomeStay = nameHomeStay;
            this.image = image;
            this.address = address;
        }
    }

    public static class ServerAddLove{
        @SerializedName("id")
        private Integer id;
        @SerializedName("countlove")
        private Integer countLove;

        public ServerAddLove(Integer id, Integer countLove) {
            this.id = id;
            this.countLove = countLove;
        }
    }

    public static class ServerLoadHomeStayVinHomes{
        @SerializedName("idvinhomes")
        private Integer id;

        public ServerLoadHomeStayVinHomes(Integer id) {
            this.id = id;
        }
    }

}

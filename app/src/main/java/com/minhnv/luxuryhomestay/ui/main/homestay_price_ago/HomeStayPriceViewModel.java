package com.minhnv.luxuryhomestay.ui.main.homestay_price_ago;

import android.util.Log;

import com.minhnv.luxuryhomestay.data.DataManager;
import com.minhnv.luxuryhomestay.data.model.HomestayPrice;
import com.minhnv.luxuryhomestay.ui.base.BaseViewModel;
import com.minhnv.luxuryhomestay.utils.AppLogger;
import com.minhnv.luxuryhomestay.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class HomeStayPriceViewModel extends BaseViewModel<HomeStayPriceNavigator> {
    private static final String TAG = "HomeStayPriceViewModel";
    public List<HomestayPrice> list;
    public  Observable<List<HomestayPrice>> listObservable;
    public PublishSubject<List<HomestayPrice>> listPublishSubject = PublishSubject.create();
    public HomeStayPriceViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
         listObservable = listPublishSubject.share();

    }
    public void loadListHomeStaysPriceAsc() {
        getCompositeDisposable().add(
                getDataManager().doLoadListHomeStayPriceAsc()
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(response -> {
                            list = new ArrayList<>(response);
                            listPublishSubject.onNext(response);
                            getNavigator().onSuccess();
                        }, throwable -> {
                            getNavigator().HandlerError(throwable);
                            AppLogger.d(TAG, "loadListHomeStayRating: " + throwable);
                        })
        );
    }

    public void ServerLoadHomeStaysPriceAsc(){
        getNavigator().doLoadHomeStaysPriceAsc();
    }
}

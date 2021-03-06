package com.minhnv.luxuryhomestay.ui.main.favorite;

import com.minhnv.luxuryhomestay.data.DataManager;
import com.minhnv.luxuryhomestay.data.model.Favorite;
import com.minhnv.luxuryhomestay.data.model.UserResponse;
import com.minhnv.luxuryhomestay.ui.base.BaseViewModel;
import com.minhnv.luxuryhomestay.utils.AppLogger;
import com.minhnv.luxuryhomestay.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class FavoriteViewModel extends BaseViewModel<FavoriteNavigator> {
    private static final String TAG = "FavoriteViewModel";
    private List<Favorite> list;
    public BehaviorSubject<List<Favorite>> listBehaviorSubject = BehaviorSubject.create();
    public FavoriteViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        Observable<List<Favorite>> listObservable = listBehaviorSubject.share();
    }
    public void loadFavorite(int idUser){
        getCompositeDisposable().add(
                getDataManager().doLoadListFavorite(new UserResponse.ServerListBooking(idUser))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    list = new ArrayList<>(response);
                    listBehaviorSubject.onNext(response);
                    getNavigator().onSuccess();
                    AppLogger.d(TAG, "loadFavorite: "+response);
                },throwable -> {
                    getNavigator().HandlerError(throwable);
                    AppLogger.d(TAG, "loadFavorite: "+throwable);
                })
        );
    }

    public void deleteFavorite(Integer id){
        getCompositeDisposable().add(
                getDataManager().doDeleteFavorite(new UserResponse.ServerDeleteBooking(id))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if(response.equals("success")) {
                        getNavigator().onDeleteComplete();
                    }else if(response.equals("error")){
                        getNavigator().onFailed();
                    }
                },throwable -> {
                    getNavigator().HandlerError(throwable);
                })
        );
    }

    public void ServerLoadFavorite(){
        getNavigator().ServerLoadListFavorite();
    }
}

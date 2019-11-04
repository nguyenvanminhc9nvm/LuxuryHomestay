package com.minhnv.luxuryhomestay.ui.main.social.list;

import android.util.Log;

import com.minhnv.luxuryhomestay.data.DataManager;
import com.minhnv.luxuryhomestay.data.model.Luxury;
import com.minhnv.luxuryhomestay.data.model.Story;
import com.minhnv.luxuryhomestay.data.model.UserResponse;
import com.minhnv.luxuryhomestay.ui.base.BaseViewModel;
import com.minhnv.luxuryhomestay.ui.base.ViewModelType;
import com.minhnv.luxuryhomestay.utils.AppLogger;
import com.minhnv.luxuryhomestay.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class SocialViewModel extends BaseViewModel<SocialNavigator> {

    private static final String TAG = "SocialViewModel";
    public BehaviorSubject<List<Luxury>> listPublishSubject = BehaviorSubject.create();
    public BehaviorSubject<List<Story>> listBehaviorSubject = BehaviorSubject.create();

    public SocialViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        Observable<List<Luxury>> listObservable = listPublishSubject.share();
        Observable<List<Story>> listObservable1 = listBehaviorSubject.share();
    }

    public void luxuryList() {
        getCompositeDisposable().add(
                getDataManager().doLoadListLuxury()
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(response -> {
                            List<Luxury> luxuries = new ArrayList<>(response);
                            listPublishSubject.onNext(response);
                            AppLogger.d(TAG, "luxuryList: " + response);
                        }, throwable -> {
                            getNavigator().HandlerError(throwable);
                        })
        );
    }

    public void ServerLoadListLuxury() {
        getNavigator().loadList();
    }

    public void addLoveForPost(Integer id, Integer countLove) {
        getCompositeDisposable().add(
                getDataManager().doAddLoveLuxury(new UserResponse.ServerAddLove(id, countLove))
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(response -> {
                            if (response.equals("Success")) {
                                AppLogger.d(TAG, "addLoveForPost: " + response);
                            }
                        }, throwable -> {
                            getNavigator().HandlerError(throwable);
                            AppLogger.d(TAG, "addLoveForPost: " + throwable);
                        })
        );
    }

    public void loadListStory() {
        getCompositeDisposable().add(
                getDataManager().doLoadListStory()
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(response -> {
                            listBehaviorSubject.onNext(response);
                        }, throwable -> {
                            AppLogger.d(TAG, throwable);
                        })
        );
    }

    public void deleteStories() {
        getCompositeDisposable().add(
                getDataManager().doDeleteStories()
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(
                                response -> {
                                    if (response.equals("Success")) {
                                        getNavigator().onSuccess();
                                    } else if (response.equals("Failed")) {
                                        getNavigator().onFailed();
                                    }
                                },
                                throwable -> {
                                    AppLogger.d(TAG, throwable);
                                })
        );
    }

    public void ServerDeleteStories() {
        getNavigator().deleteStories();
    }

    public void ServerLoadListStory() {
        getNavigator().loadStory();
    }

}

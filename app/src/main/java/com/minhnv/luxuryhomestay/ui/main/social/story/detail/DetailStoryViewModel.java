package com.minhnv.luxuryhomestay.ui.main.social.story.detail;

import com.minhnv.luxuryhomestay.data.DataManager;
import com.minhnv.luxuryhomestay.ui.base.BaseViewModel;
import com.minhnv.luxuryhomestay.utils.rx.SchedulerProvider;

public class DetailStoryViewModel extends BaseViewModel<DetailStoryNavigator> {
    public DetailStoryViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}

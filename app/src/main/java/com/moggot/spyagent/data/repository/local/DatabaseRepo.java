package com.moggot.spyagent.data.repository.local;

import android.support.annotation.NonNull;

import com.moggot.spyagent.data.local.UserDao;
import com.moggot.spyagent.data.model.SelfModel;
import com.moggot.spyagent.data.model.TopOfLikes;
import com.moggot.spyagent.data.repository.DataRepoImpl;
import com.moggot.spyagent.data.repository.preference.PreferenceRepo;

import java.util.concurrent.Executor;

import io.reactivex.Single;
import timber.log.Timber;

public class DatabaseRepo implements DataRepoImpl {

    @NonNull
    private UserDao userDao;
    @NonNull
    private Executor executor;
    @NonNull
    private PreferenceRepo preferenceRepo;

    public DatabaseRepo(@NonNull UserDao userDao, @NonNull PreferenceRepo preferenceRepo, @NonNull Executor executor) {
        this.userDao = userDao;
        this.executor = executor;
        this.preferenceRepo = preferenceRepo;
    }

    @Override
    public Single<SelfModel> getSelfInfo() {
        Timber.d("Get selfInfo from DB");
        return userDao.getSelfInfo();
    }

    @Override
    public Single<TopOfLikes> getSelfTopLikes(@SuppressWarnings("unused") int topCount) {
        return Single.fromCallable(TopOfLikes::new);
    }

    public void saveCredentials(SelfModel selfModel) {
        preferenceRepo.saveUser(selfModel);
        executor.execute(() -> userDao.saveCredentials(selfModel));
    }

    public void updateSelfInfo(SelfModel selfModel) {
        executor.execute(() -> {
            long userId = preferenceRepo.getUser().getUserId();
            int id = userDao.getId(userId);
            selfModel.setId(id);
            selfModel.setUserId(userId);
            String accessToken = preferenceRepo.getUser().getAccessToken();
            selfModel.setAccessToken(accessToken);
            userDao.updateSelfInfo(selfModel);
        });
    }

    public void storeTopLikes(TopOfLikes topOfLikes) {

    }
}

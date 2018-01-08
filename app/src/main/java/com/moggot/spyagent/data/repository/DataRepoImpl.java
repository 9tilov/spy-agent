package com.moggot.spyagent.data.repository;

import com.moggot.spyagent.data.model.SelfResponseModel;
import com.moggot.spyagent.data.model.TopOfLikes;

import io.reactivex.Single;

public interface DataRepoImpl {

    Single<SelfResponseModel> getSelfInfo();

    Single<TopOfLikes> getSelfTopLikes(int topCount);
}

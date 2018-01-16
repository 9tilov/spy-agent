package com.moggot.spyagent.data.repository;

import com.moggot.spyagent.data.model.SelfModel;
import com.moggot.spyagent.data.model.TopOfLikes;

import io.reactivex.Maybe;
import io.reactivex.Single;

public interface DataRepoImpl {

    Single<SelfModel> getSelfInfo();

    Single<TopOfLikes> getSelfTopLikes(int topCount);
}

package com.moggot.spyagent.data.repository;

import com.moggot.spyagent.data.model.SelfResponseModel;
import com.moggot.spyagent.data.model.UserResponseModel;

import io.reactivex.Single;

public interface DataRepoImpl {

    Single<SelfResponseModel> getSelfInfo();
}

package com.moggot.spyagent.presentation.home;

import com.moggot.spyagent.data.model.SelfResponseModel;
import com.moggot.spyagent.data.model.TopOfLikes;
import com.moggot.spyagent.presentation.common.BaseView;

public interface HomeView extends BaseView {

    void showSelfInfo(SelfResponseModel selfModel);

    void showListTopLikes(TopOfLikes topOfLikes);
}

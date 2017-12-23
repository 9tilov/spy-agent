package com.moggot.spyagent.presentation.self;

import com.moggot.spyagent.data.model.UserResponseModel;
import com.moggot.spyagent.presentation.common.BaseView;

public interface SelfView extends BaseView {

    void showSelfInfo(UserResponseModel selfModel);
}

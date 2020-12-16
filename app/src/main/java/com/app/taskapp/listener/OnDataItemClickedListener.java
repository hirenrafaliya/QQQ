package com.app.taskapp.listener;

import com.app.taskapp.model.Data;

public interface OnDataItemClickedListener {

    void onDataItemClicked(Data data, int position);

}
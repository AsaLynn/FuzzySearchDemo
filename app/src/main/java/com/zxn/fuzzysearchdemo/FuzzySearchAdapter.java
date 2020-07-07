package com.zxn.fuzzysearchdemo;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseViewHolder;
import com.zxn.fuzzysearch.FuzzySearchBaseAdapter;
import com.zxn.fuzzysearch.IFuzzySearchRule;

import java.util.List;

public class FuzzySearchAdapter extends FuzzySearchBaseAdapter<ItemEntity, FuzzySearchAdapter.ItemHolder> {

    public FuzzySearchAdapter(List<ItemEntity> dataList) {
        super(null, dataList, R.layout.item_adapter);
    }

    public FuzzySearchAdapter(IFuzzySearchRule rule, List<ItemEntity> dataList) {
        super(rule, dataList, R.layout.item_adapter);
    }
//    public FuzzySearchAdapter() {
//        super(null);
//    }
//
//    public FuzzySearchAdapter(IFuzzySearchRule rule) {
//        super(rule);
//    }
//
//    public FuzzySearchAdapter(List<ItemEntity> dataList) {
//        super(null, dataList);
//    }

    @Override
    protected void convert(@NonNull ItemHolder helper, ItemEntity item) {
        TextView mTextName = helper.getView(R.id.text_item_name);
        mTextName.setText(item.getValue());

        //helper.mTextName.setText(item.getValue());
    }

//    public FuzzySearchAdapter(IFuzzySearchRule rule, List<ItemEntity> dataList) {
//        super(rule, dataList);
//    }

    static class ItemHolder extends BaseViewHolder {

//        public TextView mTextName;
//
        ItemHolder(View itemView) {
            super(itemView);
            //mTextName = itemView.findViewById(R.id.text_item_name);
        }
    }

}

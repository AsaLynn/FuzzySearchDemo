package com.zxn.fuzzysearch;

import android.text.TextUtils;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Updated by zxn on 2020/7/7.
 */
public abstract class FuzzySearchBaseAdapter<ITEM extends IFuzzySearchItem, K extends BaseViewHolder>
        extends BaseQuickAdapter<ITEM, K> implements Filterable {

    private FuzzySearchFilter mFilter;
    private List<ITEM> mBackDataList;
    private IFuzzySearchRule mIFuzzySearchRule;

    public FuzzySearchBaseAdapter(IFuzzySearchRule rule, List<ITEM> dataList, int layoutResId) {
        super(layoutResId, dataList);
        if (rule == null) {
            mIFuzzySearchRule = new DefaultFuzzySearchRule();
        }
        mBackDataList = dataList;
    }

    @Override
    public void setNewData(@Nullable List<ITEM> data) {
        super.setNewData(data);
        mBackDataList = data;
    }

    @Override
    public Filter getFilter() {
        if (mFilter == null) {
            mFilter = new FuzzySearchFilter();
        }
        return mFilter;
    }

    private class FuzzySearchFilter extends Filter {

        /**
         * 执行过滤操作,如果搜索的关键字为空，默认所有结果
         */
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults result = new FilterResults();
            List<ITEM> filterList;
            if (TextUtils.isEmpty(constraint)) {
                filterList = mBackDataList;
            } else {
                filterList = new ArrayList<>();
                for (ITEM item : mBackDataList) {
                    if (mIFuzzySearchRule.accept(constraint, item.getSourceKey(), item.getFuzzyKey())) {
                        filterList.add(item);
                    }
                }
            }
            result.values = filterList;
            result.count = filterList.size();
            return result;
        }

        /**
         * 得到过滤结果
         */
        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mData = (List<ITEM>) results.values;
            notifyDataSetChanged();
        }
    }

}

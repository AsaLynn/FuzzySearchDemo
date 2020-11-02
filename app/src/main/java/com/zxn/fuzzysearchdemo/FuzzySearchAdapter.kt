package com.zxn.fuzzysearchdemo

import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zxn.fuzzysearch.FuzzySearchBaseAdapter
import com.zxn.fuzzysearch.IFuzzySearchRule
import com.zxn.fuzzysearchdemo.FuzzySearchAdapter.ItemHolder

class FuzzySearchAdapter : FuzzySearchBaseAdapter<ItemEntity, ItemHolder> {

    constructor(dataList: MutableList<ItemEntity>?) : super(null, dataList, R.layout.item_adapter)

    constructor(rule: IFuzzySearchRule?, dataList: MutableList<ItemEntity>?) : super(rule, dataList, R.layout.item_adapter)

    override fun convert(helper: ItemHolder, item: ItemEntity) {
        val mTextName = helper.getView<TextView>(R.id.text_item_name)
        mTextName.text = item.value
    }

    class ItemHolder(itemView: View?) : BaseViewHolder(itemView!!)
}
package com.zxn.fuzzysearchdemo

import com.zxn.fuzzysearch.IFuzzySearchItem
/**
 *  Updated by zxn on 2020/11/2.
 */
class ItemEntity(
    val value: String,
    private val mSortLetters: String,
    private val mFuzzySearchKey: List<String>
) : IFuzzySearchItem {
    override val sourceKey: String?
        get() = value
    override val fuzzyKey: List<String?>?
        get() = mFuzzySearchKey

//    fun getSourceKey(): String {
//        return value
//    }
//
//    fun getFuzzyKey(): List<String> {
//        return mFuzzySearchKey
//    }

}
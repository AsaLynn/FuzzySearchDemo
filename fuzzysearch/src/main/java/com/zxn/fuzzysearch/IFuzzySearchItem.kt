package com.zxn.fuzzysearch

/**
 * 先匹配原始数据，再匹配模糊数据
 *  Updated by zxn on 2020/11/2.
 */
interface IFuzzySearchItem {
    /**
     * 获取item原始字符串
     *
     * @return 原始item字符串
     */
    val sourceKey: String?

    /**
     * 获取item模糊字符串,item对应的拼音 江西省    ["jiang", "xi", "sheng"]
     *
     * @return 模糊item字符串
     */
    val fuzzyKey: List<String?>?
}
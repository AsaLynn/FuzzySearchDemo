package com.zxn.fuzzysearchdemo

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.zxn.fuzzysearch.PinyinUtil
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

/**
 *  Updated by zxn on 2020/11/2.
 */
class MainActivity : AppCompatActivity() {
    private var mContext: Context? = null
    private var mViewHolder: View? = null
    private var mLayoutFuzzySearch: ViewGroup? = null
    private var mFuzzySearchAdapter: FuzzySearchAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mContext = this
        initView()
        initEvent()
        initData()
    }

    private fun initView() {
        mViewHolder = findViewById(R.id.view_holder_for_focus)
        mLayoutFuzzySearch = findViewById(R.id.layout_fuzzy_search)
        recycler_fuzzy_search_list.layoutManager = LinearLayoutManager(mContext)
    }

    private fun initEvent() {
        mTextCancel!!.setOnClickListener {
            mEditSearchInput!!.setText("")
            hideKeyboard(mContext, mEditSearchInput)
            mViewHolder!!.requestFocus()
        }
        mEditSearchInput!!.onFocusChangeListener = OnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                mLayoutFuzzySearch!!.visibility = View.VISIBLE
            } else {
                mLayoutFuzzySearch!!.visibility = View.GONE
            }
        }
        mEditSearchInput!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                mFuzzySearchAdapter!!.filter.filter(s)
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }

    private fun initData() {
        val dateList = fillData(resources.getStringArray(R.array.region))
        // 这里我们先排序
        //Collections.sort(dateList, new LettersComparator<ItemEntity>());
        recycler_fuzzy_search_list!!.adapter =
            FuzzySearchAdapter(dateList).also { mFuzzySearchAdapter = it }
    }

    private fun fillData(date: Array<String>): List<ItemEntity> {
        val sortList: MutableList<ItemEntity> = ArrayList()
        for (item in date) {
            var letter: String
            //汉字转换成拼音
            val pinyinList = PinyinUtil.getPinYinList(item)
            letter = if (pinyinList != null && pinyinList.isNotEmpty()) {
                val letters = pinyinList[0].substring(0, 1).toUpperCase()
                //用Raw字符串定义正则表达式:判断首字母是否是英文字母
                val pattern = """[A-Z]"""
                //将正则规则传入到Regex中
                val regex = Regex(pattern)
                if (letters.matches(regex)) {
                    letters.toUpperCase()
                } else {
                    "#"
                }
            } else {
                "#"
            }
            sortList.add(ItemEntity(item, letter, pinyinList))
        }
        return sortList
    }

    private fun hideKeyboard(context: Context?, view: View?) {
        val im = context!!.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        im.hideSoftInputFromWindow(view!!.windowToken, 0)
    }
}
package com.zxn.fuzzysearchdemo;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import com.zxn.fuzzysearch.PinyinUtil;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

	private Context            mContext;
	private View               mViewHolder;
	private EditText           mEditSearchInput;
	private TextView           mTextCancel;
	private ViewGroup          mLayoutFuzzySearch;
	private RecyclerView       mRecyclerSearch;
	private FuzzySearchAdapter mFuzzySearchAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this;
		initView();
		initEvent();
		initData();
	}

	private void initView() {
		mViewHolder = findViewById(R.id.view_holder_for_focus);
		mEditSearchInput = findViewById(R.id.edit_search_input);
		mTextCancel = findViewById(R.id.text_search_cancel);
		mLayoutFuzzySearch = findViewById(R.id.layout_fuzzy_search);
		mRecyclerSearch = findViewById(R.id.recycler_fuzzy_search_list);
		mRecyclerSearch.setLayoutManager(new LinearLayoutManager(mContext));
	}

	private void initEvent() {
		mTextCancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mEditSearchInput.setText("");
				hideKeyboard(mContext, mEditSearchInput);
				mViewHolder.requestFocus();
			}
		});


		mEditSearchInput.setOnFocusChangeListener(new android.view.View.
			OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					mLayoutFuzzySearch.setVisibility(View.VISIBLE);
				} else {
					mLayoutFuzzySearch.setVisibility(View.GONE);
				}
			}
		});

		mEditSearchInput.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				mFuzzySearchAdapter.getFilter().filter(s);
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
	}

	private void initData() {
		List<ItemEntity> dateList = fillData(getResources().getStringArray(R.array.region));
		// 这里我们先排序
		//Collections.sort(dateList, new LettersComparator<ItemEntity>());
		mRecyclerSearch.setAdapter(mFuzzySearchAdapter = new FuzzySearchAdapter(dateList));
	}

	private List<ItemEntity> fillData(String[] date) {
		List<ItemEntity> sortList = new ArrayList<>();
		for (String item : date) {
			String letter;
			//汉字转换成拼音
			List<String> pinyinList = PinyinUtil.getPinYinList(item);
			if (pinyinList != null && !pinyinList.isEmpty()) {
				// A-Z导航
				String letters = pinyinList.get(0).substring(0, 1).toUpperCase();
				// 正则表达式，判断首字母是否是英文字母
				if (letters.matches("[A-Z]")) {
					letter = letters.toUpperCase();
				} else {
					letter = "#";
				}
			} else {
				letter = "#";
			}
			sortList.add(new ItemEntity(item, letter, pinyinList));
		}
		return sortList;

	}

	private void hideKeyboard(Context context, View view) {
		InputMethodManager im = (InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE);
		if (im != null) {
			im.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}
	}
}

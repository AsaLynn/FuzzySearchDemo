package com.zxn.fuzzysearchdemo;

import com.zxn.fuzzysearch.IFuzzySearchItem;

import java.util.List;

public class ItemEntity implements IFuzzySearchItem {

	private String       mValue;
	private String       mSortLetters;
	private List<String> mFuzzySearchKey;

	public ItemEntity(String value, String sortLetters, List<String> fuzzySearchKey) {
		mValue = value;
		mSortLetters = sortLetters;
		mFuzzySearchKey = fuzzySearchKey;
	}

	public String getValue() {
		return mValue;
	}

	@Override
	public String getSourceKey() {
		return mValue;
	}

	@Override
	public List<String> getFuzzyKey() {
		return mFuzzySearchKey;
	}
}

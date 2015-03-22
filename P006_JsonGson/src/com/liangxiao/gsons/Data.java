package com.liangxiao.gsons;

import java.util.List;

public class Data {
	private String RecordCount;
	private List<Provider> ProviderList;

	public Data() {
		super();
	}

	public Data(String recordCount, List<Provider> providerList) {
		super();
		RecordCount = recordCount;
		ProviderList = providerList;
	}

	public String getRecordCount() {
		return RecordCount;
	}

	public void setRecordCount(String recordCount) {
		RecordCount = recordCount;
	}

	public List<Provider> getProviderList() {
		return ProviderList;
	}

	public void setProviderList(List<Provider> providerList) {
		ProviderList = providerList;
	}

}

package com.liangxiao.gsons;

public class Provider {

	private String ProviderID;
	private String ProviderCode;
	private String StoreName;
	private String AppLogo;
	private String Address;
	private String StoreScore;
	private String Distance;

	public Provider() {
		super();
	}

	public Provider(String providerID, String providerCode, String storeName,
			String appLogo, String address, String storeScore, String distance) {
		super();
		ProviderID = providerID;
		ProviderCode = providerCode;
		StoreName = storeName;
		AppLogo = appLogo;
		Address = address;
		StoreScore = storeScore;
		Distance = distance;
	}

	public String getProviderID() {
		return ProviderID;
	}

	public void setProviderID(String providerID) {
		ProviderID = providerID;
	}

	public String getProviderCode() {
		return ProviderCode;
	}

	public void setProviderCode(String providerCode) {
		ProviderCode = providerCode;
	}

	public String getStoreName() {
		return StoreName;
	}

	public void setStoreName(String storeName) {
		StoreName = storeName;
	}

	public String getAppLogo() {
		return AppLogo;
	}

	public void setAppLogo(String appLogo) {
		AppLogo = appLogo;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getStoreScore() {
		return StoreScore;
	}

	public void setStoreScore(String storeScore) {
		StoreScore = storeScore;
	}

	public String getDistance() {
		return Distance;
	}

	public void setDistance(String distance) {
		Distance = distance;
	}

}

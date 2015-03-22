package com.liangxiao.gsons;

import java.io.Serializable;

public class ProviderInfoData implements Serializable {

	private String status;
	private String errcode;
	private String msg;
	private Data data;

	public ProviderInfoData() {
		super();
	}

	public ProviderInfoData(String status, String errcode, String msg, Data data) {
		super();
		this.status = status;
		this.errcode = errcode;
		this.msg = msg;
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

}

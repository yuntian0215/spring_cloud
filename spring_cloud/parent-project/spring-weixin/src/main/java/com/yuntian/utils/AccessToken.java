package com.yuntian.utils;
/**
 * <p>微信通用接口凭证</p>
 * 2019年4月3日下午3:07:03
 * @author lvjie
 */
public class AccessToken {
	// 获取到的凭证
    private String accessToken;

    // 凭证有效时间，单位：秒
    private int expiresin;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public int getExpiresin() {
		return expiresin;
	}

	public void setExpiresin(int expiresin) {
		this.expiresin = expiresin;
	}
}

package com.pvr.entity;

public final class PVRCinimasObject {
	
	private final String pvrName;
	private final String pvrHomeCity;
	private final String phoneNo;
	private final String website;
	
	public PVRCinimasObject() {
		this.pvrName="PVR Cinimas";
		this.pvrHomeCity="Bangalore";
		this.phoneNo="9090900900";
		this.website="www.pvrCinimas.com";
		
	}

	public String getPvrName() {
		return pvrName;
	}

	public String getPvrHomeCity() {
		return pvrHomeCity;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public String getWebsite() {
		return website;
	}
}

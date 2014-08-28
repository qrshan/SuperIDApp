package com.simu.superid.menu;

public class Item {
	public String mTitle;
    public int mIconRes;
    private int index;

    public Item(int index, String title, int iconRes) {
    	this.index = index;
    	this.mTitle = title;
        this.mIconRes = iconRes;
    }

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}

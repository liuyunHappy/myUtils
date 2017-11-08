package com.liujunwen.entity;

public class PersonChild {
	private String childName;

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	@Override
	public String toString() {
		return "PersonChild [childName=" + childName + "]";
	}
}

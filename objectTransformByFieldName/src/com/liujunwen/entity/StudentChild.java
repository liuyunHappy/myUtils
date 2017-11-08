package com.liujunwen.entity;

public class StudentChild {
	private String childName;

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	@Override
	public String toString() {
		return "StudentChild [childName=" + childName + "]";
	}
}

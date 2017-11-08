package com.liujunwen.entity;

import java.util.List;

public class Student {
	private String name;
	
	private List<String> infos;
	
	private List<StudentChild> childs;

	private StudentChild child;
	
	public List<StudentChild> getChilds() {
		return childs;
	}

	public void setChilds(List<StudentChild> childs) {
		this.childs = childs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getInfos() {
		return infos;
	}

	public void setInfos(List<String> infos) {
		this.infos = infos;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", infos=" + infos + ", childs=" + childs + ", child=" + child + "]";
	}

	public StudentChild getChild() {
		return child;
	}

	public void setChild(StudentChild child) {
		this.child = child;
	}
}

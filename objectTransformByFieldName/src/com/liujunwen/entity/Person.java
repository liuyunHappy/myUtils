package com.liujunwen.entity;

import java.util.List;

public class Person {
	private String name;
	
	private PersonChild child;
	private List<String> infos;
	private List<PersonChild> childs;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", child=" + child + ", infos=" + infos + ", childs=" + childs + "]";
	}

	public List<String> getInfos() {
		return infos;
	}

	public void setInfos(List<String> infos) {
		this.infos = infos;
	}

	public PersonChild getChild() {
		return child;
	}

	public void setChild(PersonChild child) {
		this.child = child;
	}

	public List<PersonChild> getChilds() {
		return childs;
	}

	public void setChilds(List<PersonChild> childs) {
		this.childs = childs;
	}
}

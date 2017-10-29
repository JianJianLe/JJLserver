package com.xy.bean;

import java.util.List;

/**
 * 修改广告传输对象
 * @author jat
 *
 */
public class AddCharBean {

	private String oldTitle;
	private String oldContent;
	private String title;
	private String content;
	private List<JJLUserDto> userList;
	public String getOldTitle() {
		return oldTitle;
	}
	public void setOldTitle(String oldTitle) {
		this.oldTitle = oldTitle;
	}
	public String getOldContent() {
		return oldContent;
	}
	public void setOldContent(String oldContent) {
		this.oldContent = oldContent;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<JJLUserDto> getUserList() {
		return userList;
	}
	public void setUserList(List<JJLUserDto> userList) {
		this.userList = userList;
	}
	@Override
	public String toString() {
		return "AddCharBean [oldTitle=" + oldTitle + ", oldContent="
				+ oldContent + ", title=" + title + ", content=" + content
				+ ", userList=" + userList + "]";
	}
}

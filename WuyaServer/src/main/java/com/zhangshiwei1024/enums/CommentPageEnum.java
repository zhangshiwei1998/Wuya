package com.zhangshiwei1024.enums;

/**
 * 评论页面枚举类
 *
 * @author: Naccl
 * @date: 2022-01-22
 */
public enum CommentPageEnum {
	/** 未知页面评论	 */
	UNKNOWN("UNKNOWN", "UNKNOWN"),

	/** 博客评论	 */
	BLOG("", ""),
	/** 关于我 页面评论	 */
	ABOUT("关于我", "/about"),
	/** 友链页面评论	 */
	FRIEND("友人帐", "/friends"),
	;

	private String title;
	private String path;

	CommentPageEnum(String title, String path) {
		this.title = title;
		this.path = path;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}

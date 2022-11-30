package com.zhangshiwei1024.enums;

/**
 * 访问行为枚举类
 *
 * @author: Naccl
 * @date: 2022-01-08
 */
public enum VisitBehavior {
	/** 未知行为 */
	UNKNOWN("UNKNOWN", "UNKNOWN"),

	/** 访问首页 */
	INDEX("访问页面", "首页"),
	/** 访问归档页面 */
	ARCHIVE("访问页面", "归档"),
	/** 访问动态页面 */
	MOMENT("访问页面", "动态"),
	/** 访问友链页面 */
	FRIEND("访问页面", "友链"),
	/** 访问关于我页面 */
	ABOUT("访问页面", "关于我"),

	/** 查看博客 */
	BLOG("查看博客", ""),
	/** 查看分类 */
	CATEGORY("查看分类", ""),
	/** 查看标签 */
	TAG("查看标签", ""),
	/** 搜索博客 */
	SEARCH("搜索博客", ""),
	/** 点击友链 */
	CLICK_FRIEND("点击友链", ""),
	/** 点赞动态 */
	LIKE_MOMENT("点赞动态", ""),
	/** 校验博客密码 */
	CHECK_PASSWORD("校验博客密码", ""),
	;

	/**
	 * 访问行为
	 */
	private final String behavior;
	/**
	 * 访问内容
	 */
	private final String content;

	VisitBehavior(String behavior, String content) {
		this.behavior = behavior;
		this.content = content;
	}

	public String getBehavior() {
		return behavior;
	}

	public String getContent() {
		return content;
	}
}

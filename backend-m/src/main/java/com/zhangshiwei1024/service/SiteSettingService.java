package com.zhangshiwei1024.service;

import com.zhangshiwei1024.entity.SiteSetting;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface SiteSettingService {
	Map<String, List<SiteSetting>> getList();

	Map<String, Object> getSiteInfo();

	String getWebTitleSuffix();

	void updateSiteSetting(List<LinkedHashMap> siteSettings, List<Integer> deleteIds);
}

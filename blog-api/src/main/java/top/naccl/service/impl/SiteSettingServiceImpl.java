package top.naccl.service.impl;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.naccl.entity.SiteSetting;
import top.naccl.mapper.SiteSettingMapper;
import top.naccl.model.bean.Badge;
import top.naccl.model.bean.Copyright;
import top.naccl.model.bean.Favorite;
import top.naccl.model.vo.Introduction;
import top.naccl.service.SiteSettingService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: 站点设置业务层实现
 * @Author: Naccl
 * @Date: 2020-08-09
 */
@Service
public class SiteSettingServiceImpl implements SiteSettingService {
	@Autowired
	SiteSettingMapper siteSettingMapper;

	@Override
	public Map<String, List<SiteSetting>> getList() {
		List<SiteSetting> siteSettings = siteSettingMapper.getList();
		Map<String, List<SiteSetting>> map = new HashMap<>();
		List<SiteSetting> type1 = new ArrayList<>();
		List<SiteSetting> type2 = new ArrayList<>();
		List<SiteSetting> type3 = new ArrayList<>();
		for (SiteSetting s : siteSettings) {
			if (s.getType() == 1) {
				type1.add(s);
			} else if (s.getType() == 2) {
				type2.add(s);
			} else if (s.getType() == 3) {
				type3.add(s);
			}
		}
		map.put("type1", type1);
		map.put("type2", type2);
		map.put("type3", type3);
		return map;
	}

	@Override
	public Map<String, Object> getSiteInfo() {
		List<SiteSetting> siteSettings = siteSettingMapper.getList();
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> siteInfo = new HashMap<>();
		List<Badge> badges = new ArrayList<>();
		Introduction introduction = new Introduction();
		List<Favorite> favorites = new ArrayList<>();
		List<String> rollTexts = new ArrayList<>();
		for (SiteSetting s : siteSettings) {
			if (s.getType() == 1) {
				if ("copyright".equals(s.getNameEn())) {
					siteInfo.put(s.getNameEn(), JSON.parseObject(s.getValue(), Copyright.class));
				} else {
					siteInfo.put(s.getNameEn(), s.getValue());
				}
			} else if (s.getType() == 2) {
				Badge badge = JSON.parseObject(s.getValue(), Badge.class);
				badges.add(badge);
			} else if (s.getType() == 3) {
				if ("avatar".equals(s.getNameEn())) {
					introduction.setAvatar(s.getValue());
				} else if ("name".equals(s.getNameEn())) {
					introduction.setName(s.getValue());
				} else if ("github".equals(s.getNameEn())) {
					introduction.setGithub(s.getValue());
				} else if ("qq".equals(s.getNameEn())) {
					introduction.setQq(s.getValue());
				} else if ("bilibili".equals(s.getNameEn())) {
					introduction.setBilibili(s.getValue());
				} else if ("netease".equals(s.getNameEn())) {
					introduction.setNetease(s.getValue());
				} else if ("email".equals(s.getNameEn())) {
					introduction.setEmail(s.getValue());
				} else if ("favorite".equals(s.getNameEn())) {
					favorites.add(JSON.parseObject(s.getValue(), Favorite.class));
				} else if ("rollText".equals(s.getNameEn())) {
					Pattern p = Pattern.compile("\"(.*?)\"");
					Matcher m = p.matcher(s.getValue());
					while (m.find()) {
						rollTexts.add(m.group(1));
					}
				}
			}
		}
		introduction.setFavorites(favorites);
		introduction.setRollText(rollTexts);
		map.put("introduction", introduction);
		map.put("siteInfo", siteInfo);
		map.put("badges", badges);
		return map;
	}

	@Transactional
	@Override
	public int updateSiteSetting(SiteSetting siteSetting) {
		return siteSettingMapper.updateSiteSetting(siteSetting);
	}

	@Transactional
	@Override
	public int deleteSiteSettingById(Integer id) {
		return siteSettingMapper.deleteSiteSettingById(id);
	}

	@Transactional
	@Override
	public int saveSiteSetting(SiteSetting siteSetting) {
		return siteSettingMapper.saveSiteSetting(siteSetting);
	}
}
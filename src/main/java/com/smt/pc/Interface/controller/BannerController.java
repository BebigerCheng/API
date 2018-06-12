package com.smt.pc.Interface.controller;

import com.smt.pc.Interface.domain.BannerDO;
import com.smt.pc.Interface.service.BannerService;
import com.smt.pc.Interface.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


/**
 * 
 * 
 * @author lijikai
 * @email 
 * @date 2018-04-16 17:19:37
 */
 
@Controller
@RequestMapping("/system/banner")
public class BannerController {
	@Autowired
	private BannerService bannerService;
	

	@ResponseBody
	@GetMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
		params.put("isActive","1");
		List<BannerDO> bannerList = bannerService.list(params);
		if(bannerList.size() > 0){
			params.put("bannerList",bannerList);
			return R.ok(params);
		}
		return R.error();
	}
}

package com.smt.pc.Interface.service.impl;

import com.smt.pc.Interface.domain.BannerDO;
import com.smt.pc.Interface.mapper.BannerDao;
import com.smt.pc.Interface.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service
public class BannerServiceImpl implements BannerService {
	@Autowired
	private BannerDao bannerDao;
	
	@Override
	public BannerDO get(Integer id){
		return bannerDao.get(id);
	}
	
	@Override
	public List<BannerDO> list(Map<String, Object> map){
		return bannerDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return bannerDao.count(map);
	}
	
	@Override
	public int save(BannerDO banner){
		return bannerDao.save(banner);
	}
	
	@Override
	public int update(BannerDO banner){
		return bannerDao.update(banner);
	}
	
	@Override
	public int remove(Integer id){
		return bannerDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return bannerDao.batchRemove(ids);
	}
	
}

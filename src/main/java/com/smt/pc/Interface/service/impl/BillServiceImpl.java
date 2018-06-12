package com.smt.pc.Interface.service.impl;

import com.smt.pc.Interface.domain.BillDO;
import com.smt.pc.Interface.domain.UserDO;
import com.smt.pc.Interface.mapper.BillDao;
import com.smt.pc.Interface.service.BillService;
import com.smt.pc.Interface.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;



@Service
public class BillServiceImpl implements BillService {
	@Autowired
	private BillDao billDao;
	
	@Override
	public BillDO get(Integer id){
		return billDao.get(id);
	}
	
	@Override
	public List<BillDO> list(Map<String, Object> map){
		String expiringDate = String.valueOf(map.get("expiringDate"));
		String type = expiringDate.split(",")[0];
		String startTime = null;
		String endTime = null;

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();

		if ("1".equals(type)){
			startTime = formatter.format(cal.getTime());
			cal.add(Calendar.DATE, 30);
			endTime = formatter.format(cal.getTime());
			//endTime = LocalDateTime.now().plusDays(30).toEpochSecond(ZoneOffset.of("+8"));
			/**
			 * 你这个时间戳计算会出问题，用上面这个方法
			 */
//			endTime = System.currentTimeMillis() + 60*60*24*1000*30;
		}else if("2".equals(type)){
			cal.add(Calendar.MONTH, 1);
			startTime = formatter.format(cal.getTime());
			cal.add(Calendar.MONTH, 1);
			endTime = formatter.format(cal.getTime());
//			startTime = System.currentTimeMillis() + 60*60*24*1000*30;
//			endTime = System.currentTimeMillis() + 60*60*24*1000*60;
		}else if("3".equals(type)){
			cal.add(Calendar.MONTH, 2);
			startTime = formatter.format(cal.getTime());
			cal.add(Calendar.MONTH, 1);
			endTime = formatter.format(cal.getTime());
//			startTime = System.currentTimeMillis() + 60*60*24*1000*30;
//			endTime = System.currentTimeMillis() + 60*60*24*1000*60;
		}else if("4".equals(type)){
			cal.add(Calendar.MONTH, 3);
			startTime = formatter.format(cal.getTime());
			cal.add(Calendar.MONTH, 3);
			endTime = formatter.format(cal.getTime());
//			startTime = System.currentTimeMillis() + 60*60*24*1000*60;
//			endTime = System.currentTimeMillis() + 60*60*24*1000*90;
		}else if("5".equals(type)){
			cal.add(Calendar.MONTH, 6);
			startTime = formatter.format(cal.getTime());
//			startTime = System.currentTimeMillis() + 60*60*24*1000*90;
		}
		map.put("startTime",startTime);
		map.put("endTime",endTime);
		return billDao.list(map);
	}
	
	@Override
	public int count(Query query){
		return billDao.count(query);
	}
	
	@Override
	public int save(BillDO bill){
		return billDao.save(bill);
	}
	
	@Override
	public int update(BillDO bill){
		return billDao.update(bill);
	}
	
	@Override
	public int remove(Integer id){
		return billDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return billDao.batchRemove(ids);
	}

	@Override
	public BillDO getBillById(Integer id) {
		return billDao.getBillById(id);
	}

}

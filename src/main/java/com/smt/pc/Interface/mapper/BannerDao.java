package com.smt.pc.Interface.mapper;

import com.smt.pc.Interface.domain.BannerDO;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author lijikai
 * @email 
 * @date 2018-04-16 17:19:37
 */
@Mapper
public interface BannerDao {

	BannerDO get(Integer id);
	
	List<BannerDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(BannerDO banner);
	
	int update(BannerDO banner);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}

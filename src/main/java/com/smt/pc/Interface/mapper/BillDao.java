package com.smt.pc.Interface.mapper;

import com.smt.pc.Interface.domain.BillDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 票据表
 *
 * @author lijikai
 * @email
 * @date 2018 -03-28 14:12:17
 */
@Mapper
public interface BillDao {

	/**
	 * Get bill do.
	 *
	 * @param id the id
	 * @return the bill do
	 */
	BillDO get(Integer id);

	/**
	 * List list.
	 *
	 * @param map the map
	 * @return the list
	 */
	List<BillDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(BillDO bill);
	
	int update(BillDO bill);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	BillDO getBillById(Integer id);
}

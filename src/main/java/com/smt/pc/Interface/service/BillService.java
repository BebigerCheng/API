package com.smt.pc.Interface.service;


import com.smt.pc.Interface.domain.BillDO;
import com.smt.pc.Interface.utils.Query;

import java.util.List;
import java.util.Map;

/**
 * 票据表
 *
 * @author lijikai
 * @email
 * @date 2018 -03-28 14:12:17
 */
public interface BillService {

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

	/**
	 * Count int.
	 *
	 * @param query the map
	 * @return the int
	 */
	int count(Query query);

	/**
	 * Save int.
	 *
	 * @param bill the bill
	 * @return the int
	 */
	int save(BillDO bill);

	/**
	 * Update int.
	 *
	 * @param bill the bill
	 * @return the int
	 */
	int update(BillDO bill);

	/**
	 * Remove int.
	 *
	 * @param id the id
	 * @return the int
	 */
	int remove(Integer id);

	/**
	 * Batch remove int.
	 *
	 * @param ids the ids
	 * @return the int
	 */
	int batchRemove(Integer[] ids);

	/**
	 * Gets bill by id.
	 *
	 * @param id the id
	 * @return the bill by id
	 */
	BillDO getBillById(Integer id);
}

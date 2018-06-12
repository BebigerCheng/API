package com.smt.pc.Interface.controller;

import com.smt.pc.Interface.domain.BillDO;
import com.smt.pc.Interface.result.ResultDO;
import com.smt.pc.Interface.service.BillService;
import com.smt.pc.Interface.utils.PageUtils;
import com.smt.pc.Interface.utils.Query;
import com.smt.pc.Interface.utils.R;
import com.smt.pc.Interface.utils.SmtConst;
import com.smt.pc.Interface.utils.date.DateUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 票据表
 *
 * @author lijikai
 * @email
 * @date 2018 -03-23 13:49:46
 */
@Controller
@RequestMapping("/system/bill")
public class BillController extends BaseController {
    @Autowired
    private BillService billService;

    /**
     * Bill string.
     *
     * @return the string
     */
    @GetMapping()
    String Bill() {
        return "system/bill/bill";
    }

    /**
     * List page utils.
     *
     * @param params the params
     * @return the page utils
     */
    @ApiOperation(value = "条件分页查询", notes = "params:实体类参数 offset：当前页 limit:每页显示条数", httpMethod = "POST")
    @ApiResponse(code = 200, message = "Success")
    @ResponseBody
    @PostMapping("/list")
    public PageUtils list(@RequestBody Map<String, Object> params) {
        //查询列表数据

        params.put("isactive","1");
        Query query = new Query(params);
        List<BillDO> billList = billService.list(query);

        List list = encapsulation(billList);

        int total = billService.count(query);
        return new PageUtils(list, total);
    }

    @ApiOperation(value = "用户bill分页查询", notes = "params:实体类参数 offset：当前页 limit:每页显示条数", httpMethod = "POST")
    @ApiResponse(code = 200, message = "Success")
    @ResponseBody
    @PostMapping("/userBill")
    public PageUtils billForUserList(@RequestBody Map<String, Object> params ,HttpSession session) {

        //查询列表数据
        params.put("isactive","1");
        Query query = new Query(params);
        List<BillDO> billList = billService.list(query);

        List list = encapsulation(billList);

        int total = billService.count(query);
        return new PageUtils(list, total);
    }


    /**
     * Gets bill by id.
     *
     * @param id the id
     * @return the bill by id
     */
    @ResponseBody
    @RequestMapping("/get/{id}")
    public ResultDO<BillDO> getBillById(@PathVariable("id") Integer id) {


        ResultDO<BillDO> resultDO = new ResultDO<>();
        resultDO.setSuccess(true);

        BillDO billDO = billService.getBillById(id);

        //获取剩余天数
        int diffDay = DateUtils.getDifferDateDay(billDO.getExpiringDate(), new Date());

        //获取每十w扣款
        billDO.setDebit10(getDebit10(billDO.getInterestRate(),diffDay));

        billDO.setOfferAmount(getOfferAmount(billDO.getDebit10(),billDO.getBillAmount()));

        billDO.setDiffDay(diffDay);
        resultDO.setData(billDO);

        return resultDO;

    }
    /*
    @GetMapping("/add")
	String add(){
	    return "system/bill/add";
	}*/

    /**
     * Edit string.
     *
     * @param id    the id
     * @param model the model
     * @return the string
     */
    @GetMapping("/edit/{id}")
    String edit(@PathVariable("id") Integer id, Model model) {
        BillDO bill = billService.get(id);
        model.addAttribute("bill", bill);
        return "system/bill/edit";
    }


    /**
     * Gets debit 10.
     * 获取每十万元扣款
     *
     * @param rate    the rate 利率
     * @param diffday the diffday 天数
     * @return the debit 10  保留两位小数 四舍五入
     */
    String getDebit10(String rate, int diffday) {



        if("".equals(rate.toString())){
            return "--";
        }
        BigDecimal rates=new BigDecimal(rate);

        rates = rates.divide(new BigDecimal(100));

        // (10*年利率*到期天数)/360



        return rates.multiply(new BigDecimal(diffday)).multiply(new BigDecimal(100000)).divide(new BigDecimal(SmtConst.DI_NUM), 2, BigDecimal.ROUND_HALF_UP).toString();


    }


    /**
     * Gets debit 10.
     * 获取报价金额
     *
     * @param debit10    the debit 10
     * @param billAmount the bill amount
     * @return the offer amount
     */
    String getOfferAmount(String debit10, BigDecimal billAmount) {

        //竞价
        if("--".equals(debit10)){
            return "--";
        }

        return billAmount.subtract(billAmount.divide((new BigDecimal(100000)),2,BigDecimal.ROUND_DOWN).multiply(new BigDecimal(debit10))).divide(new BigDecimal(SmtConst.DEBIT_NUM)).toString();

    }


    /**
     * 保存
     *
     * @param bill the bill
     * @return the r
     */
    @ApiOperation(value = "保存", notes = "", httpMethod = "POST")
    @ApiResponse(code = 200, message = "Success")
    @ResponseBody
    @PostMapping("/save")
    public R save(@RequestBody BillDO bill) {
        //暂时已发布
        bill.setStatus(3);
        //默认启用
        bill.setIsactive(1);
        bill.setBillAmount(bill.getBillAmount().multiply(new BigDecimal(SmtConst.DEBIT_NUM)));
        bill.setCreateTime(new Date());
        bill.setUpdateTime(new Date());
        if (billService.save(bill) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     *
     * @param bill the bill
     * @return the r
     */
    @ApiOperation(value = "更新", notes = "", httpMethod = "POST")
    @ApiResponse(code = 200, message = "Success")
    @ResponseBody
    @PostMapping("/update")
    public R update(@RequestBody BillDO bill) {
        bill.setUpdateTime(new Date());
        bill.setBillAmount(bill.getBillAmount().multiply(new BigDecimal(SmtConst.DEBIT_NUM)));
        if (billService.update(bill) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     *
     * @param
     * @return the r
     */
    @ApiOperation(value = "删除", notes = "根据票据Id删除用户", httpMethod = "POST")
    @ApiResponse(code = 200, message = "Success")
    @PostMapping("/remove")
    @ResponseBody
    public R remove(@RequestBody Map<String,Object> params) {
       String idstr =  params.get("id").toString();
        Integer id = Integer.valueOf(idstr);
        if(billService.remove(id) > 0){
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     *
     * @param ids the ids
     * @return the r
     */
    @ApiOperation(value = "批量删除", notes = "参数为需要删除的id的数组", httpMethod = "POST")
    @ApiResponse(code = 200, message = "Success")
    @PostMapping("/batchRemove")
    @ResponseBody
    public R remove(@RequestParam("ids[]") Integer[] ids) {
        billService.batchRemove(ids);
        return R.ok();
    }

    /**
     * 对查询的数据进行封装
     *
     * @param billList
     * @return the list
     * */
    private List encapsulation(List<BillDO> billList){
        int diffDay;
        List list = new ArrayList();
        for (BillDO billDO : billList) {
            diffDay = DateUtils.getDifferDateDay(billDO.getExpiringDate(), new Date());
            billDO.setDiffDay(diffDay);
            billDO.setDebit10(getDebit10(billDO.getInterestRate(),diffDay));
            billDO.setOfferAmount(getOfferAmount(billDO.getDebit10(),billDO.getBillAmount()));

            list.add(billDO);

        }
        return list;
    }

}

package com.smt.pc.Interface.controller;

import com.smt.pc.Interface.domain.OperationLogDO;
import com.smt.pc.Interface.service.OperationLogService;
import com.smt.pc.Interface.utils.PageUtils;
import com.smt.pc.Interface.utils.Query;
import com.smt.pc.Interface.utils.R;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 用户操作日志表
 *
 * @author lijikai
 * @email
 * @date 2018-04-02 11:25:15
 */

@RestController
@RequestMapping("/system/operationLog")
public class OperationLogController {
    @Autowired
    private OperationLogService operationLogService;


    @ApiOperation(value = "根据条件分页查询", notes = "", httpMethod = "GET")
    @ApiResponse(code = 200, message = "Success")
    @ResponseBody
    @GetMapping("/list")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<OperationLogDO> operationLogList = operationLogService.list(query);
        int total = operationLogService.count(query);
        return new PageUtils(operationLogList, total);
    }


    @GetMapping("/edit/{id}")
    String edit(@PathVariable("id") Integer id, Model model) {
        OperationLogDO operationLog = operationLogService.get(id);
        model.addAttribute("operationLog", operationLog);
        return "system/operationLog/edit";
    }

    /**
     * 保存
     */
    @ApiOperation(value = "买点记录", httpMethod = "POST")
    @ApiResponse(code = 200, message = "Success")
    @ResponseBody
    @PostMapping("/save")
    public R save(@RequestBody OperationLogDO operationLog) {
        operationLog.setCreationTime(new Date());
        operationLog.setOperationTime(new Date());
        operationLog.setUpdateTime(new Date());
        if (operationLogService.save(operationLog) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "买点修改", httpMethod = "POST")
    @ApiResponse(code = 200, message = "Success")
    @ResponseBody
    @RequestMapping("/update")
    public R update(OperationLogDO operationLog) {
        operationLogService.update(operationLog);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    public R remove(Integer id) {
        if (operationLogService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    public R remove(@RequestParam("ids[]") Integer[] ids) {
        operationLogService.batchRemove(ids);
        return R.ok();
    }

}

package com.smt.pc.Interface.controller;

import com.alibaba.fastjson.JSONObject;
import com.smt.pc.Interface.service.UploadService;
import com.smt.pc.Interface.utils.EmailService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The type Upload controller.
 *
 * @author leroy
 */
@Controller
@RequestMapping("/upload")
@PropertySource("classpath:application-dev.properties")
public class UploadController extends BaseController {


    @Autowired
    UploadService uploadImg2Oss;


    //   @Autowired
//    private UploadService uploadService;
    @Value("${upload.IP}")
    private String ip;//ip
    @Value("${upload.port}")
    private int port;//端口
    @Value("${upload.img}")
    private String imgUrl;//图片存储地址
    @Value("${upload.file}")
    private String fileUrl;//文档存储地址



    /**
     * 使用Ajax异步上传图片
     *
     * @param pic      封装图片对象
     * @param request  the request
     * @param response the response
     * @throws IllegalStateException the illegal state exception
     * @throws IOException           the io exception
     */
    @ApiOperation(value = "文件上传", notes = "单个文件上传", httpMethod = "POST")
    @ApiResponse(code = 200, message = "Success")
    @RequestMapping(value = "/files", method = RequestMethod.POST)
    public void uploadPic(@RequestParam MultipartFile pic, HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {

        try {
            // 获取图片原始文件名
            String originalFilename = pic.getOriginalFilename();
            System.out.println(originalFilename);

            // 文件名使用当前时间
            String name = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());

            // 获取上传图片的扩展名(jpg/png/...)
            String extension = FilenameUtils.getExtension(originalFilename);

            // 图片上传的相对路径（因为相对路径放到页面上就可以显示图片）
            String path;

            //判断文件类型，分别存入不同文件夹
            if ("jpg".equals(extension) || "png".equals(extension)) {
                path = imgUrl;
            } else if ("txt".equals(extension) || "pdf".equals(extension)) {
                path = fileUrl;
            } else {
                throw new RuntimeException("上传文件格式不正确！");
            }

            String fileName = name + "." + extension;

            //图片上传的绝对路径
            String url = path;

            File dir = new File(url);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // 上传图片
            pic.transferTo(new File(url + "/" + fileName));

            // 将相对路径写回（json格式）
            JSONObject jsonObject = new JSONObject();
            // 将图片上传到本地
            jsonObject.put("path", "http://" + ip + ":" + port + "/" + path + "/" + fileName);

            // 设置响应数据的类型json
            response.setContentType("application/json; charset=utf-8");
            // 写回
            response.getWriter().write(jsonObject.toString());

        } catch (Exception e) {
            throw new RuntimeException("服务器繁忙，上传图片失败");
        }
    }


    /**
     * 使用Ajax异步上传图片
     *
     * @param
     * @throws IllegalStateException the illegal state exception
     * @throws IOException           the io exception
     */
    @ApiOperation(value = "文件上传", notes = "单个文件上传", httpMethod = "POST")
    @ApiResponse(code = 200, message = "Success")
    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public void uploadPicture(@RequestParam MultipartFile pic,HttpServletResponse response) throws IllegalStateException {

        try {

            String url = uploadImg2Oss.updateHead(pic);

            // 将相对路径写回（json格式）
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("path", url);
            // 设置响应数据的类型json
            response.setContentType("application/json; charset=utf-8");
            // 写回
            response.getWriter().write(jsonObject.toString());

        } catch (Exception ignored) {

            EmailService.sendSimpleEmail("错误。。", ignored.toString());
        }

    }




}

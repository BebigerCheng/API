package com.smt.pc.Interface.utils;

import com.smt.pc.Interface.exception.SmtException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * web处理共通
 * Created by lijikai on 2015/5/11.
 */
public class WebUtil {

    private static Logger logger = Logger.getLogger(WebUtil.class);

    /**
     * 获取Request
     *
     * @return
     */
    public static HttpServletRequest getRequest() {

        if (RequestContextHolder.getRequestAttributes() != null) {
            return ((ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes()).getRequest();
        }

        return null;
    }

    /**
     * 获取Response
     *
     * @return
     */
    public static HttpServletResponse getResponse() {

        if (RequestContextHolder.getRequestAttributes() != null) {
            return ((ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes()).getResponse();
        }

        return null;
    }

    /**
     * 获取Session
     *
     * @return
     */
    public static HttpSession getHttpSession() {

        HttpServletRequest request = getRequest();

        if (request != null) {
            return request.getSession();
        }

        return null;
    }

    /**
     * 获取ServletContext
     *
     * @return
     */
    public static ServletContext getServletContext() {

        return getHttpSession().getServletContext();
    }

    /**
     * 根据相对路径获取绝对路径
     *
     * @param path
     * @return
     */
    public static String getRealPath(String path) {
        try {
            if (path == null) {
                path = StringUtils.EMPTY;
            }
            getRequest().getServletContext();
            return WebUtils.getRealPath(getRequest().getServletContext(), path);
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage(), e);
            return StringUtils.EMPTY;
        }
    }

    /**
     * 判断是否Ajax调用
     *
     * @param request
     * @return
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {

        return "XMLHttpRequest".equals(request.getHeader("x-requested-with"));
    }

    /**
     * 上传文件
     *
     * @param file
     * @param dirPath
     * @return
     * @throws SmtException
     */
    public static String upload(MultipartFile file, String dirPath) throws SmtException {
        return upload(file, dirPath, null);
    }

    /**
     * 上传文件
     *
     * @param file
     * @param dirPath
     * @param fileName
     * @return
     * @throws SmtException
     */
    public static String upload(MultipartFile file, String dirPath, String fileName) throws SmtException {
        OutputStream out = null;
        String uploadFilePath = "";

        try {
            String realpath = getRealPath(dirPath);

            if (!IOUtil.exist(realpath)) {
                IOUtil.createDir(realpath);
            }

            int orginExtPos = file.getOriginalFilename().lastIndexOf(".");
            String orginExt = file.getOriginalFilename().substring(orginExtPos);

            if (StringUtils.isBlank(fileName)) {
                uploadFilePath = String.valueOf(System.currentTimeMillis()) + new Random().nextInt(100) + orginExt;
            } else {
                uploadFilePath = fileName;

            }

            out = new FileOutputStream(new File(realpath, uploadFilePath));
            out.write(file.getBytes());

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new SmtException("上传失败！");
        } finally {
            IOUtil.closeOutputStream(out);
        }

        return uploadFilePath;
    }

    /**
     * 上传文件  文件名 加上时间戳
     *lj
     * @param file  上传的文件
      * @param dirPath 上传的路径
     * @param fileName 文件名
     * @return
     * @throws SmtException
     */
    public static String uploadFile(MultipartFile file, String dirPath, String fileName) throws SmtException {
        OutputStream out = null;
        String uploadFilePath = "";

        try {
            String realpath = getRealPath(dirPath);

            if (!IOUtil.exist(realpath)) {
                IOUtil.createDir(realpath);
            }
                uploadFilePath = String.valueOf(System.currentTimeMillis()) +  file.getOriginalFilename();
            out = new FileOutputStream(new File(realpath, uploadFilePath));
            out.write(file.getBytes());

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new SmtException("上传失败！");
        } finally {
            IOUtil.closeOutputStream(out);
        }

        return uploadFilePath;
    }

    /**
     * 删除文件
     *
     * @param filePath
     * @return
     */
    public static boolean delete(String filePath) {
        try {
            String realpath = getRealPath(filePath);
            return IOUtil.deleteFile(realpath);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 保存文件
     *
     * @param filePath
     * @param date
     * @return
     */
    public static boolean save(String filePath, byte[] date) {
        try {
            String realpath = getRealPath(filePath);
            return IOUtil.saveFile(realpath, date);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 创建文件夹
     *
     * @param dirPath
     * @return
     */
    public static boolean mkdir(String dirPath) {
        try {
            String realpath = getRealPath(dirPath);

            if (!IOUtil.exist(realpath)) {
                return IOUtil.createDir(realpath);
            }

            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 获取文件名
     *
     * @param dir
     * @param withOutPrefix
     * @return
     */
    public static List<String> getFileName(String dir, String withOutPrefix) {
        try {
            String realpath = getRealPath(dir);
            return IOUtil.getFileName(realpath, withOutPrefix);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 获取文件路径
     *
     * @param dir
     * @param withOutPrefix
     * @return
     */
    public static List<String> getFilePath(String dir, String withOutPrefix) {
        try {
            String realpath = getRealPath(dir);
            return IOUtil.getFilePath(realpath, withOutPrefix);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 获取浏览器类型
     *
     * @return
     */
    public static String getBrowseType() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        return getBrowseType(request);
    }

    public static String getBrowseType(HttpServletRequest request) {
        String typename = request.getHeader("User-Agent");
        String type = "";
        if (typename.indexOf("MSIE") > -1) {
            type = SmtConst.BROWSE_IE;
        } else if (typename.indexOf("Firefox") > -1) {
            type = SmtConst.BROWSE_FILEFOX;
        } else if (typename.indexOf("Chrome") > -1) {
            type = SmtConst.BROWSE_CHEOME;
        } else if (typename.indexOf("Safari") > -1) {
            type = SmtConst.BROWSE_SAFARI;
        }
        return type;
    }

    /**
     * 下载文件
     *
     * @param response
     * @param fileName
     * @param data
     * @throws SmtException
     */
    public static void download(HttpServletResponse response, String fileName,
                                byte[] data) throws SmtException {

        if (data == null) {
            throw new SmtException(ResourceUtil.getResource("ERR_0052"));
        }

        OutputStream outputStream = null;
        try {
            String type = getBrowseType();
            if (SmtConst.BROWSE_FILEFOX.equals(type)
                    || SmtConst.BROWSE_CHEOME.equals(type)) {
                fileName = new String(fileName.getBytes(),
                        SmtConst.CHARSET_ISO8859_1);
            } else {
                fileName = URLEncoder.encode(fileName, SmtConst.CHARSET_UTF8);
            }

            response.reset();
            response.setHeader("Content-Disposition", "attachment; filename=\""
                    + fileName + "\"");
            response.addHeader("Content-Length", "" + data.length);
            response.setContentType("application/octet-stream;charset=UTF-8");
            outputStream = new BufferedOutputStream(response.getOutputStream());
            outputStream.write(data);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new SmtException(ResourceUtil.getResource("ERR_0052"));
        } finally {
            IOUtil.closeOutputStream(outputStream);
        }
    }

    /**
     * 下载文件
     *
     * @param response
     * @param fileName
     * @param path
     * @throws SmtException
     */
    public static void download(HttpServletResponse response, String fileName,
                                String path) throws SmtException {

        if (StringUtils.isBlank(path)) {
            throw new SmtException(ResourceUtil.getResource("ERR_0052"));
        }

        download(response, fileName, IOUtil.getFile(path));
    }

    /**
     * 弹出提示框
     *
     * @param response
     * @param msg
     */
    public static void alertMsg(HttpServletResponse response, String msg) {
        try {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter()
                    .write("<script>alert('" + msg + "');</script>");
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 写入Cookie
     *
     * @param response
     * @param name
     * @param value
     */
    public static void addCookie(HttpServletResponse response, String name,
                                 String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(SmtConst.COOKIE_AGE);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * 获取Cookie
     *
     * @param request
     * @param name
     * @return
     */
    public static String getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            cookie.getName();
            if (cookie.getName().equals(name)) {
                return cookie.getValue();
            }
        }
        return SmtConst.EMPTY;
    }

    /**
     * 删除Coockie
     *
     * @param response
     * @param name
     */
    public static void removeCookie(HttpServletResponse response, String name) {
        Cookie cookie = new Cookie(name, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * 获取客户端IP
     *
     * @return
     */
    public static String getClientIp() {
        return getRequest().getRemoteAddr();
    }

    /**
     * 获取客户端地域
     *
     * @return
     */
    public static String getClientLang() {
        HttpServletRequest request = getRequest();
        return getClientLang(request);
    }

    /**
     * 获取客户端地域
     *
     * @param request
     * @return
     */
    public static String getClientLang(HttpServletRequest request) {
        if (request != null) {
            String lang = request.getHeader("Accept-Language");
            String[] arr1 = lang.split(",");
            String[] arr2 = arr1[0].split("-");
            String clientLang = arr2[0];
            return clientLang;
        } else {
            return Locale.getDefault().getLanguage();
        }
    }

}

package com.smt.pc.Interface.utils.date;


import com.alibaba.druid.filter.config.ConfigTools;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * DateUtils
 *
 * @author LIJIKAI
 * @date 17/4/12
 */
public class DateUtils {

    private static DateUtils dateUtils;


    //默认显示日期的格式1
    public static final String DATAFORMAT_STR = "yyyy-MM-dd";

    //默认显示日期的格式
    public static final String YYYY_MM_DATAFORMAT_STR = "yyyy-MM";

    //默认显示日期时间的格式
    public static final String DATATIMEF_STR = "yyyy-MM-dd HH:mm:ss";

    //默认显示简体中文日期的格式
    public static final String ZHCN_DATAFORMAT_STR = "yyyy年MM月dd日";

    //默认显示简体中文日期时间的格式
    public static final String ZHCN_DATATIMEF_STR = "yyyy年MM月dd日HH时mm分ss秒";

    //默认显示简体中文日期时间的格式(只显示到分)
    public static final String ZHCN_DATATIMEF_STR_4yMMddHHmm = "yyyy年MM月dd日HH时mm分";

    //默认显示简体中文日期时间的格式(只显示月日时分)
    public static final String ZHCN_DATATIMEF_STR_MMddHHmm = "MM月dd日HH时mm分";

    public static final SimpleDateFormat dateFormatDB = new SimpleDateFormat("yyyyMMddHHmmss");

    public static final SimpleDateFormat dateFormatShortDB = new SimpleDateFormat("yyyyMMdd");

    public static final String NUM_DATATIME_STR = "yyyyMMddHHmmssSSS";
    private static DateFormat dateFormat = null;

    public static DateFormat dateTimeFormat = null;

    private static DateFormat zhcnDateFormat = null;

    private static DateFormat zhcnDateTimeFormat = null;

    private static DateFormat zhcnDateTime4yMMddHHmm = null;

    private static DateFormat zhcnDateTimeMMddHHmm = null;

    private static DateFormat numDateTimeFormat = null;

    static {
        dateFormat = new SimpleDateFormat(DATAFORMAT_STR);
        dateTimeFormat = new SimpleDateFormat(DATATIMEF_STR);
        zhcnDateFormat = new SimpleDateFormat(ZHCN_DATAFORMAT_STR);
        zhcnDateTimeFormat = new SimpleDateFormat(ZHCN_DATATIMEF_STR);
        zhcnDateTime4yMMddHHmm = new SimpleDateFormat(ZHCN_DATATIMEF_STR_4yMMddHHmm);
        zhcnDateTimeMMddHHmm = new SimpleDateFormat(ZHCN_DATATIMEF_STR_MMddHHmm);
        numDateTimeFormat = new SimpleDateFormat(NUM_DATATIME_STR);
    }


    public static String getFileNameDate() {
        String dateString = numDateTimeFormat.format(new Date());
        return dateString;
    }

    public DateUtils() {

    }

    public static DateUtils getDateUtils() {
        if (null == dateUtils) {
            dateUtils = new DateUtils();
        }
        return dateUtils;
    }

    private DateFormat getDateFormat(String formatStr) {
        if (formatStr.equalsIgnoreCase(DATAFORMAT_STR)) {
            return dateFormat;
        } else if (formatStr.equalsIgnoreCase(DATATIMEF_STR)) {
            return dateTimeFormat;
        } else if (formatStr.equalsIgnoreCase(ZHCN_DATAFORMAT_STR)) {
            return zhcnDateFormat;
        } else if (formatStr.equalsIgnoreCase(ZHCN_DATATIMEF_STR)) {
            return zhcnDateTimeFormat;
        } else if (formatStr.equalsIgnoreCase(ZHCN_DATATIMEF_STR_4yMMddHHmm)) {
            return zhcnDateTime4yMMddHHmm;
        } else if (formatStr.equalsIgnoreCase(ZHCN_DATATIMEF_STR_MMddHHmm)) {
            return zhcnDateTimeMMddHHmm;
        } else {
            return new SimpleDateFormat(formatStr);
        }
    }

    /**
     * 按照默认显示日期时间的格式"yyyy-MM-dd HH:mm:ss"，转化dateTimeStr为Date类型
     * dateTimeStr必须是"yyyy-MM-dd HH:mm:ss"的形式
     *
     * @param dateTimeStr
     * @return
     */
    public Date getDate(String dateTimeStr) {
        return getDate(dateTimeStr, DATAFORMAT_STR);
    }

    /**
     * 按照默认formatStr的格式，转化dateTimeStr为Date类型
     * dateTimeStr必须是formatStr的形式
     *
     * @param dateTimeStr
     * @param formatStr
     * @return
     */
    public Date getDate(String dateTimeStr, String formatStr) {
        try {
            if (dateTimeStr == null || dateTimeStr.equals("")) {
                return null;
            }
            DateFormat sdf = getDateFormat(formatStr);
            return sdf.parse(dateTimeStr);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将YYYYMMDD转换成Date日期
     *
     * @param date
     * @return
     * @throws
     */
    public Date transferDate(String date) throws Exception {
        if (date == null || date.length() < 1)
            return null;

        if (date.length() != 8)
            throw new Exception("日期格式错误");
        String con = "-";

        String yyyy = date.substring(0, 4);
        String mm = date.substring(4, 6);
        String dd = date.substring(6, 8);

        int month = Integer.parseInt(mm);
        int day = Integer.parseInt(dd);
        if (month < 1 || month > 12 || day < 1 || day > 31)
            throw new Exception("日期格式错误");

        String str = yyyy + con + mm + con + dd;
        return getDate(str, DateUtils.DATAFORMAT_STR);
    }

    /**
     *
     *
     *
     */
    public static String getNowDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dd = format.format(date);

        return dd;
    }

    /**
     * 将YYYY－MM－DD日期转换成yyyymmdd格式字符串
     *
     * @param date
     * @return
     */
    public String getYYYYMMDDDate(Date date) {
        if (date == null)
            return null;
        String yyyy = getYear(date) + "";
        String mm = getMonth(date) + "";
        String dd = getDay(date) + "";

        mm = StringUtils.rightPad(mm, 2, "0");
        dd = StringUtils.rightPad(dd, 2, "0");
        return yyyy + mm + dd;
    }

    /**
     * 将YYYY－MM－DD日期转换成YYYYMMDDHHMMSS格式字符串
     *
     * @param date
     * @return
     */
    public String getYYYYMMDDHHMMSSDate(Date date) {
        if (date == null)
            return null;
        String yyyy = getYear(date) + "";
        String mm = getMonth(date) + "";
        String dd = getDay(date) + "";
        String hh = getHour(date) + "";
        String min = getMin(date) + "";
        String ss = getSecond(date) + "";

        mm = StringUtils.rightPad(mm, 2, "0");
        dd = StringUtils.rightPad(dd, 2, "0");
        hh = StringUtils.rightPad(hh, 2, "0");
        min = StringUtils.rightPad(min, 2, "0");
        ss = StringUtils.rightPad(ss, 2, "0");

        return yyyy + mm + dd + hh + min + ss;
    }

    /**
     * 将YYYY－MM－DD日期转换成yyyymmdd格式字符串
     *
     * @param date
     * @return
     */
    public String getYYYYMMDDDate(String date) {
        return getYYYYMMDDDate(getDate(date, DATAFORMAT_STR));
    }

    /**
     * 将Date转换成字符串“yyyy-mm-dd hh:mm:ss”的字符串
     *
     * @param date
     * @return
     */
    public String dateToDateString(Date date) {
        return dateToDateString(date, DATATIMEF_STR);
    }

    /**
     * 将Date转换成formatStr格式的字符串
     *
     * @param date
     * @param formatStr
     * @return
     */
    public String dateToDateString(Date date, String formatStr) {
        DateFormat df = getDateFormat(formatStr);
        return df.format(date);
    }

    /**
     * 返回一个yyyy-MM-dd HH:mm:ss 形式的日期时间字符串中的HH:mm:ss
     *
     * @param dateTime
     * @return
     */
    public String getTimeString(String dateTime) {
        return getTimeString(dateTime, DATATIMEF_STR);
    }

    /**
     * 返回一个formatStr格式的日期时间字符串中的HH:mm:ss
     *
     * @param dateTime
     * @param formatStr
     * @return
     */
    public String getTimeString(String dateTime, String formatStr) {
        Date d = getDate(dateTime, formatStr);
        String s = dateToDateString(d);
        return s.substring(DATATIMEF_STR.indexOf('H'));
    }

    /**
     * 获取当前日期yyyy-MM-dd的形式
     *
     * @return
     */
    public String getCurDate() {
        //return dateToDateString(new Date(),DATAFORMAT_STR);
        return dateToDateString(Calendar.getInstance().getTime(), DATAFORMAT_STR);
    }

    /**
     * 获取当前日期yyyy年MM月dd日的形式
     *
     * @return
     */
    public String getCurZhCNDate() {
        return dateToDateString(new Date(), ZHCN_DATAFORMAT_STR);
    }

    /**
     * 获取当前日期时间yyyy-MM-dd HH:mm:ss的形式
     *
     * @return
     */
    public String getCurDateTime() {
        return dateToDateString(new Date(), DATATIMEF_STR);
    }

    /**
     * 获取当前日期时间yyyy年MM月dd日HH时mm分ss秒的形式
     *
     * @return
     */
    public String getCurZhCNDateTime() {
        return dateToDateString(new Date(), ZHCN_DATATIMEF_STR);
    }

    public String getCurZhCNDateTime4yMMddHHmm() {
        return dateToDateString(new Date(), ZHCN_DATATIMEF_STR_4yMMddHHmm);
    }

    /**
     * 获取日期d的days天后的一个Date
     *
     * @param d
     * @param days
     * @return
     */
    public Date getInternalDateByDay(Date d, int days) {
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        now.setTime(d);
        now.add(Calendar.DATE, days);
        return now.getTime();
    }

    public Date getInternalDateByMon(Date d, int months) {
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        now.setTime(d);
        now.add(Calendar.MONTH, months);
        return now.getTime();
    }

    public Date getInternalDateByYear(Date d, int years) {
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        now.setTime(d);
        now.add(Calendar.YEAR, years);
        return now.getTime();
    }

    public Date getInternalDateBySec(Date d, int sec) {
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        now.setTime(d);
        now.add(Calendar.SECOND, sec);
        return now.getTime();
    }

    public Date getInternalDateByMin(Date d, int min) {
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        now.setTime(d);
        now.add(Calendar.MINUTE, min);
        return now.getTime();
    }

    public Date getInternalDateByHour(Date d, int hours) {
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        now.setTime(d);
        now.add(Calendar.HOUR_OF_DAY, hours);
        return now.getTime();
    }

    /**
     * 根据一个日期字符串，返回日期格式，目前支持4种
     * 如果都不是，则返回null
     *
     * @param DateString
     * @return
     */
    public String getFormateStr(String DateString) {
        String patternStr1 = "[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}"; //yyyy-MM-dd
        String patternStr2 = "[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}\\s[0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}"; //yyyy-MM-dd HH:mm:ss;
        String patternStr3 = "[0-9]{4}年[0-9]{1,2}月[0-9]{1,2}日";//yyyy年MM月dd日
        String patternStr4 = "[0-9]{4}年[0-9]{1,2}月[0-9]{1,2}日[0-9]{1,2}时[0-9]{1,2}分[0-9]{1,2}秒";//yyyy年MM月dd日HH时mm分ss秒

        Pattern p = Pattern.compile(patternStr1);
        Matcher m = p.matcher(DateString);
        boolean b = m.matches();
        if (b)
            return DATAFORMAT_STR;
        p = Pattern.compile(patternStr2);
        m = p.matcher(DateString);
        b = m.matches();
        if (b)
            return DATATIMEF_STR;

        p = Pattern.compile(patternStr3);
        m = p.matcher(DateString);
        b = m.matches();
        if (b)
            return ZHCN_DATAFORMAT_STR;

        p = Pattern.compile(patternStr4);
        m = p.matcher(DateString);
        b = m.matches();
        if (b)
            return ZHCN_DATATIMEF_STR;
        return null;
    }

    /**
     * 将一个"yyyy-MM-dd HH:mm:ss"字符串，转换成"yyyy年MM月dd日HH时mm分ss秒"的字符串
     *
     * @param dateStr
     * @return
     */
    public String getZhCNDateTime(String dateStr) {
        Date d = getDate(dateStr);
        return dateToDateString(d, ZHCN_DATATIMEF_STR);
    }

    public String getZhCNDateTime4yMMddHHmm(String dateStr) {
        Date d = getDate(dateStr);
        return dateToDateString(d, ZHCN_DATATIMEF_STR_4yMMddHHmm);
    }

    /**
     * 将一个"yyyy-MM-dd"字符串，转换成"yyyy年MM月dd日"的字符串
     *
     * @param dateStr
     * @return
     */
    public String getZhCNDate(String dateStr) {
        Date d = getDate(dateStr, DATAFORMAT_STR);
        return dateToDateString(d, ZHCN_DATAFORMAT_STR);
    }

    /**
     * 将dateStr从fmtFrom转换到fmtTo的格式
     *
     * @param dateStr
     * @param fmtFrom
     * @param fmtTo
     * @return
     */
    public String getDateStr(String dateStr, String fmtFrom, String fmtTo) {
        Date d = getDate(dateStr, fmtFrom);
        return dateToDateString(d, fmtTo);
    }

    /**
     * 比较两个"yyyy-MM-dd HH:mm:ss"格式的日期，之间相差多少毫秒,time2-time1
     *
     * @param time1
     * @param time2
     * @return
     */
    public long compareDateStr(String time1, String time2) {
        Date d1 = getDate(time1);
        Date d2 = getDate(time2);
        return d2.getTime() - d1.getTime();
    }

    /**
     * 将小时数换算成返回以毫秒为单位的时间
     *
     * @param hours
     * @return
     */
    public long getMicroSec(BigDecimal hours) {
        BigDecimal bd;
        bd = hours.multiply(new BigDecimal(3600 * 1000));
        return bd.longValue();
    }

    /**
     * 获取Date中的分钟
     *
     * @param d
     * @return
     */
    public int getMin(Date d) {
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        now.setTime(d);
        return now.get(Calendar.MINUTE);
    }

    /**
     * 获取Date中的小时(24小时)
     *
     * @param d
     * @return
     */
    public  static  int getHour(Date d) {
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        now.setTime(d);
        return now.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取Date中的秒
     *
     * @param d
     * @return
     */
    public int getSecond(Date d) {
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        now.setTime(d);
        return now.get(Calendar.SECOND);
    }

    /**
     * 获取xxxx-xx-xx的日
     *
     * @param d
     * @return
     */
    public int getDay(Date d) {
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        now.setTime(d);
        return now.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取月份，1-12月
     *
     * @param d
     * @return
     */
    public int getMonth(Date d) {
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        now.setTime(d);
        return now.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取19xx,20xx形式的年
     *
     * @param d
     * @return
     */
    public int getYear(Date d) {
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        now.setTime(d);
        return now.get(Calendar.YEAR);
    }

    /**
     * 得到d的上个月的年份+月份,如200505
     *
     * @return
     */
    public String getYearMonthOfLastMon(Date d) {
        Date newdate = getInternalDateByMon(d, -1);
        String year = String.valueOf(getYear(newdate));
        String month = String.valueOf(getMonth(newdate));
        return year + month;
    }

    /**
     * 得到当前日期的年和月如200509
     *
     * @return String
     */
    public String getCurYearMonth() {
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        String DATE_FORMAT = "yyyyMM";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getDefault());
        return (sdf.format(now.getTime()));
    }

    public Date getNextMonth(String year, String month) {
        String datestr = year + "-" + month + "-01";
        Date date = getDate(datestr, DATAFORMAT_STR);
        return getInternalDateByMon(date, 1);
    }

    public Date getLastMonth(String year, String month) {
        String datestr = year + "-" + month + "-01";
        Date date = getDate(datestr, DATAFORMAT_STR);
        return getInternalDateByMon(date, -1);
    }

    /**
     * 得到日期d，按照页面日期控件格式，如"2001-3-16"
     *
     * @param d
     * @return
     */
    public String getSingleNumDate(Date d) {
        return dateToDateString(d, DATAFORMAT_STR);
    }

    /**
     * 得到d半年前的日期,"yyyy-MM-dd"
     *
     * @param d
     * @return
     */
    public String getHalfYearBeforeStr(Date d) {
        return dateToDateString(getInternalDateByMon(d, -6), DATAFORMAT_STR);
    }

    /**
     * 得到当前日期D的月底的前/后若干天的时间,<0表示之前，>0表示之后
     *
     * @param d
     * @param days
     * @return
     */
    public String getInternalDateByLastDay(Date d, int days) {

        return dateToDateString(getInternalDateByDay(d, days), DATAFORMAT_STR);
    }

    /**
     * 日期中的年月日相加
     *
     * @param field  int  需要加的字段  年 月 日
     * @param amount int 加多少
     * @return String
     */
    public String addDate(int field, int amount) {
        int temp = 0;
        if (field == 1) {
            temp = Calendar.YEAR;
        }
        if (field == 2) {
            temp = Calendar.MONTH;
        }
        if (field == 3) {
            temp = Calendar.DATE;
        }

        String Time = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance(TimeZone.getDefault());
            cal.add(temp, amount);
            Time = sdf.format(cal.getTime());
            return Time;
        } catch (Exception e) {

            return null;
        }

    }

    /**
     * 获得系统当前月份的天数
     *
     * @return
     */
    public int getCurentMonthDay() {
        Date date = Calendar.getInstance().getTime();
        return getMonthDay(date);
    }

    /**
     * 获得指定日期月份的天数
     *
     * @return
     */
    public int getMonthDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.getActualMaximum(Calendar.DAY_OF_MONTH);

    }

    /**
     * 获得指定日期月份的天数  yyyy-mm-dd
     *
     * @return
     */
    public int getMonthDay(String date) {
        Date strDate = getDate(date, DATAFORMAT_STR);
        return getMonthDay(strDate);

    }

    public String getStringDate(Calendar cal) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(cal.getTime());
    }

    /**
     * 计算两个日期相差天数，仅计算yyyy-MM-dd部分
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return
     */
    public static int getDifferDateDay(Date date1, Date date2) {
        long differ = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date1 = sdf.parse(sdf.format(date1));
            date2 = sdf.parse(sdf.format(date2));
            long time1 = date1.getTime();
            long time2 = date2.getTime();
            differ = (time1 < time2) ? time2 - time1 : time1 - time2;
        } catch (ParseException e) {

        }
        return (int) (differ / (1000 * 60 * 60 * 24));
    }

    /**
     * 计算两个日期相差小时数
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return
     */
    public int getDifferDateHour(Date date1, Date date2) {
        long differ = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date1 = sdf.parse(sdf.format(date1));
            date2 = sdf.parse(sdf.format(date2));
            long time1 = date1.getTime();
            long time2 = date2.getTime();
            differ = (time1 < time2) ? time2 - time1 : time1 - time2;
        } catch (ParseException e) {

        }
        return (int) (differ / (1000 * 60 * 60));
    }

    /**
     * 计算两个日期相差分钟数
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return
     */
    public int getDifferDateMinute(Date date1, Date date2) {
        long differ = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date1 = sdf.parse(sdf.format(date1));
            date2 = sdf.parse(sdf.format(date2));
            long time1 = date1.getTime();
            long time2 = date2.getTime();
            differ = (time1 < time2) ? time2 - time1 : time1 - time2;
        } catch (ParseException e) {

        }
        return (int) (differ / (1000 * 60));
    }

    /**
     * 计算两个日期相差秒数
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return
     */
    public static int getDifferDateSecond(Date date1, Date date2) {
        long differ = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date1 = sdf.parse(sdf.format(date1));
            date2 = sdf.parse(sdf.format(date2));
            long time1 = date1.getTime();
            long time2 = date2.getTime();
            differ = (time1 < time2) ? time2 - time1 : time1 - time2;
        } catch (ParseException e) {

        }
        return (int) (differ / 1000);
    }

    /**
     * 判断日期是星期几
     *
     * @param date 日期
     * @param fmt  格式，0表示返回数字格式，1表示返回中文格式，2返回英文前3位
     * @return
     */
    public String getDayOfWeek(Date date, int fmt) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayOfWeek = (c.get(Calendar.DAY_OF_WEEK) == 1) ? 7 : c.get(Calendar.DAY_OF_WEEK) - 1;
        String dayOfWeekStr = null;
        switch (dayOfWeek) {
            case 1:
                dayOfWeekStr = (0 == fmt) ? "1" : ((1 == fmt) ? "一" : "Mon");
                break;
            case 2:
                dayOfWeekStr = (0 == fmt) ? "2" : ((1 == fmt) ? "二" : "Tue");
                break;
            case 3:
                dayOfWeekStr = (0 == fmt) ? "3" : ((1 == fmt) ? "三" : "Wed");
                break;
            case 4:
                dayOfWeekStr = (0 == fmt) ? "4" : ((1 == fmt) ? "四" : "Thu");
                break;
            case 5:
                dayOfWeekStr = (0 == fmt) ? "5" : ((1 == fmt) ? "五" : "Fri");
                break;
            case 6:
                dayOfWeekStr = (0 == fmt) ? "6" : ((1 == fmt) ? "六" : "Sat");
                break;
            case 7:
                dayOfWeekStr = (0 == fmt) ? "7" : ((1 == fmt) ? "七" : "Sun");
                break;
        }
        return dayOfWeekStr;
    }

    /**
     * 月份的转换，从数字转3位英文或从3位英文转数字
     *
     * @param month 月份
     * @param type  转换类型，0从数字转3位英文，1从三位英文转数字
     * @return
     */
    public String getMonth(String month, int type) {
        String result = null;
        if (0 == type) {
            if ("1".equals(month) || "01".equals(month)) {
                result = "Jan";
            } else if ("2".equals(month) || "02".equals(month)) {
                result = "Feb";
            } else if ("3".equals(month) || "03".equals(month)) {
                result = "Mar";
            } else if ("4".equals(month) || "04".equals(month)) {
                result = "Apr";
            } else if ("5".equals(month) || "05".equals(month)) {
                result = "May";
            } else if ("6".equals(month) || "06".equals(month)) {
                result = "Jun";
            } else if ("7".equals(month) || "07".equals(month)) {
                result = "Jul";
            } else if ("8".equals(month) || "08".equals(month)) {
                result = "Aug";
            } else if ("9".equals(month) || "09".equals(month)) {
                result = "Sep";
            } else if ("10".equals(month)) {
                result = "Oct";
            } else if ("11".equals(month)) {
                result = "Nov";
            } else if ("12".equals(month)) {
                result = "Dec";
            }
        } else if (1 == type) {
            if ("JAN".equals(month.toUpperCase())) {
                result = "01";
            } else if ("FEB".equals(month.toUpperCase())) {
                result = "02";
            } else if ("MAR".equals(month.toUpperCase())) {
                result = "03";
            } else if ("APR".equals(month.toUpperCase())) {
                result = "04";
            } else if ("MAY".equals(month.toUpperCase())) {
                result = "05";
            } else if ("JUN".equals(month.toUpperCase())) {
                result = "06";
            } else if ("JUL".equals(month.toUpperCase())) {
                result = "07";
            } else if ("AUG".equals(month.toUpperCase())) {
                result = "08";
            } else if ("SEP".equals(month.toUpperCase())) {
                result = "09";
            } else if ("OCT".equals(month.toUpperCase())) {
                result = "10";
            } else if ("NOV".equals(month.toUpperCase())) {
                result = "11";
            } else if ("DEC".equals(month.toUpperCase())) {
                result = "12";
            }
        }
        return result;
    }

    /**
     * 在某一个日期上加上或减去一个时间量
     *
     * @param date   日期
     * @param field  Calendar类中的日历字段
     * @param amount 要加上或减去的时间量
     * @return
     */
    public Date calendarAdd(Date date, int field, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(field, amount);
        return c.getTime();
    }

    /**
     * 在某一个日期上加上或减去一个时间量
     *
     * @param date      日期
     * @param field     Calendar类中的日历字段
     * @param amount    要加上或减去的时间量
     * @param formatStr 格式化字符串
     * @return
     */
    public String calendarAddToString(Date date, int field, int amount, String formatStr) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(field, amount);
        return dateToDateString(c.getTime(), formatStr);
    }

    public static String dateFormatDB(Date date) {
        return dateFormatDB.format(date);
    }

    public static Date dateFormatDB(String date) {
        try {
            return dateFormatDB.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String dateFormatShortDB(Date date) {
        if (date == null) {
            return "";
        }
        return dateFormatShortDB.format(date);
    }

    public static Date dateFormatShortDB(String date) {
        try {
            return dateFormatShortDB.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    //字符串日期差 格式为yyyy-MM-dd
    public static int daysBetween(String smdate, String bdate) {
        long between_days = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(smdate));
            long time1 = cal.getTimeInMillis();
            cal.setTime(sdf.parse(bdate));
            long time2 = cal.getTimeInMillis();
            between_days = (time2 - time1) / (1000 * 3600 * 24);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(String.valueOf(between_days));
    }


    /**
     * 计算时间差，X天X小时X分X秒
     *
     * @param dateStart
     * @param dateStop
     * @param formatStr 格式 yyyy-MM-dd HH:mm:ss 或者 yyyy-MM-dd HH:mm
     * @return
     */
    public String differenceOfDate(String dateStart, String dateStop, String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);

        Date d1 = null;
        Date d2 = null;
        String resultStr = "";
        try {
            d1 = format.parse(dateStart);
            d2 = format.parse(dateStop);

            //毫秒ms
            long diff = (d1.getTime() < d2.getTime()) ? d2.getTime() - d1.getTime() : d1.getTime() - d2.getTime();

            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);


            if (StringUtils.isNotBlank(Long.toString(diffDays)) && diffDays != 0) {
                resultStr += diffDays + "天";
            }
            if (StringUtils.isNotBlank(Long.toString(diffHours)) && diffHours != 0) {
                resultStr += diffHours + "小时";
            }
            if (StringUtils.isNotBlank(Long.toString(diffMinutes)) && diffMinutes != 0) {
                resultStr += diffMinutes + "分";
            }
            if (StringUtils.isNotBlank(Long.toString(diffSeconds)) && diffSeconds != 0) {
                resultStr += diffSeconds + "秒";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultStr;
    }

    /**
     * 计算时间差，X天X小时X分X秒
     *
     * @param dateStart
     * @param dateStop
     * @param formatStr 格式 yyyy-MM-dd HH:mm:ss 或者 yyyy-MM-dd HH:mm
     * @return
     */
    public Integer differenceOfDateForMinutes(String dateStart, String dateStop, String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        int minutes = 0;
        Date d1 = null;
        Date d2 = null;
        String resultStr = "";
        try {
            d1 = format.parse(dateStart);
            d2 = format.parse(dateStop);


            minutes = (int) ((d1.getTime() - d2.getTime()) / (1000 * 60));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return minutes;
    }
    /**
     * 计算时间差，X天X小时X分X秒
     *
     * @param dateStart
     * @param dateStop
     * @param formatStr 格式 yyyy-MM-dd HH:mm:ss 或者 yyyy-MM-dd HH:mm
     * @return
     */
    public Integer differenceOfDateFordiffSeconds(String dateStart, String dateStop, String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        int diffSeconds = 0;
        Date d1 = null;
        Date d2 = null;
        String resultStr = "";
        try {
            d1 = format.parse(dateStart);
            d2 = format.parse(dateStop);


            diffSeconds = (int) ((d1.getTime() - d2.getTime()) / (1000 * 60));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return diffSeconds;
    }


    /**
     * 根据“yyyyMMddhhmmss”返回Date
     *
     * @param yyyyMMddhhmmss
     * @return
     */
    public Date getDateYYYYMMDDHHMMSS(String yyyyMMddhhmmss) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        Date date = null;
        try {
            date = sdf.parse(yyyyMMddhhmmss);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public static void main(String[] args) {


        String word =
                " YPAFR0S+yG0+k+qjk2kfoeDqsQ/Xv6VC9u7F4wAm0iJ+wM3c6ozWjkLsDcjTdRVfPG5VI4Sl0hTW7vbtnKuBmA==";
        try {
            System.out.println(ConfigTools.decrypt(word));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

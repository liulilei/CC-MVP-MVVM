package com.androiddesk.base.component.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TimeUtils {
    public static final long S = 1000;
    public static final long M = 60 * S;
    public static final long H = 60 * M;
    public static final long D = 24L * H;

    public static String updateTime(long time) {
        long currTime = System.currentTimeMillis();
        long diff = currTime - time;
        if (diff < M) {
            return "一分钟内";
        } else if (diff < H) {
            return diff / M + "分钟前";
        } else {
            Calendar given = Calendar.getInstance();
            given.setTimeInMillis(time);
            extractDate(given);
            Calendar curr = Calendar.getInstance();
            curr.setTimeInMillis(currTime);
            extractDate(curr);
            int diffDate = (int) ((curr.getTimeInMillis() - given
                    .getTimeInMillis()) / D);
            if (diffDate == 0 || diff < 6 * H) {
                return diff / H + "小时前";
            } else if (diffDate == 1) {
                return "昨天";
            } else if (diffDate < 6) {
                return diffDate + "天前";
            } else if (given.get(Calendar.YEAR) == curr.get(Calendar.YEAR)) {
                SimpleDateFormat format = new SimpleDateFormat("MM月dd日");
                return format.format(given.getTime());
            } else {
                SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
                return format.format(given.getTime());
            }
        }
    }

    public static String diffTime(long oldTime) {
        return diffTime(oldTime, System.currentTimeMillis());
    }

    public static String diffTime(long oldTime, long newTime) {
        long diffTime = newTime - oldTime;
        if (diffTime < M) {
            return "刚刚";
        } else if (diffTime < H) {
            return diffTime / M + "分钟前";
        } else {
            Calendar oldDate = Calendar.getInstance();
            oldDate.setTimeInMillis(oldTime);
            extractDate(oldDate);
            Calendar newDate = Calendar.getInstance();
            newDate.setTimeInMillis(newTime);
            int diffDate = (int) ((newDate.getTimeInMillis() - oldDate
                    .getTimeInMillis()) / D);
            if (diffDate == 0 || diffTime < 6 * H) {
                return diffTime / H + "小时前";
            } else if (diffDate == 1) {
                return "昨天";
            } else if (diffDate < 6) {
                return diffDate + "天前";
            } else if (oldDate.get(Calendar.YEAR) == newDate.get(Calendar.YEAR)) {
                SimpleDateFormat format = new SimpleDateFormat("MM月dd日");
                return format.format(oldDate.getTime());
            } else {
                SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
                return format.format(oldDate.getTime());
            }
        }
    }

    public static String commentCreateTime(long createTime) {
        long diffTime = System.currentTimeMillis() - createTime;
        if (diffTime < M) {
            return "刚刚";
        } else if (diffTime < H) {
            return diffTime / M + "分钟前";
        } else {
            Calendar oldDate = Calendar.getInstance();
            oldDate.setTimeInMillis(createTime);
            extractDate(oldDate);
            Calendar newDate = Calendar.getInstance();
            newDate.setTimeInMillis(System.currentTimeMillis());
            int diffDate = (int) ((newDate.getTimeInMillis() - oldDate
                    .getTimeInMillis()) / D);
            if (diffDate < 1) {
                return diffTime / H + "小时前";
            } else if (diffDate < 4) {
                return diffDate + "天前";
            } else if (oldDate.get(Calendar.YEAR) == newDate.get(Calendar.YEAR)) {
                SimpleDateFormat format = new SimpleDateFormat("MM月dd日");
                return format.format(oldDate.getTime());
            } else {
                SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
                return format.format(oldDate.getTime());
            }
        }
    }

    private static void extractDate(Calendar calendar) {
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
    }

    public static String time(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        Calendar curr = Calendar.getInstance();
        curr.setTimeInMillis(System.currentTimeMillis());
        if (calendar.get(Calendar.YEAR) == curr.get(Calendar.YEAR)) {
            SimpleDateFormat format = new SimpleDateFormat("MM - dd  HH:mm");
            return format.format(calendar.getTime());
        } else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy - MM - dd  HH:mm");
            return format.format(calendar.getTime());
        }
    }

    public static String caseCreateTime(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        Calendar curr = Calendar.getInstance();
        curr.setTimeInMillis(System.currentTimeMillis());
        if (calendar.get(Calendar.YEAR) == curr.get(Calendar.YEAR)) {
            SimpleDateFormat format = new SimpleDateFormat("MM月dd日  HH:mm");
            return format.format(calendar.getTime());
        } else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日  HH:mm");
            return format.format(calendar.getTime());
        }
    }

    public static String timeNoHM(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        Calendar curr = Calendar.getInstance();
        curr.setTimeInMillis(System.currentTimeMillis());
        if (calendar.get(Calendar.YEAR) == curr.get(Calendar.YEAR)) {
            SimpleDateFormat format = new SimpleDateFormat("MM月dd日");
            return format.format(calendar.getTime());
        } else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
            return format.format(calendar.getTime());
        }
    }

    public static String messageTime(long time) {
        String str = "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        long timeInMillis = calendar.getTimeInMillis();
        int second = calendar.get(Calendar.SECOND);
        Calendar curr = Calendar.getInstance();
        curr.setTimeInMillis(System.currentTimeMillis());
        long timeInMillis2 = curr.getTimeInMillis();
        int hour2 = curr.get(Calendar.HOUR_OF_DAY);
        int minute2 = curr.get((Calendar.MINUTE));
        int second2 = curr.get(Calendar.SECOND);
        int diffDate = (int) ((timeInMillis2 - timeInMillis) / D);
        int am_pm = calendar.get(Calendar.AM_PM);// 上午（下午）
        if (am_pm == 0) {
            str = str + "上午";
        } else if (am_pm == 1) {
            str = str + "下午";
        }

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        String hToHour = null;
        if (String.valueOf(hour).length() == 1) {
            hToHour = "0" + hour;
        } else {
            hToHour = String.valueOf(hour);
        }

        int minute = calendar.get(Calendar.MINUTE);
        String mToMinute = null;
        if (String.valueOf(minute).length() == 1) {
            mToMinute = "0" + minute;
        } else {
            mToMinute = String.valueOf(minute);
        }

        if (calendar.get(Calendar.YEAR) == curr.get(Calendar.YEAR)) {
            if (diffDate < 1) {
                if (hour > hour2) {
                    str = "昨天 " + str;
                } else if (hour < hour2) {
                    str = "今天 " + str;
                } else if (hour == hour2) {
                    if (minute == minute2) {
                        if (second <= second2) {
                            str = "今天 " + str;
                        } else {
                            str = "昨天 " + str;
                        }
                    } else if (minute < minute2) {
                        str = "今天 " + str;
                    } else if (minute > minute2) {
                        str = "昨天 " + str;
                    }
                }
            } else if (diffDate == 1) {
                if (hour < hour2) {
                    str = "昨天 " + str;
                } else if (hour > hour2) {
                    SimpleDateFormat format = new SimpleDateFormat("MM月dd日");
                    str = format.format(calendar.getTime()) + str;
                } else if (hour == hour2) {
                    if (minute == minute2) {
                        if (second <= second2) {
                            str = "昨天 " + str;
                        } else {
                            SimpleDateFormat format = new SimpleDateFormat("MM月dd日");
                            str = format.format(calendar.getTime()) + str;
                        }
                    } else if (minute < minute2) {
                        str = "昨天 " + str;
                    } else if (minute > minute2) {
                        SimpleDateFormat format = new SimpleDateFormat("MM月dd日");
                        str = format.format(calendar.getTime()) + str;
                    }
                }
            } else {
                SimpleDateFormat format = new SimpleDateFormat("MM月dd日");
                str = format.format(calendar.getTime()) + str;
            }
        } else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
            str = format.format(calendar.getTime()) + str;
        }

        return str + hToHour + ":" + mToMinute;
    }

    public static String beSpeakTime(Long time) {
        String str = "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        int day = calendar.get(Calendar.DAY_OF_YEAR);
        Calendar curr = Calendar.getInstance();
        curr.setTimeInMillis(System.currentTimeMillis());
        int day2 = curr.get(Calendar.DAY_OF_YEAR);
        if ((day - day2) == 0) {
            str = "今天";
        } else if ((day - day2) == 1) {
            str = "明天";
        } else if ((day - day2) == 2) {
            str = "后天";
        }
        return str + " ( " + fullTime(time) + " ) " + getHourAndMinute(time);
    }

    public static String hdrBespeakTimeHdr(long time) {
        String str;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        int day = calendar.get(Calendar.DAY_OF_YEAR);
        Calendar curr = Calendar.getInstance();
        curr.setTimeInMillis(System.currentTimeMillis());
        int day2 = curr.get(Calendar.DAY_OF_YEAR);
        if (calendar.get(Calendar.YEAR) == curr.get(Calendar.YEAR)) {
            if ((day - day2) == 0) {
                str = "今天";
            } else {
                SimpleDateFormat format = new SimpleDateFormat("MM-dd");
                str = format.format(calendar.getTime());
            }
        } else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            str = format.format(calendar.getTime());
        }
        return str;
    }

    public static String getHours(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(calendar.getTime());
    }

    public static String fullTime(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(calendar.getTime());
    }

    public static String fullTimeAndDayToSecond(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        return format.format(calendar.getTime());
    }

    public static String fullTimeAndDay(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm");
        return format.format(calendar.getTime());
    }

    public static String fullTimeAndDayWithDot(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd  HH:mm");
        return format.format(calendar.getTime());
    }

    public static String fullVariableTimeAndDay(long time) {
        //时间为当前年份的时候 不显示年份
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);//当前年份
        calendar.setTimeInMillis(time);
        SimpleDateFormat format = calendar.get(Calendar.YEAR) == year ?
                new SimpleDateFormat("MM-dd  HH:mm") : new SimpleDateFormat("yyyy-MM-dd  HH:mm");
        return format.format(calendar.getTime());
    }

    public static String fullVariableTimeAndDayInCh(long time) {
        //时间为当前年份的时候 不显示年份
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);//当前年份
        calendar.setTimeInMillis(time);
        SimpleDateFormat format = calendar.get(Calendar.YEAR) == year ?
                new SimpleDateFormat("MM月dd日") : new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(calendar.getTime());
    }

    public static String fullTimeYearMonth(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        SimpleDateFormat format = new SimpleDateFormat("yyyy 年 MM 月 dd 日");
        return format.format(calendar.getTime());
    }

    public static String fullTimeYearMonthDay(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(calendar.getTime());
    }

    public static String fullTimeYearMonthDay2(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        return format.format(calendar.getTime());
    }

    public static String fullTimeYearMonth2(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月");
        return format.format(calendar.getTime());
    }

    public static String getDay(long time) {
        Calendar t = Calendar.getInstance();
        t.setTimeInMillis(time);
        int day = t.get(Calendar.DAY_OF_MONTH);
        return day + "";
    }

    public static String fullTimeSolution(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        return format.format(calendar.getTime());
    }

    public static String getWeek(long time) {
        return getWeek(getDayOfWeek(time));
    }

    /**
     * 返回结果1为星期一 7为星期日 一天有86400000毫秒
     *
     * @param time
     * @return
     */
    public static int getDayOfWeek(long time) {
        Calendar data = Calendar.getInstance();
        data.setTimeInMillis(time);
        int dayOfWeek = data.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            return 7;
        } else {
            return dayOfWeek - 1;
        }
    }

    public static int getDayOfAMPM(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        int am_pm = calendar.get(Calendar.AM_PM);// 上午（下午）
        return am_pm;
    }


    public static int getCurrentYear() {
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        return now.get(Calendar.YEAR);
    }

    public static int getAge(Long time) {
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        Calendar birthday = Calendar.getInstance();
        birthday.setTime(new Date(time));
        return now.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
    }

    public static int getAge(long time) {
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        Calendar birthday = Calendar.getInstance();
        birthday.setTime(new Date(time));
        return now.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
    }

    /**
     * 返回 时分 08:30
     *
     * @param time
     * @return
     */
    public static String getHourAndMinute(long time) {
        Calendar t = Calendar.getInstance();
        t.setTimeInMillis(time);
        int hour = t.get(Calendar.HOUR_OF_DAY);
        int minute = t.get(Calendar.MINUTE);
        StringBuilder builder = new StringBuilder();
        if (hour < 10) {
            builder.append("0").append(hour);
        } else {
            builder.append(hour);
        }
        builder.append(":");
        if (minute < 10) {
            builder.append("0").append(minute);
        } else {
            builder.append(minute);
        }
        return builder.toString();
    }

    /**
     * 返回 分秒 08:30
     *
     * @param time
     * @return
     */
    public static String getMinuteAndSecond(int time) {
        int minute = time / 60;
        int second = time % 60;
        StringBuilder builder = new StringBuilder();
        if (minute > 0) {
            builder.append(minute);
            builder.append("'");
        }
        if (second < 10) {
            builder.append("0").append(second);
        } else {
            builder.append(second);
        }
        builder.append("''");
        return builder.toString();
    }

    /**
     * 返回 分秒 08:30
     *
     * @param time
     * @return
     */
    public static String getMinuteAndSecond2(int time) {
        int minute = time / 60;
        int second = time % 60;
        StringBuilder builder = new StringBuilder();
        if (minute < 10) {
            builder.append("0").append(minute);
        } else {
            builder.append(minute);
        }
        builder.append(":");
        if (second < 10) {
            builder.append("0").append(second);
        } else {
            builder.append(second);
        }
        return builder.toString();
    }


    public static String getLeftTime(long endTime, long nowTime) {
        long a = endTime - nowTime;
        int leftHour = (int) (a / H);
        if (leftHour > 24) {
            return a / D + "天";
        } else if (leftHour >= 1) {
            int minute = (int) ((int) (a % H) / M);
            return leftHour + "时" + minute + "分";
        } else {
            int minute = (int) (a / M);
            int second = (int) (a % M) / 1000;
            return minute + "分" + second + "秒";
        }
    }

    public static int getLeftTimeOfDay(long endTime, long nowTime) {
        long a = endTime - nowTime;
        int leftHour = (int) (a / H);
        if (leftHour > 0) {
            return (int) (a / D) + 1;
        } else {
            return 0;
        }
    }

    /**
     * 计算开始时间
     *
     * @param startTime
     * @param nowTime
     * @return
     */
    public static String getStartDay(long startTime, long nowTime) {
        Calendar start = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        start.setTimeInMillis(startTime);
        now.setTimeInMillis(nowTime);
        int nowDay = now.get(Calendar.DAY_OF_YEAR);
        int startDay = start.get(Calendar.DAY_OF_YEAR);
        int a = startDay - nowDay;
        switch (a) {
            case 0:
                return "今天";
            case 1:
                return "明天";
            case 2:
                return "后天";
            default:
                int nowweek = now.get(Calendar.WEEK_OF_YEAR);
                int startweek = start.get(Calendar.WEEK_OF_YEAR);
                int b = startweek - nowweek;
                int dayofweek = start.get(Calendar.DAY_OF_WEEK);
                if (dayofweek == 1) {
                    dayofweek = 7;
                } else if (dayofweek > 1 && dayofweek <= 7) {
                    dayofweek = dayofweek - 1;
                }
                if (b == 1 && dayofweek == 7) {         //如果是周日 显示为本周
                    b = 0;
                }
                switch (b) {
                    case 0:
                        return "周" + getWeek(dayofweek);
                    case 1:
                        return "下周" + getWeek(dayofweek);
                    default:
                        return (start.get(Calendar.MONTH) + 1) + "月" + start.get(Calendar.DAY_OF_MONTH) + "日";
                }
        }

    }

    /**
     * 1表示周一 7表示周日
     */
    public static String getWeek(int dayofweek) {
        switch (dayofweek) {
            case 1:
                return "一";
            case 2:
                return "二";
            case 3:
                return "三";
            case 4:
                return "四";
            case 5:
                return "五";
            case 6:
                return "六";
            case 7:
                return "日";
            default:
                return "";
        }

    }

    public static boolean isRefundTime(long payTime) {
        if ((System.currentTimeMillis() - payTime) > 7 * 24 * 60 * 60 * 1000) {
            return false;
        } else {
            return true;
        }
    }


    public static String timeSimple(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        Calendar curr = Calendar.getInstance();
        curr.setTimeInMillis(System.currentTimeMillis());
        if (calendar.get(Calendar.YEAR) == curr.get(Calendar.YEAR)) {
            SimpleDateFormat format = new SimpleDateFormat("MM-dd");
            return format.format(calendar.getTime());
        } else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            return format.format(calendar.getTime());
        }
    }


    public static String buyTime(long oldTime, long newTime) {
        long diffTime = newTime - oldTime;
        if (diffTime < H) {
            if (diffTime / M == 0) {
                return "刚刚";
            } else {
                return diffTime / M + "分钟前";
            }
        } else {
            Calendar oldDate = Calendar.getInstance();
            oldDate.setTimeInMillis(oldTime);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return format.format(oldDate.getTime());
        }
    }


    public static Long getTimes(int year, int month, int day, int hour, int min) {
        Date date = new Date(year - 1900, month, day, hour, min); // 获取时间转换为Date对象
        return date.getTime();
    }

    public static Long getVolunteerBeginTmie(Long durationTime) {
        Long currentTime = System.currentTimeMillis();
        Calendar curr = Calendar.getInstance();
        curr.setTimeInMillis(currentTime);
        int minute = curr.get((Calendar.MINUTE));
        if (minute > 0 && minute < 30) {
            currentTime = currentTime + ((30 - minute) * 60 * 1000) + durationTime;
        } else if (minute > 30 && minute <= 59) {
            currentTime = currentTime + ((60 - minute) * 60 * 1000) + durationTime;
        } else {
            currentTime = currentTime + durationTime;
        }
        return currentTime;
    }

    public static String getTimeAfterCreate(long createTime) {
        long differTime = System.currentTimeMillis() - createTime;
        if (differTime < H) {
            return differTime / M + "分钟前";
        } else if (differTime < D) {
            return differTime / H + "小时前";
        } else if (differTime < (30l * D)) {
            return differTime / D + "天前";
        } else {
            return differTime / (30 * D) + "月前";
        }
    }

    public static String getPromotionTime(long startTime, long endTime) {
        Calendar start = Calendar.getInstance();
        start.setTimeInMillis(startTime);
        Calendar end = Calendar.getInstance();
        end.setTimeInMillis(endTime);
        String startHour = start.get(Calendar.HOUR_OF_DAY) + "";
        String startMinute = start.get(Calendar.MINUTE) + "";
        if (startHour.length() < 2) {
            startHour = "0" + startHour;
        }
        if (startMinute.length() < 2) {
            startMinute = "0" + startMinute;
        }
        String endHour = end.get(Calendar.HOUR_OF_DAY) + "";
        String endMinute = end.get(Calendar.MINUTE) + "";
        if (endHour.length() < 2) {
            endHour = "0" + endHour;
        }
        if (endMinute.length() < 2) {
            endMinute = "0" + endMinute;
        }
        return (start.get(Calendar.MONTH) + 1) + "-" + start.get(Calendar.DAY_OF_MONTH) + " " + startHour + ":" + startMinute + "~" + endHour + ":" + endMinute;
    }

    public static Long getBespeakTime(int bespeakDay, int bespeakHour) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setTimeInMillis(System.currentTimeMillis());
        return getTimes(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get((Calendar.DAY_OF_MONTH)) + bespeakDay, bespeakHour, 0);
    }

    public static String getCNFullTime(Long revisitTime) {
        String str;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(revisitTime);
        String year = calendar.get(Calendar.YEAR) + "";
        int month = (calendar.get(Calendar.MONTH) + 1);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String dayStr = day + "";
        for (int i = 0; i < 10; i++) {
            year = year.replace(i + "", StringUtils.getIntToCN(i));
            dayStr = dayStr.replace(i + "", StringUtils.getIntToCN(i));
        }
        str = year + "年  ";
        if (month < 10) {
            str = str + StringUtils.getIntToCN(month) + "月  ";
        } else if (month == 10) {
            str = str + "十月  ";
        } else if (month == 11) {
            str = str + "十一月  ";
        } else if (month == 12) {
            str = str + "十二月  ";
        }
        if (day < 10) {
            str = str + dayStr + "日";
        } else if (day == 10) {
            str = str + "十日";
        } else if (day > 10 && day < 20) {
            str = str + ("十" + dayStr.substring(1, 2)) + "日";
        } else if (day == 20) {
            str = str + "二十日";
        } else if (day == 30) {
            str = str + "三十日";
        } else {
            str = str + dayStr.substring(0, 1) + "十" + dayStr.substring(1, 2) + "日";
        }
        return str;
    }

    /**
     * 坐诊日期(0:未知 1:周一 2:周二 ..... 7:周日)
     *
     * @param flag
     * @return
     */
    public static String getDayOfWeek(int flag) {
        switch (flag) {
            case 1:
                return "星期一";
            case 2:
                return "星期二";
            case 3:
                return "星期三";
            case 4:
                return "星期四";
            case 5:
                return "星期五";
            case 6:
                return "星期六";
            case 7:
                return "星期日";
            case 0:
            default:
                return "";
        }
    }

    /**
     * 坐诊时间(0:未知 1:上午 2:下午)
     *
     * @param flag
     * @return
     */
    public static String getIntervalOfDay(int flag) {
        switch (flag) {
            case 1:
                return "上午";
            case 2:
                return "下午";
            case 0:
            default:
                return "";
        }
    }

    public static String getTimeZoneDayOfYear(String timeZone) {
        TimeZone gmtTz = TimeZone.getTimeZone(timeZone);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(gmtTz);
        return calendar.get(Calendar.DAY_OF_YEAR) + "";
    }

    /**
     * 返回 早中晚
     * 0:0:0~11:59:59：上午好
     * 12:0:0~18:59:59：下午好
     * 19:0:0~23:59:59：晚上好
     *
     * @return
     */
    public static int periodOfDay() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour < 12) {
            return 0;
        }
        if (hour < 19) {
            return 1;
        }
        return 2;
    }

    public static String parseTime(String dateStr) throws ParseException {
        try {
            if (null != dateStr && !dateStr.equals("")) {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSZ");
                Date result;
                result = df.parse(dateStr);
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm MM/dd/yyyy");
//        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
                return sdf.format(result);
            } else {
                return "";
            }
        }catch (ParseException e){
            e.printStackTrace();
            return dateStr;
        }
    }

    public static String parseDate(String dateStr){
        try {
            if (null != dateStr && !dateStr.equals("")) {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
                Date result;
                result = df.parse(dateStr);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
                return sdf.format(result);
            } else {
                return "";
            }
        }catch (ParseException e){
            e.printStackTrace();
            return dateStr;
        }
    }

    public static String getDate(String dateStr) {
        try {
            if (null != dateStr && !dateStr.equals("")) {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
                Date result;
                result = df.parse(dateStr);
                SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
//        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
                return sdf.format(result);
            } else {
                return "";
            }
        }catch (ParseException e){
            e.printStackTrace();
            return dateStr;
        }
    }

    public static String getTime(String time) {
        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm:ss");
        Date date;
        if (!"".equals(time) && !"0".equals(time)) {
            if (time.length() == 13) {
                date = new Date(Long.parseLong(time));
            } else {
                date = new Date(Long.parseLong(time) * 1000);
            }
            return format.format(date);
        } else {
            return "";
        }
    }

    public static long getNoticeTime(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss Z");
        try {
            Date date = format.parse(time.replace("Z", " UTC"));
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String parseOrderTime(String dateStr) {
        try {
            if (null != dateStr && !dateStr.equals("")) {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSZ");
                Date result;
                result = df.parse(dateStr);
                return timeSimple(result.getTime());
            } else {
                return "";
            }
        }catch(ParseException e){
            e.printStackTrace();
            return dateStr;
        }
    }

}

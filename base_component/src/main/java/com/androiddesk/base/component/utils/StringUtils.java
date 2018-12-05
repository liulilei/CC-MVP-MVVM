package com.androiddesk.base.component.utils;


import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.StyleSpan;
import android.util.Base64;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StringUtils {
    private static final String TAG = "StringUtils";

    /**
     * Judge if a String with a null value.
     *
     * @param src
     * @return
     */
    public static boolean isNull(String src) {
        return src == null;
    }

    /**
     * Judge if a String with a null value or with zero length.
     *
     * @param src
     * @return
     */
    public static boolean isEmpty(String src) {
        return src == null || src.length() == 0;
    }

    /**
     * Judge if a String with all whitespace.
     *
     * @param src
     * @return
     */
    public static boolean isBlank(String src) {
        return src != null && "".equals(trimAll(src));
    }

    /**
     * Judge if a String equals value "null".
     *
     * @param src
     * @return
     */
    public static boolean equalsNull(String src) {
        return src != null && "null".equalsIgnoreCase(trimAll(src));
    }

    /**
     * Ignore all whitespace in a String.
     *
     * @param src
     * @return
     */
    public static String trimAll(String src) {
        if (src == null) {
            return null;
        }
        return src.replaceAll(" ", "");
    }

    /**
     * Judge if a String with a detail meaning.
     *
     * @param src
     * @return
     */
    public static boolean isMeaningful(String src) {
        return !isNull(src) && !isBlank(src) && !equalsNull(src);
    }

    /**
     * Change bytes to Hex String in lowercase.
     *
     * @param bytes
     * @return
     */
    public static String bytes2Hex(byte[] bytes) {
        return byte2Hex(bytes);
    }

    public static String byte2Hex(byte[] src) {
        if (src == null) {   //remove src.length<=0, just for new byte[0] -> ""
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                sb.append(0);
            }
            sb.append(hv);
        }
        return sb.toString();
    }

    /**
     * Parse a String into an int value, with default value 0 if exception(s) occur.
     *
     * @param input
     * @return
     */
    public static int toInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (Exception e) {
            LogUtils.Companion.e(TAG, e.toString());
        }
        return 0;
    }

    /**
     * Parse a String into a long value, with default value 0L if exception(s) occur.
     *
     * @param input
     * @return
     */
    public static long toLong(String input) {
        try {
            return Long.parseLong(input);
        } catch (Exception e) {
            LogUtils.Companion.e(TAG, e.toString());
        }
        return 0;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 格式化 double精度 保留两位小数 四舍五入
     *
     * @param price
     * @return
     */
    public static String getPrice(double price) {
        BigDecimal bg = new BigDecimal(price);
        double result = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return String.valueOf(result);
    }

    public static byte[] getBytes(char[] chars) {
        Charset cs = Charset.forName("UTF-8");
        CharBuffer cb = CharBuffer.allocate(chars.length);
        cb.put(chars);
        cb.flip();
        ByteBuffer bb = cs.encode(cb);

        return bb.array();

    }

    public static String encodeToString(char[] data) {
        String base64 = "";
        try {
            base64 = Base64.encodeToString(StringUtils.decodeHex(data), Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (base64.contains("\n")) {
            base64 = base64.replace("\n", "");
        }
        return base64;
    }

    /**
     * 将十六进制字符数组转换为字节数组
     *
     * @param data 十六进制char[]
     * @return byte[]
     * @throws RuntimeException 如果源十六进制字符数组是一个奇怪的长度，将抛出运行时异常
     */
    public static byte[] decodeHex(char[] data) throws Exception {
        int len = data.length;
        if ((len & 0x01) != 0) {
            throw new Exception("Odd number of characters.");
        }
        byte[] out = new byte[len >> 1];
        // two characters form the hex value.
        for (int i = 0, j = 0; j < len; i++) {
            int f = toDigit(data[j], j) << 4;
            j++;
            f = f | toDigit(data[j], j);
            j++;
            out[i] = (byte) (f & 0xFF);
        }
        return out;
    }

    /**
     * 将十六进制字符转换成一个整数
     *
     * @param ch    十六进制char
     * @param index 十六进制字符在字符数组中的位置
     * @return 一个整数
     * @throws RuntimeException 当ch不是一个合法的十六进制字符时，抛出运行时异常
     */
    protected static int toDigit(char ch, int index) {
        int digit = Character.digit(ch, 16);
        if (digit == -1) {
            throw new RuntimeException("Illegal hexadecimal character " + ch
                    + " at index " + index);
        }
        return digit;
    }

    /**
     * 如果价格 小数点后面全为0 则去掉0
     *
     * @return
     */
    public static String getServicePrice(double price) {
        String pricestr = price + "";
        String[] x = pricestr.split("\\.");
        if (x.length == 2) {
            if (x[1].equals("0") || x[1].equals("00")) {
                pricestr = x[0];
            }
        }
        return pricestr;
    }

    public static String getIntToCN(int number) {
        String[] cn = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一"};
        if (cn.length < number - 1) {
            return String.valueOf(number);
        }
        return cn[number];
    }

    /**
     * 判断手机号码是否正确
     *
     * @param number
     * @return
     */
    public static boolean isTlePhoneNumber(String number) {
        /**
         * 手机号(1开头, 11位)
         */
        String MOBILE = "^1\\d{10}$";
        Pattern p = Pattern.compile(MOBILE);
        Matcher m = p.matcher(number);
        return m.matches();
    }

    public static SpannableStringBuilder getStringSpannable(String str) {
        if (str.length() == 16) {
            SpannableStringBuilder sb = new SpannableStringBuilder(str);
            sb.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, 4, Spannable.SPAN_INCLUSIVE_INCLUSIVE); // make them also bold
            sb.setSpan(new AbsoluteSizeSpan(40), 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            sb.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 7, 9, Spannable.SPAN_INCLUSIVE_INCLUSIVE); // make them also bold
            sb.setSpan(new AbsoluteSizeSpan(40), 7, 9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            sb.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 12, 14, Spannable.SPAN_INCLUSIVE_INCLUSIVE); // make them also bold
            sb.setSpan(new AbsoluteSizeSpan(40), 12, 14, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return sb;
        } else {
            return null;
        }
    }

    /**
     * 获取客服id
     *
     * @param message
     * @return
     */
    public static int getMessageAsId(String message) {
        int asId = 1;
        if (!StringUtils.isEmpty(message)) {
            try {
                JSONObject object = new JSONObject(message);
                String ext = "";
                if (object != null) {
                    ext = object.getString("ext");
                }
                if (!StringUtils.isEmpty(ext)) {
                    JSONObject jsonObject = new JSONObject(ext);
                    if (jsonObject != null) {
                        asId = jsonObject.getInt("asId");
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return asId;
    }

    /**
     * 获取客服id
     *
     * @param extras
     * @return
     */
    public static int getExtAsId(String extras) {
        int asId = 1;
        if (!StringUtils.isEmpty(extras)) {
            try {
                JSONObject object = new JSONObject(extras);
                if (object != null) {
                    asId = object.getInt("asId");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return asId;
    }

    public static String addUrlParams(String url, String params, int result) {
        if (url.contains("?")) {
            url = url + "&" + params + "=" + result;
        } else {
            url = url + "?" + params + "=" + result;
        }
        return url;
    }

    public static String getCode() {
        String key = TimeUtils.getTimeZoneDayOfYear("GMT");
        long ms = System.currentTimeMillis();
        return encode(key, ms);
    }

    public static String encode(String keyStr, long val) {
        char[] key = keyStr.toCharArray();
        String sms = get10to36Code(val);
        char[] cms = sms.toCharArray();
        for (int i = 0; i < cms.length; i++) {
            cms[i] = (char) (cms[i] ^ (key[i % key.length]));
        }
        return new String(cms);
    }

    public static String get10to36Code(long val) {
        String res = "";
        while (val > 36) {
            int iVal = (int) (val % 36);
            if (iVal < 10) {
                res = iVal + res;
            } else {
                char cVal = (char) (iVal - 10 + 65);
                res = cVal + res;
            }
            val = val / 36;
        }
        if (val < 10) {
            res = val + res;
        } else {
            val = val - 10 + 65;
            char cVal = (char) val;
            res = cVal + res;
        }
        return res;
    }

    public static String getSocketUrl(String url, String token, long timestamp, int userId, int type) {
        return url + "token=" + token + "&timestamp=" + timestamp + "&sign=" + Md5.digest32(String.valueOf(timestamp) + userId + "_" + type + token);
    }

    /**
     * 数字0 1 判断
     *
     * @return
     */
    public static boolean isTrue(int value) {
        return value == 1;
    }

    /**
     * 获得一个UUID
     *
     * @return String UUID
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String getAndroidUUID() {
        return "android-" + getUUID();
    }
}

package com.androiddesk.base.component.utils;

import android.text.TextUtils;

import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class IOUtils {

    private static final String TAG = "IOUtils";

    private IOUtils() {
    }

    public static byte[] readBytesFrom(File file) {
        byte[] result = null;
        if (!file.exists()) {
            return result;
        }
        FileInputStream fis = null;
        ByteArrayOutputStream baos = null;
        try {
            fis = new FileInputStream(file);
            baos = new ByteArrayOutputStream();
            copyStream(fis, baos);
            result = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (baos != null) {
                try {
                    baos.close();
                    baos = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                    fis = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static void writeBytesTo(File file, byte[] bytes) {
        FileOutputStream fos = null;
        try {
            if (file.exists())
                file.delete();
            fos = new FileOutputStream(file);
            fos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String readStringFrom(File file) {
        String result = null;
        if (!file.exists()) {
            return result;
        }
        FileInputStream fis = null;
        ByteArrayOutputStream baos = null;
        try {
            fis = new FileInputStream(file);
            baos = new ByteArrayOutputStream();
            copyStream(fis, baos);
            result = new String(baos.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (baos != null) {
                try {
                    baos.close();
                    baos = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                    fis = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static void writeStringTo(File file, String content) {
        byte[] bytes = content.getBytes();
        writeBytesTo(file, bytes);
        content = null;
    }

    public static interface CopyStreamListener {
        public void onBuffering(long pos);
    }

    public static void copyStream(InputStream is, OutputStream os)
            throws IOException {
        copyStream(is, os, null);
    }

    public static void copyStream(InputStream is, OutputStream os,
                                  CopyStreamListener listener) throws IOException {
        byte buf[] = new byte[10240];
        int len = -1;

        int pos = 0;
        while ((len = is.read(buf, 0, 10240)) != -1) {
            os.write(buf, 0, len);
            pos += len;
            if (listener != null)
                listener.onBuffering(pos);
        }
    }

    /**
     * 转换数据
     *
     * @param is
     * @return
     * @throws IOException
     */
    public static ByteBuffer getByteBuffer(InputStream is) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(0);
        try {
            byte buf[] = new byte[1024];
            int len = is.read(buf, 0, 1024);
            while (len != -1) {
                //创建临时缓冲区
                ByteBuffer tmpbuf = ByteBuffer.allocate(buffer.capacity() + len);
                tmpbuf.put(buffer.array());//保存原缓冲区中的内容
                tmpbuf.put(buf, 0, len);//添加新的内容
                buffer = tmpbuf;
                len = is.read(buf, 0, 1024);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            is.close();
        }
        return buffer;
    }

    /**
     * 扩充ByteBuffer
     *
     * @param buffer
     * @param b
     * @return
     */
    public static ByteBuffer appendBuffer(ByteBuffer buffer, byte b[]) {
        if (b != null) {
            ByteBuffer newbuffer = ByteBuffer.allocate(buffer.capacity() + b.length);
            newbuffer.put(buffer.array());
            newbuffer.put(b);
            return newbuffer;
        } else {
            return buffer;
        }
    }

    /**
     * 数据转换
     *
     * @param buffer
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getString(ByteBuffer buffer, Charset encode) throws UnsupportedEncodingException {
        if (buffer == null) return "";
        byte bt[] = buffer.array();
        String str = "";
        if (buffer == null || bt.length == 0) {
            return "";
        } else {
            str = new String(bt, 0, bt.length, encode.name());
        }
        return str;
    }

    public static ByteBuffer getByteBuffer(String source) {
        ByteBuffer buffer = null;
        if (!TextUtils.isEmpty(source)) {
            byte bt[] = source.getBytes();
            buffer = ByteBuffer.allocate(bt.length);
            buffer.put(bt);

        }
        return buffer;
    }

    /**
     * 获得文件中的内容
     *
     * @param path
     * @param encode
     * @return
     */
    public static String getContent(String path, Charset encode) {
        File file = new File(path);
        if (!file.exists())
            return null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            ByteBuffer buffer = getByteBuffer(fis);
            return getString(buffer, encode);
        } catch (Exception e) {
            LogUtils.Companion.e(TAG, e.toString());
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static final void close(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static final void close(Reader reader) {
        if (reader != null) {
            try {
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Simple wrapper around {@link InputStream#read()} that throws EOFException
     * instead of returning -1.
     */
    private static int read(InputStream is) throws IOException {
        int b = is.read();
        if (b == -1) {
            throw new EOFException();
        }
        return b;
    }

    public static void writeInt(OutputStream os, int n) throws IOException {
        os.write((n >> 0) & 0xff);
        os.write((n >> 8) & 0xff);
        os.write((n >> 16) & 0xff);
        os.write((n >> 24) & 0xff);
    }

    public static int readInt(InputStream is) throws IOException {
        int n = 0;
        n |= (read(is) << 0);
        n |= (read(is) << 8);
        n |= (read(is) << 16);
        n |= (read(is) << 24);
        return n;
    }

    public static void writeLong(OutputStream os, long n) throws IOException {
        os.write((byte) (n >>> 0));
        os.write((byte) (n >>> 8));
        os.write((byte) (n >>> 16));
        os.write((byte) (n >>> 24));
        os.write((byte) (n >>> 32));
        os.write((byte) (n >>> 40));
        os.write((byte) (n >>> 48));
        os.write((byte) (n >>> 56));
    }

    public static long readLong(InputStream is) throws IOException {
        long n = 0;
        n |= ((read(is) & 0xFFL) << 0);
        n |= ((read(is) & 0xFFL) << 8);
        n |= ((read(is) & 0xFFL) << 16);
        n |= ((read(is) & 0xFFL) << 24);
        n |= ((read(is) & 0xFFL) << 32);
        n |= ((read(is) & 0xFFL) << 40);
        n |= ((read(is) & 0xFFL) << 48);
        n |= ((read(is) & 0xFFL) << 56);
        return n;
    }

    public static void writeString(OutputStream os, String s) throws IOException {
        byte[] b = s.getBytes("UTF-8");
        writeLong(os, b.length);
        os.write(b, 0, b.length);
    }

    public static String readString(InputStream is) throws IOException {
        int n = (int) readLong(is);
        byte[] b = streamToBytes(is, n);
        return new String(b, "UTF-8");
    }

    public static void writeStringStringMap(Map<String, String> map, OutputStream os) throws IOException {
        if (map != null) {
            writeInt(os, map.size());
            for (Map.Entry<String, String> entry : map.entrySet()) {
                writeString(os, entry.getKey());
                writeString(os, entry.getValue());
            }
        } else {
            writeInt(os, 0);
        }
    }

    public static Map<String, String> readStringStringMap(InputStream is) throws IOException {
        int size = readInt(is);
        Map<String, String> result = (size == 0)
                ? Collections.<String, String>emptyMap()
                : new HashMap<String, String>(size);
        for (int i = 0; i < size; i++) {
            String key = readString(is).intern();
            String value = readString(is).intern();
            result.put(key, value);
        }
        return result;
    }

    /**
     * Reads the contents of an InputStream into a byte[].
     */
    private static byte[] streamToBytes(InputStream in, int length) throws IOException {
        byte[] bytes = new byte[length];
        int count;
        int pos = 0;
        while (pos < length && ((count = in.read(bytes, pos, length - pos)) != -1)) {
            pos += count;
        }
        if (pos != length) {
            throw new IOException("Expected " + length + " bytes, read " + pos + " bytes");
        }
        return bytes;
    }

}

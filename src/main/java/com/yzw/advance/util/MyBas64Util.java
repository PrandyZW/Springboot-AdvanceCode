package com.yzw.advance.util;

import org.springframework.util.Base64Utils;

import java.util.ArrayList;
import java.util.List;

public class MyBas64Util {

    public static char[] base64 = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
            'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
            'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'
    };

    /**
     * 加密字符
     * @param bytes
     * @return
     */
    private static String encodeToBase64(byte[] bytes) {
        //记录编码的字节数被3除的余数
        int num = bytes.length % 3;
        StringBuffer stringBuffer = new StringBuffer();
        int v = 0;
        for (int i = 2; i < bytes.length; i += 3) {
            //用V来接收拼接后的数，由于byte数组中每一个是8位的二进制数，三个拼起来正好是24位
            v = ((bytes[i - 2] & 0xff) << 16) | ((bytes[i - 1] & 0xff) << 8) | (bytes[i] & 0xff);
            //在将v与0x3f进行&运算，每次取到六位，将其分成4组
            //与0x3f进行&运算，是因为0x3f的二进制码是0011 1111，中间包含六个1，只有在全一的时候才会为1，所以值取到了最后六位
            int d0 = v & 0x3f;
            int d1 = v >>> 6 & 0x3f;//>>>为无符号的位运算
            int d2 = v >>> 12 & 0x3f;
            int d3 = v >>> 18 & 0x3f;

            //将取得的4组6位数据查表后存入List中
            stringBuffer.append(base64[d3]);
            stringBuffer.append(base64[d2]);
            stringBuffer.append(base64[d1]);
            stringBuffer.append(base64[d0]);

        }
        //下面的操作是当编码的字节数不能被3整除时，在末尾添加等于号
        if (num > 1) {
            v = ((bytes[bytes.length - 2] & 0xff) << 16) | ((bytes[bytes.length - 1] & 0xff) << 8);

            int d1 = v >>> 6 & 0x3f;
            int d2 = v >>> 12 & 0x3f;
            int d3 = v >>> 18 & 0x3f;
            stringBuffer.append(base64[d3]);
            stringBuffer.append(base64[d2]);
            stringBuffer.append(base64[d1]);
            stringBuffer.append("=");
        }
        if (num == 1) {
            v = ((bytes[bytes.length - 1] & 0xff) << 16);
            int d2 = v >>> 12 & 0x3f;
            int d3 = v >>> 18 & 0x3f;
            stringBuffer.append(base64[d3]);
            stringBuffer.append(base64[d2]);
            stringBuffer.append("=");
            stringBuffer.append("=");
        }

        return stringBuffer.toString();
    }

    /**
     * 解密字符
     * @param string
     * @return
     */
    private static String decode(String string) {
        byte[] b = new byte[string.length()];
        char[] ch = string.toCharArray();
        int n = 0;
        //遍历Base64表获取下标并存入byte数组
        for (char c : ch) {
            for (int i = 0; i < base64.length; i++) {
                if (c == base64[i]) {
                    b[n] = (byte) i;
                    n++;
                }
            }
        }
        int v = 0;
        byte[] bytes = new byte[string.length() - 1];
        int num = 0;
        //将4组合并，并且分为3组每组8位
        for (int i = 0; i < b.length; i += 4) {
            v = ((b[i] & 0xff) << 18) | ((b[i + 1] & 0xff) << 12) | (b[i + 2] & 0xff) << 6 | (b[i + 3] & 0xff);
            int d0 = v & 0xff;
            int d1 = v >>> 8 & 0xff;
            int d2 = v >>> 16 & 0xff;
            bytes[num] = (byte) d2;
            bytes[num + 1] = (byte) d1;
            bytes[num + 2] = (byte) d0;
            num += 3;
        }
        //去除byte数组末尾的0，末尾有0才去除
        if (bytes[bytes.length - 1] == 0) {
            try {
                int length = 0;
                for (int i = 0; i < bytes.length; ++i) {
                    if (bytes[i] == 0) {
                        length = i;
                        break;
                    }
                }
                String str1 = new String(bytes, 0, length);
                return str1;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            String s1 = new String(bytes);
            return s1;
        }
        return null;
    }


    public static void main(String[] args) {


        String encode = encodeToBase64("中国，我爱你".getBytes());
        System.out.println("加密字符："+encode);
        String encode1 = new String(Base64Utils.encode("中国，我爱你".getBytes()));
        System.out.println("标准加密字符："+encode1);
        String decode = decode(encode);
        System.out.println("解密字符:"+decode);
        String decode1 = new String(Base64Utils.decode(encode1.getBytes()));
        System.out.println("标准解密字符："+decode1);

        System.out.println(encode.equals(encode1));
        System.out.println(decode.equals(decode1));
    }

}

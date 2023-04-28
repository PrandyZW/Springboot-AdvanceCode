package com.yzw.advance.util;

public class Base64 {


    /**
     * Base 64值参考表
     值	编码 	    值	编码        值 编码	值     编码
     0  A   17  R   34 i   51  z
     1  B   18  S   35 j   52  0
     2  C   19  T   36 k   53  1
     3  D   20  U   37 l   54  2
     4  E   21  V   38 m   55  3
     5  F   22  W   39 n   56  4
     6  G   23  X   40 o   57  5
     7  H   24  Y   41 P   58  6
     8  I   25  Z   42 q   59  7
     9  J   26  a   43 r   60  8
     10  K   27  b   44 s   61  9
     11  L   28  c   45 t   62  +
     12  M   29  d   46 u   63  /
     13  N   30  e   47 v
     14  O   31  f   48 w  （pad）=
     15  P   32  g   49 x
     16  Q   33  h   50 y
     */

    //根据rfc 4648和rfc 2045
    private static final byte[] STANDARD_ENCODE_TABLE = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'
    };

    public static void main(String[] args) {
        String encode = Encoder.encode("中");
        System.out.println("加密字符："+encode);
        String decode = Decoder.decode0(encode);
        System.out.println("解密密字符："+decode);
        //System.out.println(encode);
        //String decode = Decoder.decode("dGVzdHRlc3R0ZXN0dGVzdA==");
        //System.out.println(decode);
       // System.out.println(new String(Base64_2.getEncoder().encode("测试测试".getBytes())));
    }


//编码实例:"foobar1",请按标注的数字从1-5看,1.将字符串转为字节数组,2将字节数组换成二进制字符串,3.处理字节数组转为使每个元素长度为8,4.拼接数组为一个大字符串,
//	然后每6个长度为一组,最后一个如果长度不够,后面补0,每两个0记一个"=",并放入数组,5.最后将二进制字符串转为十进制,当做base64表中的下标找对应的值拼接起来,如果后面补0,则在后面拼接上"=",
//	最后可以直接返回字符串,或返回字符串的字节数组
    //解码过程就是倒着来

// 5	index列:[25, 38, 61, 47, 24, 38, 5, 50, 12, 16]
// 4	二进制字符串按6分组:[011001, 100110, 111101, 101111, 011000, 100110, 000101, 110010, 001100, 010000]
// 3	字符串转数组,按8补0后:[01100110, 01101111, 01101111, 01100010, 01100001, 01110010, 00110001]
// 2	字符串转数组:[1100110, 1101111, 1101111, 1100010, 1100001, 1110010, 110001]
// 1	待解码的文字的字节数组:[102, 111, 111, 98, 97, 114, 49]



    public static class Encoder{

        public static String encode(String str){
//			System.out.println("待编码的文字的字节数组:"+Arrays.toString(str.getBytes()));
            return encode(str.getBytes());
        }

        public static String encode(byte[] b){
            String strArr [] = new String[b.length];
            for (int i = 0; i < b.length; i++) {
                strArr[i] = DecimalConversion.decimalToOther(2, String.valueOf((byte)b[i] & 0xff));//& 0xff(1111 1111)实际上是将负数转为显示成不考虑符号位的负数的补码形式,正数不受影响
            }
            String encodeByte = encode0(strArr);
            return encodeByte;

        }

        public static byte[] encodeByte(String str){
            return encode(str).getBytes();
        }

        public static byte[] encodeByte(byte[] b){
            return encode(b).getBytes();
        }

        /**
         * Base 64 编码规则:
         * --:错误1.将byte数组中的值转为二进制,将所有二进制数的长度相加,看长度是否为8的倍数,如果不足,则在每个数组元素的二进制前面补0,直到长度满足8的倍数
         * 1. 将byte数组的值转为二进制并放入数组,将数组中的每个元素的长度变成8个bit位,(即如果长度是6,则在值前面补两个0),最后按顺序拼接成一个完整的字符串
         * 2.将字符串以6位分组,不足的,要在末尾补0,达到6的倍数(记下补0的次数)
         * 3.将 每个分组的字符串拿出来,转为十进制以这个为下标,去查表,取得所需的对应编码值
         * 4.将值(可以拼接成字符串,也可以byte数组的形式返回)如果第二部在末尾补0了,每补"00",就在值后拼接一个'='
         * @param binaryStrArr
         * @return
         */
        private static String encode0(String [] binaryStrArr){//二进制字符串   //int类型最大是32位
            int len = 0;//标记第二部,以6分组加 0 的次数
            String binaryStr = "";
            int strArrLength = StringUtil.getStringArrLength(binaryStrArr);
//			System.out.println("字符串转数组:"+ Arrays.toString(binaryStrArr));
            //将二进制的编码,以8位为一组,//不足的前面补0
            for (int i = 0; i < binaryStrArr.length; i++) {
                binaryStrArr[i] = StringUtil.addZeroFirst(binaryStrArr[i], 8);
            }
            binaryStr = StringUtil.getLongStrToStrArr(binaryStrArr);
            //分成6 位一组的字符串,不足末尾补0

            int mod6 = binaryStr.length()%6;
            if(mod6 != 0){
                len = 6 - mod6;
                binaryStr = binaryStr.concat(StringUtil.addZero(len));
            }
            //按6个长度分组
            String[] splitForNum = StringUtil.splitForLength(binaryStr, 6);
            StringBuffer sbf = new StringBuffer();
            int lastLen = len/2;
            //byte [] result = new byte[splitForNum.length + lastLen];
            for (int i = 0; i < splitForNum.length; i++) {//将截取的二进制字符串 转十进制
                int index = Integer.valueOf( DecimalConversion.otherToDecimal(2, splitForNum[i]));
                sbf.append((char)STANDARD_ENCODE_TABLE[index]);
            }
            //每加 00;则输出结果 加 =
            for (int i = splitForNum.length; i < splitForNum.length + lastLen; i++) {
                sbf.append('=');
            }

//			System.out.println("字符串转数组,按8补0后:"+ Arrays.toString(binaryStrArr));
//			System.out.println("二进制字符串按6分组后:"+ Arrays.toString(splitForNum));
            return sbf.toString();

        }

    }


    public static class Decoder{

        /**
         *4. 将前面得到的字符串进行按8分组,并去除前面多余的0(不去也行)
         *5. 将每个二进制元素转为十进制,并放入byte数组,(可以直接返回数组,也可以拼接成字符串返回)
         *
         * @param str
         */
        public static byte[] decodeByte(String str){
            String longStr = decode0(str);
            //按8分组
            String[] binaryEightStr = StringUtil.splitForLength(longStr, 8);
//            System.out.println("字符串转数组,按8补0后:"+Arrays.toString(binaryEightStr));
            byte [] b = new byte[binaryEightStr.length];
            for (int i = 0; i < binaryEightStr.length; i++) {
                binaryEightStr[i] = StringUtil.removeStartingNumStr(binaryEightStr[i], 0);//去除前面多余的0
                //转为十进制
                int decimalValue = Integer.valueOf(DecimalConversion.otherToDecimal(2, binaryEightStr[i]));
                b[i] = (byte) decimalValue;
            }
//            System.out.println("字符串转数组:"+Arrays.toString(binaryEightStr));
//            System.out.println("待解码的文字的字节数组:"+Arrays.toString(b));
            return b;
            //return new Strng(b);//TODO //不能随便new String,必须先getBytes(),然后在new String(),不然得到的字节数组前后会不一致,不然会出现异想不到的后果
        }



        public static byte[] decodeByte(byte[] b){
            return decodeByte(new String(b));
        }



        /**
         * 1. 去除字符串中的"=",并记录下"="出现的次数
         * 2. 去除后的字符串,分成字符串数组,每个值去查 base64标准表,得到对应的下标索引,放入到一个数组中
         * 3. 将数组拼接成一个完整的二进制字符串,并且去除末尾多余的0(通过"="出现的次数*2)
         * @param bytes
         * @return
         */
        private static String decode0(String str){
            int indexOf = str.indexOf("=");
            int equalsCount = 0;
            if(indexOf != -1){
                str = str.substring(0, indexOf);
                equalsCount = str.length() - indexOf;
            }

            byte[] bytes = str.getBytes();

            String[] strArr = new String[bytes.length];
            String[] binaryStrArr = new String[bytes.length];
            for (int i = 0; i < bytes.length; i++) {
                //返回 -1 的原因,二分查找,要求查找的数组是有序的....
                //strArr[i]= String.valueOf(Arrays.binarySearch(STANDARD_ENCODE_TABLE, bytes[i]));//查找对应编码的下标
                strArr[i] = String.valueOf(ArrayUtil.searchIndex(STANDARD_ENCODE_TABLE, bytes[i]));//
                String strIndex = DecimalConversion.decimalToOther(2, strArr[i]);
                binaryStrArr[i] = StringUtil.addZeroFirst(strIndex, 6);
            }
//            System.out.println("index列:"+Arrays.toString(strArr));
//            System.out.println("二进制字符串按6分组:"+Arrays.toString(binaryStrArr));
            String longStr = StringUtil.getLongStrToStrArr(binaryStrArr);
            longStr.substring(0,longStr.length() - (equalsCount << 1));//去除末尾添加的0//equals *  2
            return longStr;
        }

    }






}

package com.yzw.advance.util;



import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;


//	优化了处理类型,将Integer 类型转换为了BigInteger 类型
//TODO 暂时不支持小数类型的处理
/**
 * 暂时只支持1-16进制转换
 * 查库发现,Integer 有其他进制转换十进制的方法
 * @author zcm
 *
 */
public class DecimalConversion {

    public static final int radix2 = 2;//二进制		计算机内部表示数的字节长度是固定的，比如8位，16位，32位。所以在高位补齐，java中字节码是8位的
    public static final int radix8 = 8;//八进制		八进制用0开头,比如:012就是十进制的10
    public static final int radix10 = 10;//十进制
    public static final int radix16 = 16;//十六进制	十六进制用0x开头,


    //替换16进制的后六位字母
    public static final HashMap<Integer,String> hexMap = new HashMap<>();

    static{
        hexMap.put(10, "A");
        hexMap.put(11, "B");
        hexMap.put(12, "C");
        hexMap.put(13, "D");
        hexMap.put(14, "E");
        hexMap.put(15, "F");
    }

    public static void main(String[] args) {
        System.out.println(otherToDecimal(16, "fffffffffffffff"));//fffffffffffff0
    }


    /**
     * 十进制转其他进制
     * @param radix		//进制类型
     * @param number	//要转换字符串的string 形式
     * TODO 暂不支持小数形式
     * @return
     */
    //方法:辗转相除,取余数的反值
    public static String decimalToOther(int radix,String numStr){
        if(radix == 0 || radix >16){
            NoSuchRadixOrNotSupportRadixException e = new NoSuchRadixOrNotSupportRadixException("没有这样的进制或不支持这个进制");
            e.printStackTrace();
            return null;
        }
        int regex = judgeInteger(radix,numStr.trim());//返回为2需要转换
        if(regex == 2 ){
            String result = reverse(division(numStr.trim(), radix));
            if(radix == 8){
                result = "0".concat(result);
            }else if(radix == 16){
                result = "0x".concat(result);
            }
            return result;
        }else{
            return numStr;//不处理, 1或0
        }
    }

    /**
     * 其他进制转十进制
     * @param radix		进制类型:
     * @param numStr	需要转换的数
     * @return
     *
     * 方法:阶乘法
     */
    public static String otherToDecimal(int radix,String numStr){
        if(radix == 0 || radix >16){
            NoSuchRadixOrNotSupportRadixException e = new NoSuchRadixOrNotSupportRadixException("没有这样的进制或不支持这个进制");
            e.printStackTrace();
            return null;
        }
        numStr = numStr.trim();
        if(radix == 16 ){
            numStr = numStr.replaceFirst("0x", "").toUpperCase();//去除  16进制字符的标记0x,并将字母转为大写
        }else if(radix == 8){
            if(numStr.indexOf(0) == 0){//去除 8进制字符标记 : 第一位是0
                numStr = numStr.substring(1);
            }
        }
        return factorial(numStr, radix);
    }


    /**
     * 反转
     * @param result
     * @return
     */
    public static String reverse(String result) {
        StringBuffer sbf  = new StringBuffer();
        for (int i = 0,j = result.length(); i < j; i++) {
            sbf.append(result.substring(j-i-1,j-i));
        }
        return sbf.toString();
    }

    /**
     * 辗转除radix法
     * @param numStr
     * @param sbf
     * @return
     */
    public static String division(String numStr,int radix) {
        BigInteger bigStr = new BigInteger(numStr);
        BigInteger bigDix = new BigInteger(String.valueOf(radix));
        StringBuffer sbf = new StringBuffer();
        while( bigStr.compareTo(bigDix) >= 0) {
            BigInteger[] result = bigStr.divideAndRemainder(bigDix);//商是0,余数是1
            BigInteger temp = result[0];
            int temp2 = Integer.valueOf(result[1].toString());//余数不会大于除数,所以可以转为int类型
            //divideAndRemainder//
            if(radix == 16 && hexMap.containsKey(temp2)){//如果16进制则转为字母
                sbf.append(hexMap.get(temp2));
            }else{
                sbf.append(temp2);
            }
            bigStr = temp;
        }
        sbf.append(bigStr.toString());
        return sbf.toString();
    }

    /**
     * 按位取值 乘 进制的次方,相加
     * @param number
     * @param radix
     * @return
     */
    public static String factorial(String numStr,int radix){
        BigInteger sum = new BigInteger("0");//int sum = 0;
        BigInteger bitValue = new BigInteger("0");//int bitValue = 0;
        for (int i = 0,numLen = numStr.length(); i < numLen; i++) {//从最高位开始取,取值*进制^位数-1
            String bitString = numStr.substring(i,i+1);
            if(radix == 16 && hexMap.containsValue(bitString)){
                for (Entry<Integer, String> entry : hexMap.entrySet()) {
                    if(entry.getValue().equals(bitString)){
                        bitValue = new BigInteger(String.valueOf(entry.getKey()));
                    }
                }
            }else	bitValue = new BigInteger(bitString);//bitValue = Integer.valueOf(bitString);
            //int b = radix;
            BigInteger b = new BigInteger(String.valueOf(radix));
            BigInteger bRadix = new BigInteger(String.valueOf(radix));
            for (int k = 1; k < numLen-i-1 ; k++) {//按位置,乘 进制的(位置-1)次方//此处结果有可能溢出,需要换成大数据类型
                //b = b*radix;
                b = b.multiply(bRadix);
            }
            if(i == numLen - 1) b = new BigInteger("1");//最后一位的次方结果总是1
            //sum += bitValue*b;//位置*for循环次方的结果,然后相加这些结果
            sum =sum.add(bitValue.multiply(b));
        }
        return sum.toString();

    }
    /**
     * 判断是否需要进行转换
     *
     * @param numStr	待转换的数字
     * @param radix			进制类型
     * @return				0:代表为0;
     * 						1:代表小于要转换的进制位,无需转换
     * 						2:需要转换
     */
    public static int judgeInteger(int radix,String numStr){
        if(String.valueOf(0).equals(numStr)){//判断是否和0相等
            return 0;
        }
        if(numStr.contains(".")){//判断数字有无小数点
            return 1;//暂时无处理小数功能
        }else{
            BigDecimal bg = new BigDecimal(numStr);
            int i = bg.compareTo(new BigDecimal(radix));
            if( i == -1 ){
                return 1;//不需转换
            }else{
                return 2;
            }
        }

    }

}


class NoSuchRadixOrNotSupportRadixException extends Exception{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public NoSuchRadixOrNotSupportRadixException() {
        super();
    }

    public NoSuchRadixOrNotSupportRadixException(String message, Throwable cause, boolean enableSuppression,
                                                 boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public NoSuchRadixOrNotSupportRadixException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchRadixOrNotSupportRadixException(String message) {
        super(message);
    }

    public NoSuchRadixOrNotSupportRadixException(Throwable cause) {
        super(cause);
    }



}
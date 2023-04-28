package com.yzw.advance.util;

public class StringUtil {




    /**
     * 返回string 的单个字符的String 数组形式,而不是字符数组形式
     * @param str
     * @return
     */
    public static String[] getStrings(String str){
        int j = str.length();
        String [] strArr = new String[j];
        for (int i = 0; i < j; i++) {
            strArr[i] = str.substring(i, i + 1);
        }
        return strArr;
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
     * 获得反转的单个字符的字符串数组
     * @param str
     * @return
     */
    public static String[] getStringsReverse(String str){
        return getStrings(reverse(str));
    }

    /**
     * 返回传入的数量的"0"字符串
     * @param size
     * @return
     */
    public static String addZero(int size){
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < size; i++) {
            sbf.append("0");
        }
        return sbf.toString();
    }

    /**
     * 根据位数自动添加缺少的0,将0补在开头
     * 补全二进制的0
     * @param size
     * @return	返回补全后的字符串
     */
    public static String addZeroFirst(String str,int num){
        int mod = str.length() % num;
        int len = 0;
        if(mod == 0) return str;
        else if(mod != 0) len = num - mod;
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < len; i++) {
            sbf.append("0");
        }
        return sbf.toString().concat(str);
    }

    /**
     * 根据位数自动添加缺少的0,将0补在结尾
     * 补全二进制的0
     * @param size
     * @return	返回补全后的字符串
     */
    public static String addZeroEnd(String str,int num){
        return reverse(addZeroFirst(reverse(str), num));
    }

    /**
     * 根据传入长度截取字符串,返回字符串数组
     * 注意,位数不够的请自行补0
     * @param str
     * @param num
     * @return
     */
    public static String[] splitForLength(String str,int num){
		/*int numValue = str.length() % num;
		if(numValue != 0){
			str = str.concat(addzero(num - numValue));
		}*/
        String []  strArr = new String[str.length() / num];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = str.substring((0 + i)*num,(1 + i)*num);// 0,6 ; 6,12
        }
        return strArr;

    }

    /**
     * 获得string数组的数组各个元素的长度之和
     * @param strArr
     * @return
     */
    public static int getStringArrLength(String[] strArr){
        int count = 0;
        for (String str : strArr) {
            count += str.length();
        }
        return count;
    }

    /**
     * 获得字符串数组的各个下标拼接的长字符串
     * @param strArr
     * @return
     */
    public static String getLongStrToStrArr(String[] strArr){
        return getLongStrToIndex(strArr,0,strArr.length);
    }

    public static String getLongStrToIndex(String[] strArr,int endIndex){//index 索引,下标
        return getLongStrToIndex(strArr, 0,endIndex);
    }

    public static String getLongStrToIndex(String[] strArr,int startIndex,int endIndex){
        String longStr = "";
        for (int i = startIndex; i < endIndex; i++) {
            longStr += strArr[i];
        }
        return longStr;
    }

    public static boolean checkEmpty(String str){
        if(null != str && !str.equals("")){
            return false;
        }
        return true;
    }

    /**
     * 去除以特定数字开头的子字符串,直到后面跟着的是其他子字符串,
     * 返回一个去除后的新字符串
     *
     * @param str
     * @param num	单个数字,范围:0-9
     */
    public static String removeStartingNumStr (String str,int num){
        return removeStartingStr(str, String.valueOf(num));
    }

    /**
     * 去除以特定数字结尾的子字符串,直到前面跟着的是其他字符串
     * 返回一个去除后的新字符串
     * @param str
     * @param num
     * @return
     */
    public static String removeEndingNumStr(String str,int num){
        String removeStartingNumStr = removeStartingStr(reverse(str),String.valueOf(num));
        return reverse(removeStartingNumStr);
    }

    /**
     * 去除以特定字符(注意:只能是单个字符)开头的子字符串,直到后面跟着的是其他子字符串,
     * 返回一个去除后的新字符串
     * @param str
     * @param childrenStr
     * @return
     */
    public static String removeStartingStr (String str,String childrenStr){
        String[] strings = getStrings(str);
        Integer index = null;
        for (int i = 0; i < strings.length; i++) {
            if(strings[i].equals(childrenStr)){
                index = i;
            }else{
                break;
            }
        }
        if(null != index){
            str = str.substring(index+1);
        }
        return str;
    }

    /**
     * 去除以特定字符(注意:只能是单个字符)结尾的子字符串,直到前面跟着的是其他子字符串,
     * 返回一个去除后的新字符串
     * @param str
     * @param childrenStr
     * @return
     */
    public static String removeEndingStr(String str,String childrenStr){
        String removeStartingNumStr = removeStartingStr(reverse(str),childrenStr);
        return reverse(removeStartingNumStr);
    }

}

package com.yzw.advance.util;

public class ArrayUtil {


    /**
     * 无序数组的查找
     * 如果是有序的,Arrays.binarySearch()二分查找
     * @param b
     * @param key
     * @return	返回该元素在数组中的下标,如果有数组中要查询的值有多个,则返回第一次出现的索引
     * 			-1:表示数组中不存在该值
     */
    public static int searchIndex(byte[] b,byte key){
        byte[] b2 = {key};
        String str = new String(b);
        String keyStr = new String(b2);
        if(str.contains(keyStr)){
            return str.indexOf(keyStr);
        }else{
            return -1;
        }
    }

    /**
     * 无序数组的查找
     * 如果是有序的,Arrays.binarySearch()二分查找
     * @param b
     * @param key
     * @return	返回该元素在数组中的下标,如果有数组中要查询的值有多个,则返回第一次出现的索引
     * 			-1:表示数组中不存在该值
     */
    public static int searchIndex2(byte[] b,byte key){
        for (int i = 0; i < b.length; i++) {
            if(b[i] == key){
                return i;
            }
        }
        return -1;
    }

}

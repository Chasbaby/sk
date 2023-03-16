package com.ruoyi.common.utils;

import com.github.binarywang.java.emoji.EmojiConverter;

/**
 * @author chas
 * @introduction 表情包转换
 * @data 2023-3
 */
public class EmojiUtils {
    private static EmojiConverter emojiConverter = EmojiConverter.getInstance();

    // 将表情编码转换成可读的表情字符
    public static String emojiConverterUnicodeStr(String emojiStr){
        String regex = "^[a-z0-9A-Z\u4e00-\u9fa5]+$";
        if(emojiStr.matches(regex)){
            return emojiStr;
        }
        String result = "";
        try {
            result = emojiConverter.toUnicode(emojiStr);
        } catch (Exception e) {
            return emojiStr;
        }
        return result;
    }

    // 将带有表情的字符转换为表情编码（存入数据库）
    public static String emojiConverterToAlias(String str){
        String result = emojiConverter.toAlias(str);
        return result;
    }


}

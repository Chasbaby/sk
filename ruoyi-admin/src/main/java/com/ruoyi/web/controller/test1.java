package com.ruoyi.web.controller;

import com.ruoyi.common.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * @author chas
 * @introduction
 * @data
 */
public class test1 {
    public static void main(String[] args) {
        working();
    }

    public static Map<String,String> storeMap(){
        Map<String,String> map = new HashMap<>();
        map.put("begin","(beginsym,begin)");
        map.put("call","(callsym,call)");
        map.put("const","(constsym,const)");
        map.put("do","(dosym,do)");
        map.put("end","(endsym,end)");
        map.put("if","(ifsym,id)");
        map.put("odd","(oddsym,odd)");
        map.put("procedure","(proceduresym,procedure)");
        map.put("read","(readsym,read)");
        map.put("then","(thensym,then)");
        map.put("while","(whilesym,while)");
        map.put("var","(varsym,var)");
        map.put("write","(writesym,write)");
        return map;
    }
    public static void Letter(String str){
        if (StringUtils.isEmpty(str)){
            System.out.println("基本字不能为空");
        }
        Map<String, String> LetterMap = storeMap();
        Set<String> set = LetterMap.keySet();
        set.forEach(item->{
            if (item.equals(str)) System.out.println(LetterMap.get(item));
        });
    }

    public static void working(){
        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.next();
        StringBuilder builder = new StringBuilder();
        while (!str1.equals("xxx")){
            builder.append(" ");
            builder.append(str1);
            str1 = scanner.next();
        }
        String str = builder.toString();
        System.out.println(str);
        int length = str.length();
        for (int i = 0;i<length;i++){
            if (str.charAt(i)==' ' || str.charAt(i) == '\n')
                continue;
            else if (Character.isDigit(str.charAt(i))){
                StringBuilder digit = new StringBuilder();
                while(Character.isDigit(str.charAt(i))){
                    digit.append(str.charAt(i));
                    i++;
                }
                i--;
                System.out.println("(number,"+digit+")");
            }
            else if (Character.isAlphabetic(str.charAt(i))){
                StringBuilder lett = new StringBuilder();
                while (Character.isDigit(str.charAt(i))||Character.isAlphabetic(str.charAt(i))){
                    lett.append(str.charAt(i));
                    i++;
                }
                i--;
                Letter(lett.toString());
            }
            else {
                switch (str.charAt(i)){
                    case '+':
                        System.out.println("(plus,+)");
                        break;
                    case '-':
                        System.out.println("(minus,-)");
                        break;
                    case '*':
                        System.out.println("(times,*)");
                        break;
                    case '/':
                        System.out.println("(slash,/)");
                        break;
                    case '=':
                        System.out.println("(eql,=)");
                        break;
                    case '<':
                        i++;
                        if (">".equals(str.charAt(i))){
                            System.out.println("(neq,<>)");
                        }
                        else if ("=".equals(str.charAt(i))){
                            System.out.println("(leq,<=)");
                        }else {
                            i--;
                            System.out.println("(lss,<)");
                        }
                        break;
                    case'>':
                        i++;
                        if ("=".equals(str.charAt(i))){
                            System.out.println("(geq,>=)");
                        }else {
                            i--;
                            System.out.println("(gtr,>)");
                        }
                        break;
                    case ':':
                        i++;
                        if ("=".equals(str.charAt(i))){
                            System.out.println("(becomes,:=)");
                        }else {
                            System.out.println("错了哦");
                        }
                        break;
                    case '(':
                        System.out.println("(lparen,()");
                        break;
                    case ')':
                        System.out.println("(rparen,))");
                        break;
                    case ',':
                        System.out.println("(comma,,)");
                        break;
                    case';':
                        System.out.println("(semicolon,;)");
                        break;
                    case '.':
                        System.out.println("(period,.)");
                        break;
                    default:
                       System.out.println("出错喽");
                        break;
                }
            }
        }

    }

}

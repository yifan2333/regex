package com.yifan.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * 匹配字符
 *
 * @author: wuyifan
 * @date: 2019年09月24日 下午2:35
 * @version 1.0
 */ 
public class RegexTest {

    @Test
    public void test1() {
        Pattern pattern = Pattern.compile("hello");
        Matcher matcher = pattern.matcher("hgghello-hello");
        printMather(matcher);
        /*
         * hello
         * hello
         */
    }

    @Test
    public void test2() {
        // 横向匹配 表示b出现2-5次
        Pattern pattern = Pattern.compile("ab{2,5}c");
        Matcher matcher = pattern.matcher("abc abbc abbbc abbbbc abbbbbc abbbbbbc");
        // find是从一个字符串中找出匹配的所有字符
        System.out.println(matcher.find());  // true
        // matches是匹配整个字符串是否满足正则
        System.out.println(matcher.matches());  // false

        Matcher matcher2 = pattern.matcher("abbbc");
        System.out.println(matcher2.matches()); // true
        printMather(matcher);
        /*
         * abbc
         * abbbc
         * abbbbc
         * abbbbbc
         */
    }

    @Test
    public void test3() {
        // 纵向匹配，表示a,b中间可以出现 1、 2、 3
        Pattern pattern = Pattern.compile("a[123]b");
        Matcher matcher = pattern.matcher("a0b a1b a2b a3b a4b");
        printMather(matcher);
        /*
         * a1b
         * a2b
         * a3b
         */
    }

    /**
     * 贪婪匹配，尽可能多的匹配
     * 12312
     * 34123
     * 45123
     * 456
     * @author wuyifan
     * @date 2019年9月24日 下午3:09
     */
    @Test
    public void test4() {
        Pattern pattern = Pattern.compile("\\d{2,5}");
        Matcher matcher = pattern.matcher("123123412345123456");
        printMather(matcher);
        /*
         * 12312
         * 34123
         * 45123
         * 456
         */
    }

    /**
     * 惰性匹配,尽可能少的匹配
     * 默认是贪婪匹配，在贪婪匹配后边加？就是惰性匹配
     *
     * @author wuyifan
     * @date 2019年9月24日 下午3:11
     */
    @Test
    public void test5() {
        Pattern pattern = Pattern.compile("\\d{2,5}?");
        Matcher matcher = pattern.matcher("123123");
        printMather(matcher);
        /*
         * 12
         * 31
         * 23
         */
    }

    /**
     * 而多选分支可以支持多个子模式任选其一
     * 用|（管道符）分隔，表示其中任何之一
     * 分支结构也是惰性的，即当前面的匹配上了，后面的就不再尝试了。
     *
     * @author wuyifan
     * @date 2019年9月24日 下午3:14
     */
    @Test
    public void test6() {
        Pattern pattern = Pattern.compile("good|nice");
        Matcher matcher = pattern.matcher("good idea, nice try.");
        printMather(matcher);
        /*
         * good
         * nice
         */
    }

    @Test
    public void test7() {
        // 匹配颜色值 3位或者6位十六进制数值
        Pattern pattern = Pattern.compile("#([0-9a-fA-F]{6}|[0-9a-fA-F]{3})");
        Matcher matcher = pattern.matcher("#ffbbad #Fc01DF #FFF #ffE");
        printMather(matcher);
    }

    @Test
    public void test8() {
        // 匹配时间
        Pattern pattern = Pattern.compile("([01]\\d|2[0-3]):[0-5]\\d");
        Matcher matcher = pattern.matcher("23:59");
        printMather(matcher);
        printMather(pattern.matcher("02:07"));
    }

    @Test
    public void test9() {
        // 匹配日期
        Pattern pattern = Pattern.compile("\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])");
        Matcher matcher = pattern.matcher("2017-06-10");
        printMather(matcher);
        printMather(pattern.matcher("02:07"));
    }

    @Test
    public void test10() {
        Pattern pattern = Pattern.compile("[a-zA-Z]:\\\\([^\\\\:*<>|\"?\\r\\n/]+\\\\)*([^\\\\:*<>|\"?\\r\\n/]+)?");
        Matcher matcher = pattern.matcher("F:\\study\\javascript\\regex\\regular expression.pdf");
        printMather(matcher);
        printMather(pattern.matcher("F:\\study\\javascript\\regex"));
    }

    @Test
    public void test11() {
        String str = "<a id=\"test\"  class=\"main\"></a>";
        // 贪婪匹配 id="test"  class="main"
        Pattern pattern = Pattern.compile("id=\".*\"");
        Matcher matcher = pattern.matcher(str);
        printMather(matcher);

        // 惰性匹配 id="test"
        Pattern pattern2 = Pattern.compile("id=\".*?\"");
        printMather(pattern2.matcher(str));

        // id="test"
        Pattern pattern3 = Pattern.compile("id=\"[^\"]*\"");
        printMather(pattern3.matcher(str));
    }

    private static void printMather(Matcher matcher) {
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}

package com.yifan.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * 匹配位置
 *
 * @author: wuyifan
 * @date: 2019年09月24日 下午3:54
 * @version 1.0
 */ 
public class RegexTest2 {

    /**
     * ^（脱字符）匹配开头，在多行匹配中匹配行开头。
     * $（美元符号）匹配结尾，在多行匹配中匹配行结尾。
     *
     * @author wuyifan
     * @date 2019年9月24日 下午3:57
     */
    @Test
    public void test() {
        Pattern pattern = Pattern.compile("^|$");
        String string = "hello";
        Matcher matcher = pattern.matcher(string);
        System.out.println(matcher.replaceAll("#"));  // #hello#
    }

    /**
     * 按行匹配 (?m)
     *
     * @author wuyifan
     * @date 2019年9月24日 下午4:11
     */
    @Test
    public void test1() {
        Pattern pattern = Pattern.compile("(?m)^|$");
        String string = "I\nlove\njavascript";
        Matcher matcher = pattern.matcher(string);
        String result = matcher.replaceAll("#");
        System.out.println(result);

        System.out.println(string.replaceAll("(?m)^|$", "#"));
    }

    /**
     * 单词边界 \b
     *
     * @author wuyifan
     * @date 2019年9月24日 下午4:15
     */
    @Test
    public void test2() {
        Pattern pattern = Pattern.compile("\\b");
        String string = "[JS] Lesson_01.mp4";
        Matcher matcher = pattern.matcher(string);
        System.out.println(matcher.replaceAll("#"));  // [#JS#] #Lesson_01#.#mp4#
        System.out.println(string.replaceAll("\\b", "#"));  // [#JS#] #Lesson_01#.#mp4#

        // \b 是特殊字符
        System.out.println("\b");
    }

    /**
     * 单词边界 \B
     * 除了 \b 之外的位置
     *
     * @author wuyifan
     * @date 2019年9月24日 下午4:22
     */
    @Test
    public void test3() {
        Pattern pattern = Pattern.compile("\\B");
        String string = "[JS] Lesson_01.mp4";
        Matcher matcher = pattern.matcher(string);
        System.out.println(matcher.replaceAll("#"));  // #[J#S]# L#e#s#s#o#n#_#0#1.m#p#4
        System.out.println(string.replaceAll("\\B", "#"));  // #[J#S]# L#e#s#s#o#n#_#0#1.m#p#4
    }

    /**
     * (?=p) p前边的位置
     * positive lookahead
     * 正向先行断言
     *
     * @author wuyifan
     * @date 2019年9月24日 下午4:36
     */
    @Test
    public void test4() {
        Pattern pattern = Pattern.compile("(?=l)");
        String string = "hello";
        Matcher matcher = pattern.matcher(string);
        System.out.println(matcher.replaceAll("#"));  // he#l#lo
        System.out.println(string.replaceAll("(?=l)", "#"));  // he#l#lo
    }

    /**
     * (?!p) 不是p前边的位置
     * negative lookahead
     * 负向先行断言
     *
     * @author wuyifan
     * @date 2019年9月24日 下午4:36
     */
    @Test
    public void test5() {
        Pattern pattern = Pattern.compile("(?!l)");
        String string = "hello";
        Matcher matcher = pattern.matcher(string);
        System.out.println(matcher.replaceAll("#"));  // #h#ell#o#
        System.out.println(string.replaceAll("(?!l)", "#"));  // #h#ell#o#
    }

    /**
     * (?<=l) l后边的位置
     * positive lookbehind
     *
     *
     * @author wuyifan
     * @date 2019年9月24日 下午4:40
     */
    @Test
    public void test6() {
        Pattern pattern = Pattern.compile("(?<=l)");
        String string = "hello";
        Matcher matcher = pattern.matcher(string);
        System.out.println(matcher.replaceAll("#"));  // hel#l#o
        System.out.println(string.replaceAll("(?<=l)", "#"));  // hel#l#o
    }

    /**
     * negative lookbehind
     * (?<!l) 不是l后边的位置
     *
     * @author wuyifan
     * @date 2019年9月24日 下午4:41
     */
    @Test
    public void test7() {
        Pattern pattern = Pattern.compile("(?<!l)");
        String string = "hello";
        Matcher matcher = pattern.matcher(string);
        System.out.println(matcher.replaceAll("#"));  // hel#l#o
        System.out.println(string.replaceAll("(?<!l)", "#"));  // hel#l#o
    }

    @Test
    public void test8() {
        String string = "123456789 123456789";
        // 在字符边界往前三个数字的非字符边界位置插入 ,
        System.out.println(string.replaceAll("\\B", ","));  // 1,2,3,4,5,6,7,8,9 1,2,3,4,5,6,7,8,9
        System.out.println(string.replaceAll("\\B(?=(\\d{3})+\\b)", ","));  // 123,456,789 123,456,789
    }

    @Test
    public void test9() {
        String string = "asd123456asd";
        // 匹配 长度6-12位，由数字、小写字符和大写字母 的密码
        System.out.println(Pattern.compile("^[0-9a-zA-Z]{6,12}$").matcher(string).matches());
        // 数字前边任意长度的位置  #a#s#d#1#2#3#4#5#6asd
        System.out.println(string.replaceAll("(?=.*\\d)", "#"));

        // (?=.*[0-9]).* 这个正则表达式可以分为两个部分 (?=.*[0-9]) 和 .*
        // (?=.*[0-9]) 是说 任意字符（.）出现任意次数后有个数字（[0-9]），即数字前边的位置
        // .* 表示任意字符，出现任意次数，即能匹配所有字符串
        // 组合在一起 就是一个任意长度的字符，但存在数字
        System.out.println(Pattern.compile("(?=.*[0-9]).*").matcher("asdf1sad").matches());

        // 必须包含数字
        System.out.println(Pattern.compile("(?=.*[0-9])^[0-9A-Za-z]{6,12}$").matcher("asdf1sad").matches());

        // 同时包含数字和小写字母
        // 同时包含数字和大写字母
        // 同时包含小写字母和大写字母
        // 同时包含数字、小写字母和大写字母
        Pattern pattern = Pattern.compile("((?=.*[0-9])(?=.*[a-z])|(?=.*[0-9])(?=.*[A-Z])|(?=.*[a-z])(?=.*[A-Z]))^[0-9A-Za-z]{6,12}$");

        System.out.println(pattern.matcher("1234567").matches());  //false
        System.out.println(pattern.matcher("abcdef").matches());  // false
        System.out.println(pattern.matcher("ABCDEFGH").matches());  // false
        System.out.println(pattern.matcher("ab23C").matches());  // false
        System.out.println(pattern.matcher("ABCDEF234").matches());  // true
        System.out.println(pattern.matcher("abcdEF234").matches());  // true
    }

    private static void printMather(Matcher matcher) {
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

}

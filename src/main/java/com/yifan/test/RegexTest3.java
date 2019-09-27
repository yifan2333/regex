package com.yifan.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * 括号的作用
 *   分组和分支结构
 *   捕获分组
 *   反向引用
 *   非捕获分组
 *
 * @author: wuyifan
 * @date: 2019年09月26日 下午2:28
 * @version 1.0
 */ 
public class RegexTest3 {

    /**
     * 分组
     *
     *
     * @author wuyifan
     * @date 2019年9月26日 下午2:31
     */
    @Test
    public void test() {
        Pattern pattern = Pattern.compile("(ab)+");
        Matcher matcher = pattern.matcher("ababa abbb abababa");

        printMather(matcher);
    }

    /**
     * 分支结构 (p1|p2)
     *
     * @author wuyifan
     * @date 2019年9月26日 下午2:34
     */
    @Test
    public void test1() {
        Pattern pattern = Pattern.compile("(I love (JavaScript|Regular Expression))+");
        System.out.println(pattern.matcher("I love JavaScript").matches());
        System.out.println(pattern.matcher("I love Regular Expression").matches());
    }

    /**
     * 引用分组
     *   可以进行数据提取
     *
     * @author wuyifan
     * @date 2019年9月26日 下午2:42
     */
    @Test
    public void test2() {
        Pattern pattern1 = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
        Pattern pattern2 = Pattern.compile("(\\d{4})-(\\d{2})-(\\d{2})");
        String string = "2017-06-12";
        Matcher matcher1 = pattern1.matcher(string);
        if (matcher1.find()) {
            System.out.println(matcher1.group());
        }
        Matcher matcher2 = pattern2.matcher(string);
        if (matcher2.matches()) {
            System.out.println(matcher2.group(1));
            System.out.println(matcher2.group(2));
            System.out.println(matcher2.group(3));
        }
    }

    /**
     * 反向引用
     *    \1 表示第一个分组
     *    \2 表示第二个分组
     *    \3 表示第三个分组
     * @author wuyifan
     * @date 2019年9月26日 下午3:05
     */
    @Test
    public void test3() {
        // 一个正则要匹配 2016-06-12 2016/06/12 2016.06.12
        Pattern pattern1 = Pattern.compile("\\d{4}(-|/|.)\\d{2}(-|/|.)\\d{2}");
        System.out.println(pattern1.matcher("2017-06-12").matches());
        // 但它会匹配 2017-06.12 这样的
        System.out.println(pattern1.matcher("2017-06.12").matches());
        // 要求分隔符前后一致，就可以使用反向引用
        Pattern pattern2 = Pattern.compile("\\d{4}(-|/|.)\\d{2}\\1\\d{2}");
        System.out.println(pattern2.matcher("2017-06-12").matches());
        System.out.println(pattern2.matcher("2017-06.12").matches());
    }

    /**
     * 反向引用括号嵌套
     *     按照左括号从左至右的顺序
     *
     * @author wuyifan
     * @date 2019年9月27日 下午3:14
     */
    @Test
    public void test4() {
        Pattern pattern = Pattern.compile("((\\d)(\\d(\\d)))\\1\\2\\3\\4");
        Matcher matcher = pattern.matcher("1231231233");
        // 第一个字符是数字 1
        // 第二个字符是数字 2
        // 第三个字符是数字 3
        // 接下来是 \1，是第一个分组的内容，第一个括号对应的分组是 123
        // 接下来是 \2，找到第2个开括号，对应的分组，匹配的内容是1
        // 接下来是 \3，找到第三个括号，对应的分组匹配的是23
        // 接下来是 \4，第四个括号，对应的分组匹配的是3
        // 连起来是 1231231233，刚好能匹配到
        if (matcher.matches()) {
            System.out.println(matcher.group(1));  // 123
            System.out.println(matcher.group(2));  // 1
            System.out.println(matcher.group(3));  // 23
            System.out.println(matcher.group(4));  // 3
        }
    }

    /**
     * 非捕获分组
     *     反向引用不会引用到非捕获分组的数据
     *
     * @author wuyifan
     * @date 2019年9月27日 下午3:32
     */
    @Test
    public void test5() {
        Pattern pattern = Pattern.compile("(?:ab)+");
        Matcher matcher = pattern.matcher("ababa abbb ababab");

        printMather(matcher);
    }

    /**
     * trim
     *
     * @author wuyifan
     * @date 2019年9月26日 下午3:36
     */
    @Test
    public void test6() {
        // 去除掉开头和结尾的 \s
        System.out.println("  dfsd  ".replaceAll("^\\s+|\\s+$", ""));
        // 匹配整个字符串，取出首位空格之外的字符串
        System.out.println("  dfsd  ".replaceAll("^\\s*(.*?)\\s*$", "$1"));
        // 第一个效率更高
    }

    /**
     * 将第一个单词大写
     *
     * @author wuyifan
     * @date 2019年9月26日 下午3:43
     */
    @Test
    public void test7() {
        // 去除掉开头和结尾的 \s
        System.out.println("  apple  ".replaceAll("^\\s+|\\s+$", ""));
        // 匹配整个字符串，取出首位空格之外的字符串
        System.out.println("  apple  ".replaceAll("^\\s*(.*?)\\s*$", "$1"));
        // 第一个效率更高
    }

    @Test
    public void test8() {
        String test = "i am a boy";
        StringBuilder sb = new StringBuilder(test);
        Pattern pattern = Pattern.compile("(?:[_]|\\s|^)\\w");
        Matcher matcher = pattern.matcher(test);
        while (matcher.find()) {
            String a = matcher.group();
            int start = matcher.start();
            int end = matcher.end();
            sb.replace(start, end, a.toUpperCase());
        }
        System.out.println(test);
        System.out.println(sb.toString());
    }

    private static void printMather(Matcher matcher) {
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}

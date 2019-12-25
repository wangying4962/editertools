package com.study.editertools.utils;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PdfUtil {
    public static void main() throws IOException {
        String fileName = "G:\\水浒传（改三校）.pdf";
        Map<String, Integer> allAnnotationMap = getAllAnnotation(fileName);
        PdfReader reader = new PdfReader(fileName);
        PdfReaderContentParser parser = new PdfReaderContentParser(reader);
        TextExtractionStrategy strategy;
        for (int page = 1; page <= reader.getNumberOfPages(); page++) {
            strategy = parser.processContent(page, new SimpleTextExtractionStrategy());
            String resultantText = strategy.getResultantText();

            resultantText = resultantText.replaceAll("\\n([a-z])\\n", "---Anno---");

            checkPrefix(resultantText, page, '儿');

            singleWord(resultantText, page);

            threeLinesForPage(resultantText, page);

            checkAnnotation(allAnnotationMap, resultantText, page);

        }

    }


    /**
     * 不能出现在第一个的字符
     *
     * @param resultantText
     * @param page
     */
    public static void checkPrefix(String resultantText, int page, char c) {
        String[] lines = resultantText.split("\\n");
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (line != null && line.length() != 0) {
                if (line.charAt(0) == c) {
                    System.out.println("第" + page + "页，第" + i + "行，出现某一行第一个字是：\"" + c + "\"的地方");
                }
            }

        }
    }

    /**
     * 单字符不能成一行
     */
    public static void singleWord(String resultantText, int page) {
        String[] lines = resultantText.split("\\n");
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (!line.equals(" ") && line.length() == 1) {
                System.out.println("第" + page + "页，第" + i + "行，单字成行了");
            }

        }

    }

    /**
     * 三行不能成页
     *
     * @param resultantText
     * @param page
     */
    public static void threeLinesForPage(String resultantText, int page) {
        String[] lines = resultantText.split("\\n");
        if (!"".equals(resultantText) && lines.length <= 3) {
            System.out.println("第" + page + "页，只有3行");
        }
    }

    /**
     * 获取所有脚注
     */
    public static Map<String, Integer> getAllAnnotation(String fileName) throws IOException {
        PdfReader reader = new PdfReader(fileName);
        PdfReaderContentParser parser = new PdfReaderContentParser(reader);
        TextExtractionStrategy strategy;
        Map<String, Integer> map = new HashMap<>();
        for (int page = 1; page <= reader.getNumberOfPages(); page++) {
            strategy = parser.processContent(page, new SimpleTextExtractionStrategy());
            String resultantText = strategy.getResultantText();

            resultantText = resultantText.replaceAll("\\n([a-z])\\n", "---Anno---");

            String[] lines = resultantText.split("\\n");

            for (int i = 0; i < lines.length; i++) {
                String line = lines[i];
                if (line.length() > 0 && line.charAt(0) >= 97 && line.charAt(0) < 122) {
                    //获取脚注
                    String pattern = "[ |　](.*)[ |　]";

                    // 创建 Pattern 对象
                    Pattern r = Pattern.compile(pattern);

                    // 现在创建 matcher 对象
                    Matcher m = r.matcher(line);
                    if (m.find()) {
                        String s = m.group(0).replaceAll(" ", "");
                        map.put(s, page);
                    }
                }
            }

        }

        return map;
    }

    public static void checkAnnotation(Map<String, Integer> annotationMap, String resultantText, int page) {
        Set<Map.Entry<String, Integer>> entries = annotationMap.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            String key = entry.getKey();//标注
            Integer value = entry.getValue();//标注所在的页码

            resultantText = resultantText.replaceAll("\n", "");

            //获取脚注
            String pattern = key;

            // 创建 Pattern 对象
            Pattern r = Pattern.compile(pattern);

            // 现在创建 matcher 对象
            Matcher m = r.matcher(resultantText);
            if (m.find()) {
                if (value > page) {
                    System.out.println("第" + value + "页标注:\"" + key + "\"在" + page + "页提前出现了");
                }

            }

        }
    }
}

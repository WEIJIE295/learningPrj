package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tester {

    public static void main(String[] args) {
        //�����ַ���
        String source="<div class='wp_articlecontent'><div class='Article_Content'><DIV><SPAN style=\"FONT-SIZE: 18px\">����Դ����ƽ̨�豸������Ҫ��Ӧ��������ԴѧԺҪ����������ԴѧԺ������Դ����ƽ̨�豸���е��ԣ���ͣ��ʩ��������ͣ��ʱ�䣺2017��7��28�գ����壩��7��31�գ���һ��ÿ��19:00��24:00����Ӱ�����������������磺��¥Ⱥ������ʵ���ҡ��������ġ����ѧԺ������ѧԺ������ѧԺ�������¥������ѧԺ������ѧԺ������¥Ⱥ�������������㾴���½⡣��ϵ�ˣ�����ʦ���绰��22868189�� </SPAN></DIV><P style=\"TEXT-ALIGN: right; FONT-SIZE: 18px\">���ڷ�����</P><P style=\"TEXT-ALIGN: right\"><SPAN style=\"FONT-SIZE: 18px\">2017��7��28��</SPAN></P></div></div> ";
        StringBuilder result=new StringBuilder();
        System.out.println("=======��ʼƥ����������========");
        //ƥ���������ʽ
        String patternStrs="<meta name=\"description\" content=\"(.*)\".*/>";
//        String patternStrs=".*";
        System.out.println("--------ƥ��������ʽ-------"+patternStrs);
        Pattern pattern=Pattern.compile(patternStrs);
        Matcher matcher=pattern.matcher(source);
        while(matcher.find()){
//            result.append(matcher.group()+"\n");
//            result.append("======================\n");
            result.append(matcher.group(4)+"\n");
            result.append("======================\n");
//            result.append(matcher.group(1)+"\n");
//            result.append("======================\n");
//            result.append(matcher.group(2)+"\n");
            result.append("======================\n");
//            result.append(matcher.group(3)+"\n");
        }
        System.out.println(result.toString());
        
        System.out.println("=======��ʼƥ��name����ֵ========");
        String patternName="(<div class=\"hdwiki_tmml\"><a.+?)name=\"(.+?)\">(.+?</a></div>)";
        pattern=Pattern.compile(patternName);
        matcher=pattern.matcher(source);
        result=result.delete(0, result.length());
        while(matcher.find()){
            result.append(matcher.group(2)+"\n");
        }
        System.out.println(result.toString());
    }

}

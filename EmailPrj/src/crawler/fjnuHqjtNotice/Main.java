package crawler.fjnuHqjtNotice;

import java.util.List;

import crawler.utils.MailUtil;

public class Main {
    private final static String myEmailSMTPHost = "smtp.sina.com";// ����������� SMTP ��������ַ
    private final static String port = "143";// �˿�
    private final static String sendMail = "dayinz1@sina.com";// ���������˺�
    private final static String sendMailPassword = "a123456";// ������������
    private final static String sendName = "���ڹ�������";// ������
    private final static String receiveMail = "347579300@qq.com";// �ռ�����;//903914294
    private final static String receiveName = "�ռ���";// �ռ���;

    private final static String charset = "utf-8";// ����;
    private static FjnuHqjtNoticeCrawler noticeCrawler = new FjnuHqjtNoticeCrawler();

    public static void main(String[] args) {
        List<FjnuHqjtNoticeBean> list = noticeCrawler.getNoticeList();
        for (FjnuHqjtNoticeBean fjnuHqjtNoticeBean : list) {
            String subject = fjnuHqjtNoticeBean.getTittle();// ����;
            String contant = "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp" + fjnuHqjtNoticeBean.getContant() + "<br><br>�����"
                    + fjnuHqjtNoticeBean.getUrl() + "<br><br>" + fjnuHqjtNoticeBean.getDate();// ����;
            MailUtil.sendMailBySmtp(myEmailSMTPHost, port, sendMail, sendMailPassword, sendName, receiveMail,
                    receiveName, subject, contant, charset);

        }
    }
}

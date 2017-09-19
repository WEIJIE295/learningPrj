package crawler.utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * �ʼ�����
 * 
 * @author liuwenkang
 *
 */
public class MailUtil {
    // PS: ĳЩ���������Ϊ���������䱾������İ�ȫ�ԣ��� SMTP �ͻ��������˶������루�е������Ϊ����Ȩ�롱��,
    // ���ڿ����˶������������, ����������������ʹ������������루��Ȩ�룩��
    // public static String myEmailAccount = "dayinz1@sina.com";//��������
    // public static String myEmailPassword = "a123456";//���������˺�
    // public static String myEmailSMTPHost = "smtp.sina.com";//����������� SMTP ��������ַ
    // public static String receiveMailAccount = "903914294@qq.com";//�ռ�����

    private final static String myEmailSMTPHost = "smtp.sina.com";// ����������� SMTP ��������ַ
    private final static String port = "143";// �˿�
    private final static String sendMail = "dayinz1@sina.com";// ���������˺�
    private final static String sendMailPassword = "a123456";// ������������
    private final static String sendName = "���㿵";// ������
    private final static String receiveMail = "347579300@qq.com";// �ռ�����;
    private final static String receiveName = "�ռ���";// �ռ���;
    private final static String subject = "����";// ����;
    private final static String contant = "����";// ����;
    private final static String charset = "utf-8";// ����;

    public static void main(String[] args) throws Exception {
        sendMailBySmtp(myEmailSMTPHost, port, sendMail, sendMailPassword, sendName, receiveMail, receiveName, subject,
                contant, charset);
    }

    /**
     * ����һ��ֻ�����ı��ļ��ʼ�
     *
     * @param session �ͷ����������ĻỰ
     * @param sendMail ����������
     * @param receiveMail �ռ�������
     * @return
     * @throws Exception
     */
    public static MimeMessage createMimeMessage(Session session, String sendMail, String sendName, String receiveMail,
            String receiveName, String subject, String contant, String charset) throws Exception {
        // 1. ����һ���ʼ�
        MimeMessage message = new MimeMessage(session);
        // 2. From: �����ˣ��ǳ��й�����ɣ����ⱻ�ʼ�����������Ϊ���ķ������������ʧ�ܣ����޸��ǳƣ�
        message.setFrom(new InternetAddress(sendMail, sendName, charset));
        // 3. To: �ռ��ˣ��������Ӷ���ռ��ˡ����͡����ͣ�
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, receiveName, charset));
        // 4. Subject: �ʼ����⣨�����й�����ɣ����ⱻ�ʼ�����������Ϊ���ķ������������ʧ�ܣ����޸ı��⣩
        message.setSubject(subject, charset);
        // 5. Content: �ʼ����ģ�����ʹ��html��ǩ���������й�����ɣ����ⱻ�ʼ�����������Ϊ���ķ������������ʧ�ܣ����޸ķ������ݣ�
        message.setContent(contant, "text/html;charset=" + charset);
        // 6. ���÷���ʱ��
        message.setSentDate(new Date());
        // 7. ��������
        message.saveChanges();
        return message;
    }

    public static void sendMailBySmtp(String myEmailSMTPHost, String port, String sendMail, String sendMailPassword,
            String sendName, String receiveMail, String receiveName, String subject, String contant, String charset) {
        // 1. ������������, ���������ʼ��������Ĳ�������
        Properties props = new Properties(); // ��������
        props.setProperty("mail.transport.protocol", "smtp"); // ʹ�õ�Э�飨JavaMail�淶Ҫ��
        props.setProperty("mail.smtp.host", myEmailSMTPHost); // �����˵������ SMTP ��������ַ
        props.setProperty("mail.smtp.auth", "true"); // ��Ҫ������֤

        // PS: ĳЩ���������Ҫ�� SMTP ������Ҫʹ�� SSL ��ȫ��֤ (Ϊ����߰�ȫ��, ����֧��SSL����, Ҳ�����Լ�����),
        // ����޷������ʼ�������, ��ϸ�鿴����̨��ӡ�� log, ����������� ������ʧ��, Ҫ�� SSL ��ȫ���ӡ� �ȴ���,
        // ������ /* ... */ ֮���ע�ʹ���, ���� SSL ��ȫ���ӡ�

        /*
         * props.setProperty("mail.smtp.port", smtpPort);
         * props.setProperty("mail.smtp.socketFactory.class",
         * "javax.net.ssl.SSLSocketFactory");
         * props.setProperty("mail.smtp.socketFactory.fallback", "false");
         */
        props.setProperty("mail.smtp.socketFactory.port", port);// smtp�˿� ��ǰ����Ӧ������������Ҷ˿�

        // 2. �������ô����Ự����, ���ں��ʼ�����������
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true); // ����Ϊdebugģʽ, ���Բ鿴��ϸ�ķ��� log

        // 3. ����һ���ʼ�
        try {
            MimeMessage message = createMimeMessage(session, sendMail, sendName, receiveMail, receiveName, subject,
                    contant, charset);

            // 4. ���� Session ��ȡ�ʼ��������
            Transport transport = session.getTransport();

            // 5. ʹ�� �����˺� �� ���� �����ʼ�������, ������֤����������� message �еķ���������һ��, ���򱨴�
            // ����ʧ�ܵ�ԭ��ͨ��Ϊ���¼���, ��ϸ������:
            // (1) ����û�п��� SMTP ����;
            // (2) �����������, ����ĳЩ���俪���˶�������;
            // (3) ���������Ҫ�����Ҫʹ�� SSL ��ȫ����;
            // (4) �������Ƶ��������ԭ��, ���ʼ��������ܾ�����;
            // (5) ������ϼ��㶼ȷ������, ���ʼ���������վ���Ұ�����
            transport.connect(sendMail, sendMailPassword);

            // 6. �����ʼ�, �������е��ռ���ַ, message.getAllRecipients() ��ȡ�������ڴ����ʼ�����ʱ��ӵ������ռ���, ������,
            // ������
            transport.sendMessage(message, message.getAllRecipients());

            // 7. �ر�����
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendGroupMailBySmtp(String myEmailSMTPHost, String port, String sendMail,
            String sendMailPassword, String sendName, String receiveMail, String receiveName, String subject,
            String contant, String charset) throws Exception {
        sendMailBySmtp(myEmailSMTPHost, port, sendMail, sendMailPassword, sendName, receiveMail, receiveName, subject,
                contant, charset);
    }
}

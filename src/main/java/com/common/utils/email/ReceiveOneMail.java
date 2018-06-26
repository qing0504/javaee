package com.common.utils.email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * @author wanchongyang
 * @date 2018/6/15 下午3:19
 */
public class ReceiveOneMail {
    private MimeMessage mimeMessage = null;
    /**
     * 附件下载后的存放目录
     */
    private String saveAttachPath = "";
    /**
     * 存放邮件内容
     */
    private StringBuffer bodytext = new StringBuffer();
    /**
     * 默认的日期显示格式
     */
    private String dateformat = "yy-MM-dd HH:mm";

    public ReceiveOneMail(MimeMessage mimeMessage) {
        this.mimeMessage = mimeMessage;
    }

    public void setMimeMessage(MimeMessage mimeMessage) {
        this.mimeMessage = mimeMessage;
    }

    /**
     * 获得发件人的地址和姓名
     */
    public String getFrom() throws Exception {
        InternetAddress address[] = (InternetAddress[]) mimeMessage.getFrom();
        String from = address[0].getAddress();
        if (from == null) {
            from = "";
        }
        String personal = address[0].getPersonal();
        if (personal == null) {
            personal = "";
        }
        String fromaddr = personal + "<" + from + ">";
        return fromaddr;
    }

    /**
     * 获得邮件的收件人，抄送，和密送的地址和姓名，根据所传递的参数的不同 "to"----收件人 "cc"---抄送人地址 "bcc"---密送人地址
     */
    public String getMailAddress(String type) throws Exception {
        String mailaddr = "";
        String addtype = type.toUpperCase();
        InternetAddress[] address = null;
        if (addtype.equals("TO") || addtype.equals("CC")|| addtype.equals("BCC")) {
            if (addtype.equals("TO")) {
                address = (InternetAddress[]) mimeMessage.getRecipients(Message.RecipientType.TO);
            } else if (addtype.equals("CC")) {
                address = (InternetAddress[]) mimeMessage.getRecipients(Message.RecipientType.CC);
            } else {
                address = (InternetAddress[]) mimeMessage.getRecipients(Message.RecipientType.BCC);
            }
            if (address != null) {
                for (int i = 0; i < address.length; i++) {
                    String email = address[i].getAddress();
                    if (email == null) {
                        email = "";
                    } else {
                        email = MimeUtility.decodeText(email);
                    }
                    String personal = address[i].getPersonal();
                    if (personal == null) {
                        personal = "";
                    } else {
                        personal = MimeUtility.decodeText(personal);
                    }
                    String compositeto = personal + "<" + email + ">";
                    mailaddr += "," + compositeto;
                }
                mailaddr = mailaddr.substring(1);
            }
        } else {
            throw new Exception("Error emailaddr type!");
        }
        return mailaddr;
    }

    /**
     * 获得邮件主题
     */
    public String getSubject() throws MessagingException {
        String subject = "";
        try {
            subject = MimeUtility.decodeText(mimeMessage.getSubject());
            if (subject == null) {
                subject = "";
            }
        } catch (Exception exce) {}
        return subject;
    }

    /**
     * 获得邮件发送日期
     */
    public String getSentDate() throws Exception {
        Date sentdate = mimeMessage.getSentDate();
        SimpleDateFormat format = new SimpleDateFormat(dateformat);
        return format.format(sentdate);
    }

    /**
     * 获得邮件正文内容
     */
    public String getBodyText() {
        return bodytext.toString();
    }

    /**
     * 解析邮件，把得到的邮件内容保存到一个StringBuffer对象中，解析邮件 主要是根据MimeType类型的不同执行不同的操作，一步一步的解析
     */
    public void getMailContent(Part part) throws Exception {
        String contentType = part.getContentType();
        int nameIndex = contentType.indexOf("name");
        boolean conname = false;
        if (nameIndex != -1) {
            conname = true;
        }
        System.out.println("CONTENTTYPE: " + contentType);
        if (part.isMimeType("text/plain") && !conname) {
            bodytext.append((String) part.getContent());
        } else if (part.isMimeType("text/html") && !conname) {
            bodytext.append((String) part.getContent());
        } else if (part.isMimeType("multipart/*")) {
            Multipart multipart = (Multipart) part.getContent();
            int counts = multipart.getCount();
            for (int i = 0; i < counts; i++) {
                getMailContent(multipart.getBodyPart(i));
            }
        } else if (part.isMimeType("message/rfc822")) {
            getMailContent((Part) part.getContent());
        } else {
            //throw new Exception("invalid contentType.");
        }
    }

    /**
     * 判断此邮件是否需要回执，如果需要回执返回"true",否则返回"false"
     */
    public boolean getReplySign() throws MessagingException {
        boolean replySign = false;
        String[] needReply = mimeMessage
                .getHeader("Disposition-Notification-To");
        if (needReply != null) {
            replySign = true;
        }
        return replySign;
    }

    /**
     * 获得此邮件的Message-ID
     */
    public String getMessageId() throws MessagingException {
        return mimeMessage.getMessageID();
    }

    /**
     * 【判断此邮件是否已读，如果未读返回返回false,反之返回true】
     */
    public boolean isNew() throws MessagingException {
        boolean isNew = false;
        Flags flags = mimeMessage.getFlags();
        Flags.Flag[] flag = flags.getSystemFlags();
        System.out.println("flags's length: " + flag.length);
        for (int i = 0; i < flag.length; i++) {
            if (flag[i] == Flags.Flag.SEEN) {
                isNew = true;
                System.out.println("seen Message.......");
                break;
            }
        }
        return isNew;
    }

    /**
     * 判断此邮件是否包含附件
     */
    public boolean isContainAttach(Part part) throws Exception {
        boolean attachFlag = false;
        String contentType = part.getContentType();
        if (part.isMimeType("multipart/*")) {
            Multipart mp = (Multipart) part.getContent();
            for (int i = 0; i < mp.getCount(); i++) {
                BodyPart mpart = mp.getBodyPart(i);
                String disposition = mpart.getDisposition();
                boolean flag = (disposition != null)
                        && ((disposition.equals(Part.ATTACHMENT)) || (disposition.equals(Part.INLINE)));
                if (flag) {
                    attachFlag = true;
                } else if (mpart.isMimeType("multipart/*")) {
                    attachFlag = isContainAttach(mpart);
                } else {
                    String contype = mpart.getContentType();
                    if (contype.toLowerCase().indexOf("application") != -1) {
                        attachFlag = true;
                    }
                    if (contype.toLowerCase().indexOf("name") != -1) {
                        attachFlag = true;
                    }
                }
            }
        } else if (part.isMimeType("message/rfc822")) {
            attachFlag = isContainAttach((Part) part.getContent());
        }
        return attachFlag;
    }

    /**
     * 【保存附件】
     */
    public void saveAttachment(Part part) throws Exception {
        String fileName = "";
        if (part.isMimeType("multipart/*")) {
            Multipart mp = (Multipart) part.getContent();
            for (int i = 0; i < mp.getCount(); i++) {
                BodyPart mpart = mp.getBodyPart(i);
                String disposition = mpart.getDisposition();
                if ((disposition != null)
                        && ((disposition.equals(Part.ATTACHMENT)) || (disposition.equals(Part.INLINE)))) {
                    fileName = mpart.getFileName();
                    if (fileName.toLowerCase().indexOf("gb2312") != -1) {
                        fileName = MimeUtility.decodeText(fileName);
                    }
                    saveFile(fileName, mpart.getInputStream());
                } else if (mpart.isMimeType("multipart/*")) {
                    saveAttachment(mpart);
                } else {
                    fileName = mpart.getFileName();
                    if ((fileName != null)
                            && (fileName.toLowerCase().indexOf("GB2312") != -1)) {
                        fileName = MimeUtility.decodeText(fileName);
                        saveFile(fileName, mpart.getInputStream());
                    }
                }
            }
        } else if (part.isMimeType("message/rfc822")) {
            saveAttachment((Part) part.getContent());
        }
    }

    /**
     * 【设置附件存放路径】
     */

    public void setAttachPath(String attachPath) {
        this.saveAttachPath = attachPath;
    }

    /**
     * 【设置日期显示格式】
     */
    public void setDateFormat(String format) {
        this.dateformat = format;
    }

    /**
     * 【获得附件存放路径】
     */
    public String getAttachPath() {
        return saveAttachPath;
    }

    /**
     * 【真正的保存附件到指定目录里】
     */
    private void saveFile(String fileName, InputStream in) throws Exception {
        String osName = System.getProperty("os.name");
        String storeDir = getAttachPath();
        String separator = "";
        if (osName == null) {
            osName = "";
        }
        if (osName.toLowerCase().indexOf("win") != -1) {
            separator = "\\";
            if (storeDir == null || storeDir.equals("")) {
                storeDir = "c:\\tmp";
            }
        } else {
            separator = "/";
            storeDir = "/tmp";
        }
        File storeFile = new File(storeDir + separator + fileName);
        System.out.println("storefile's path: " + storeFile.toString());
        // for(int i=0;storefile.exists();i++){
        // storefile = new File(storedir+separator+fileName+i);
        // }
        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(storeFile));
            bis = new BufferedInputStream(in);
            int c;
            while ((c = bis.read()) != -1) {
                bos.write(c);
                bos.flush();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new Exception("文件保存失败!");
        } finally {
            bos.close();
            bis.close();
        }
    }

    /**
     * PraseMimeMessage类测试
     */
    public static void main(String args[]) throws Exception {
        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.163.com");
        props.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props, null);
        URLName urln = new URLName("pop3", "pop3.163.com", 110, null,
                "happywuya054@163.com", "a1+a2-a3");
        Store store = session.getStore(urln);
        store.connect();
        Folder folder = store.getFolder("INBOX");
        folder.open(Folder.READ_ONLY);
        Message[] message = folder.getMessages();
        System.out.println("Messages's length: " + message.length);
        ReceiveOneMail pmm = null;
        for (int i = 0; i < message.length; i++) {
            System.out.println("======================");
            pmm = new ReceiveOneMail((MimeMessage) message[i]);
            System.out.println("Message " + i + " subject: " + pmm.getSubject());
            System.out.println("Message " + i + " sentdate: "+ pmm.getSentDate());
            System.out.println("Message " + i + " replysign: "+ pmm.getReplySign());
            System.out.println("Message " + i + " hasRead: " + pmm.isNew());
            System.out.println("Message " + i + "  containAttachment: "+ pmm.isContainAttach(message[i]));
            System.out.println("Message " + i + " form: " + pmm.getFrom());
            System.out.println("Message " + i + " to: "+ pmm.getMailAddress("to"));
            System.out.println("Message " + i + " cc: "+ pmm.getMailAddress("cc"));
            System.out.println("Message " + i + " bcc: "+ pmm.getMailAddress("bcc"));
            pmm.setDateFormat("yy年MM月dd日 HH:mm");
            System.out.println("Message " + i + " sentdate: "+ pmm.getSentDate());
            System.out.println("Message " + i + " Message-ID: "+ pmm.getMessageId());
            // 获得邮件内容===============
            pmm.getMailContent(message[i]);
            System.out.println("Message " + i + " bodycontent: \r\n"
                    + pmm.getBodyText());

            // pmm.setAttachPath("c:\\");
            // pmm.saveAttachment(message[i]);
        }
    }
}

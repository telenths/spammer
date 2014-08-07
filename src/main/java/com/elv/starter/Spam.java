package com.elv.starter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.elv.mail.MailMessage;
import com.elv.mail.MailSender;
import com.elv.util.AlphaNumericGen;

public class Spam {

    public static void sendMail(String from, String to, String random) {
        MailMessage message = new MailMessage();
        message.setAuth(true);
        message.setSmtp("smtp.yandex.com");
        message.setPort("25");
        message.setFrom("=?GB2312?B?xr2wssK9vfDL+Q==?= <" + from + "@yandex.com>");
        message.setUsername(from);
        message.setPassword("ttp12345");
        message.getTo().add(to);
        message.setSubject("=?GB2312?B?zqqyxri71PbWtaO6xPq1xMXz09HR+8frxPrAtKGwwr298Mv5obHSu8bwxdzTrkNQ?= =?GB2312?B?SQ==?=" + " " + random);

        try {
            InputStream is = new FileInputStream("a.eml");
            message.setContentInputStream(is);

            MailSender.Send(message);
            System.out.println(" - " + from + " - " + to + " - sent.");
        } catch (Exception e) {
            System.out.println(" - " + from + " - " + to + " - " + e);
        }
    }

    public static void spam() {
        AlphaNumericGen a = new AlphaNumericGen();
        a.setNumbers(true);
//        a.setAlphabet(true);
        a.setStep(-10000);
        List<String> names = a.gen(8, 21207302, 200);

        int count = 0;

        long start = System.currentTimeMillis();

        for (String name : names) {
            count++;
            String from = getFrom(count);

            Spam.sendMail(from, name + "@qq.com", name);
//            Spam.sendMail(from, name + "@sina.cn");
//            Spam.sendMail(from, name + "@21cn.com");
//            Spam.sendMail(from, name + "@163.com");
//            Spam.sendMail(from, name + "@yeah.net");
//            Spam.sendMail(from, name + "@sohu.com");
//            Spam.sendMail(from, name + "@tom.com");

            try {
                int sleep = (int) (Math.random() * 10000);
                System.out.println(sleep + " --> " + count);
                Thread.sleep(sleep);
            } catch (Exception e) {
            }
        }

        long time = System.currentTimeMillis() - start;

        System.out.println(count + " - " + time + "ms" + " - " + time / 1000 + "s");
    }

    private static String getFrom(int count) {
        switch (count % 2) {
        case 0:
            return "lufax.c";
        case 1:
            return "lufax.c";
        }

        return "lufax.c";
    }

    public static void main(String[] args) throws AddressException, MessagingException, FileNotFoundException, InterruptedException {
        Spam.spam();
    }

}

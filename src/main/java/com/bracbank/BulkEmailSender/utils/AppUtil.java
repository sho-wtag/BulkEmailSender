/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bracbank.BulkEmailSender.utils;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import com.bracbank.BulkEmailSender.user_auth.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 *
 * @author sarker
 */
@PropertySource(value = "classpath:application.properties")
@Component
public class AppUtil {

    @Autowired
    SessionService sessionService;

    @Value("${app.name}")
    private String APP_NAME;

    @Value("${app.full_name}")
    private String APP_FULL_NAME;
    
    public static String SHA1(String plainText) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            byte[] hash = messageDigest.digest(plainText.getBytes());

            return new String(hash);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        return null;
    }
    
    public synchronized long getMaxVersion(){
        return Calendar.getInstance().getTimeInMillis();
    }

    public static String SHA256(String plainText) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(plainText.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

    public synchronized static UUID toUUID(Object obj) {
        try {
            return UUID.fromString(obj.toString());
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        return null;
    }
    
    public String genToken(){
        String tmpToken = SHA256("SaRkEr" + UUID.randomUUID());
        sessionService.setToken(tmpToken);
        return tmpToken;
    }
    
    public String getToken(){
        return sessionService.getToken();
    }

    public synchronized static UUID toUUID(String obj) {
        try {
            return UUID.fromString(obj);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        return null;
    }

    public static String toString(Object str) {
        if (str == null) {
            return "";
        }
        try {
            return String.valueOf(str);
        } catch (Exception ex) {
            //ex.printStackTrace();
        }

        return "";
    }

    public static int toInt(String number) {
        try {
            return Integer.parseInt(number);
        } catch (Exception ex) {
            //ex.printStackTrace();
        }

        return 0;
    }
    
    public static int toInt(Object number) {
        try {
            return Integer.parseInt(number.toString());
        } catch (Exception ex) {
            //ex.printStackTrace();
        }

        return 0;
    }

    public static long toLong(Object number) {
        try {
            return Long.parseLong(number + "");
        } catch (Exception ex) {
            //ex.printStackTrace();
        }

        return 0;
    }

    public static long toLong(String number) {
        try {
            return Long.parseLong(number);
        } catch (Exception ex) {
            //ex.printStackTrace();
        }

        return 0;
    }

    public static boolean toBoolean(Object val) {
        try {
            return (boolean) val;
        } catch (Exception ex) {
            //ex.printStackTrace();
        }

        return false;
    }

    public static double toDouble(Object number) {
        try {
            return Double.parseDouble(number.toString());
        } catch (Exception ex) {
            //ex.printStackTrace();
        }

        return 0.0f;
    }

    public static Date toDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {

            return sdf.parse(date);
        } catch (Exception ex) {
            //ex.printStackTrace();
        }

        return new Date();
    }
    
    public static Date toDateTime(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.parse(date);
        } catch (Exception ex) {
            //ex.printStackTrace();
        }

        return getDateTime();
    }

    public static Date getDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {

            return sdf.parse(sdf.format(new Date()));
        } catch (Exception ex) {
            //ex.printStackTrace();
        }

        return new Date();
    }
    
    public static Date getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(sdf.format(new Date()).toString());
        } catch (Exception ex) {
            //ex.printStackTrace();
        }

        return new Date();
    }
    
    public static String getDateString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.format(new Date());
        } catch (Exception ex) {
            //ex.printStackTrace();
        }

        return null;
    }

    public static synchronized int getRandomNumber(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public static String generatePassword(int length) {
        String charStr = "!@#$%^&*()ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz01234567890_<>{}[].,+";

        String passStr = "";
        for (int i = 0; i < length; i++) {
            int _rn = AppUtil.getRandomNumber(0, 82);
            passStr += charStr.charAt(_rn);
        }

        return passStr;
    }

    public String getAppName() {
        try {
            return APP_NAME;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "CMS";
    }

    public String getAppFullName() {
        try {
            return APP_FULL_NAME;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Contact Management System";
    }
    
    public String getRootDir() {
        try {
            return "";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/AIPIMG";
    }
}

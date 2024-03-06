package com.mh.ga.administrative.models.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PersonDocumentUtil {

    public static String toHide(String document) {
        final String regex = "(\\d{3})\\.?(\\d{3})\\.?(\\d{3})-?(\\d{2})";
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(document);
        StringBuilder result = new StringBuilder();

        while (matcher.find()) {
            result.append("***.");
            result.append(matcher.group(2));
            result.append(".");
            result.append(matcher.group(3));
            result.append("-**");
        }
        return result.toString();
    }

    public static boolean isHidden(String document) {
        final String regex = "[*]{3}.\\d{3}.\\d{3}-[*]{2}";
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(document);
        return  matcher.find();
    }

    public static boolean isValid(String document) {
        final Pattern pattern = Pattern.compile("(\\d)");
        final Matcher matcher = pattern.matcher(document);

        int leftResp, rightResp, leftDigit, rightDigit;
        boolean leftResult, rightResult;

        leftResp = rightResp = leftDigit = rightDigit = 0;

        int i = 0;

        while (matcher.find()) {
            int digit = Integer.parseInt(matcher.group(0));
            if (i < 9) {
                leftResp += digit * (i + 1);
            }
            else if (i == 9) {
                leftDigit = digit;
            }

            if (i < 10) {
                rightResp += digit * i;
            }
            else if (i == 10) {
                rightDigit = digit;
            }
            i++;
        }

        // Check Left Digit Is Valid
        leftResp = (leftResp % 11) == 10 ? 0 : (leftResp % 11);
        leftResult = (leftResp == leftDigit);

        // Check Right Digit Is Valid
        rightResp = (rightResp % 11) == 10 ? 0 : (rightResp % 11);
        rightResult = rightResp == rightDigit;

        return leftResult && rightResult;
    }

}

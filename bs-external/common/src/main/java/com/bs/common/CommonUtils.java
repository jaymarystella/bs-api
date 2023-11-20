package com.bs.common;

public final class CommonUtils {
    public static String filter(String contents) {
        return contents.replaceAll("<[^>]+>", "");
    }
}

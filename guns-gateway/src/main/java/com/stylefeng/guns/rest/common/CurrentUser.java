package com.stylefeng.guns.rest.common;

public class CurrentUser {
    private static ThreadLocal<String> context = new ThreadLocal<>();

    public static void savenUserID(String userId) {
        context.set(userId);
    }

    public static String currentUserId() {
        return context.get();
    }
}

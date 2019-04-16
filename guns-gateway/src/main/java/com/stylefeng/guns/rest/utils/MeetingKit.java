package com.stylefeng.guns.rest.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MeetingKit {
    public static <T> void generateActive(List<T> resource, String idStr) throws IllegalAccessException {
        int index = -1;
        for (int i = 0; i < resource.size(); i++) {
            T obj = resource.get(i);
            Map<String, Field> map = getField(obj);
            Field id = map.get("id");
            Field active = map.get("active");
            id.setAccessible(true);
            active.setAccessible(true);
            Class<?> type = id.getType();
            Object value = null;
            Object idEqValue = null;
            if(type.getSimpleName().equals("String")) {
                value = "99";
                idEqValue = idStr;
            } else {
                value = 99;
                idEqValue = Integer.valueOf(idStr);
            }
            Object idValue = id.get(obj);
            T tmp = null;
            if (idValue.equals(value)) {
                tmp = obj;
            }
            if (idValue.equals(idEqValue)) {
                index = i;
                active.setBoolean(obj, true);
            }
            if (index == -1 && (i + 1) == resource.size() && tmp != null) {
                getField(tmp).get("active").setBoolean(tmp, true);
            }

        }

    }

    private static Map<String, Field> getField(Object obj) {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        Map<String, Field> map = new HashMap<>();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getName().indexOf("Id") > 0) {
                map.put("id", field);
            }
            if (field.getName().indexOf("Active") > 0) {
                map.put("active", field);
            }
        }
        return map;
    }
}

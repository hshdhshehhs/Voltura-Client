package me.namenotfound128.voltura.util;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {
    @SafeVarargs
    public static <T> List<T> of(T... values) {
        ArrayList<T> list = new ArrayList<T>();
        for (T value : values) {
            list.add(value);
        }
        return list;
    }

    public static <T> List<T> mergeCopy(T value, List<T> collection) {
        ArrayList<T> copy = new ArrayList<T>(collection);
        copy.add(value);
        return copy;
    }
}

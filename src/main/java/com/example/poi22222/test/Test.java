package com.example.poi22222.test;

import com.example.poi22222.test.entity.CCC;
import com.example.poi22222.test.entity.SubClass;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) throws IllegalAccessException, ClassNotFoundException {
        SubClass a = new SubClass();
        a.setName("小明");
        List<CCC> list = new ArrayList<>();
        list.add(new CCC("a", 1));
        list.add(new CCC("b", 2));
        list.add(new CCC("v", 3));
        list.add(new CCC("d", 4));
        a.setCCCS(list);

        search(a);
    }

    private static void search(SubClass a) throws IllegalAccessException, ClassNotFoundException {
        Class<? extends SubClass> aClass = a.getClass();
        Field[] fields = aClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            if (i == 0) {
                Object o = field.get(a);
                System.out.println(o);
            } else {
                List qq = (List) field.get(a);
                String typeName = fields[i].getGenericType().getTypeName();
//                Class<?> aClass1 = Class.forName(typeName);
//                Field[] declaredFields = aClass1.getDeclaredFields();
                for (Object o : qq) {
                    Field[] declaredFields = o.getClass().getDeclaredFields();
                    for (Field ff1 : declaredFields) {
                        ff1.setAccessible(true);
                        Object o1 = ff1.get(o);
                        System.out.println(o1);
                    }
                }
            }

        }
    }
}

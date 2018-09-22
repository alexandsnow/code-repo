package com.alred.repo.Optionnal;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GyList {

    public static void main(String[] args) {
        List<String> userNameList = Arrays.asList("Test1", "Test2", "Test3", "gaoyang", "what");
        // 定义过滤器
        Predicate<String> startWithPrefix = str -> str.startsWith("Test");
        Predicate<String> endWithThree = str -> str.endsWith("3");

        List<String> TestStartUserList = userNameList.stream().filter(e -> e.startsWith("Test")).filter(e -> e.endsWith("3")).collect(Collectors.toList());
        System.out.println(userNameList.stream().filter(startWithPrefix.and(endWithThree)).collect(Collectors.toList()).contains("Test3"));
        TestStartUserList.forEach(System.out::println);
        System.out.println(userNameList.stream().filter(startWithPrefix).collect(Collectors.joining(" | ")));
    }
}

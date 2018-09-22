package com.alred.repo.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class GyRunnable {
    public static void main(String[] args) {
        UserDto userDto = new UserDto();
        userDto.setName("gaoyang");
        userDto.setAge("20");
        GyFunctionalInterface gyFunctionalInterface = (u) -> {
            System.out.println(u.getName());
            System.out.println(u.toString());
        };

        GyFunctionalInterface gyFunctionalInterface1 = UserDto::showUserInfo;
        GyFunctionalInterface gyFunctionalInterface2 = System.out::println;
        GyFunctionalInterface gyFunctionalInterface3 = userDto::myTest;
        gyFunctionalInterface.show(userDto);
        gyFunctionalInterface1.show(userDto);
        gyFunctionalInterface2.show(userDto);
        gyFunctionalInterface3.show(userDto);


        Consumer<String> consumer = System.out::println;
        List<String> userNameList = new ArrayList<String>(){{
            add("GaoYang");
            add("Intellij");
        }};

        userNameList.forEach(consumer);
        System.out.println(userNameList.stream().collect(Collectors.joining(" | ")));
    }
}

@FunctionalInterface
interface GyFunctionalInterface<T extends UserDto> {

    default void test() {
        System.out.println("test");
    }

    void show(T dto);
}

class UserDto {
    String name;
    String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public static void showUserInfo(UserDto userDto) {
        System.out.println("showUserInfo method : " + userDto.toString());
    }

    public void myTest(UserDto userDto){
        System.out.println("myTest");
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}


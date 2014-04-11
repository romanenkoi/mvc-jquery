package com.romanenko;


import com.romanenko.dto.UserListDto;
import com.romanenko.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("db_context.xml");
        UserService service = (UserService) context.getBean("UserService");

        UserListDto userListDto = new UserListDto();
        userListDto.setUsers(service.findAll());

        System.out.println("Done");
    }
}

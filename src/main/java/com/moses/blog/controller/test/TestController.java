package com.moses.blog.controller.test;

import com.moses.blog.entity.TestData;
import com.moses.blog.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Moses
 */
@Controller
public class TestController {

    @Autowired
    TestMapper testMapper;

    @RequestMapping("test.action")
    public String test() {
        int a = 1;
        int b = 2;
        System.out.println(a + b);
        System.out.println("hello");
        System.out.println("hello");
        return null;
    }

    @ResponseBody
    @RequestMapping("test1.action")
    public TestData test1() {
        TestData testData = new TestData("123456", "123456");
        testData.setUsername("111111");
        System.out.println(testData.getUsername());
        System.out.println(testData.getPassword());
        return testData;
    }


    @RequestMapping("getId.action")
    public String getId(ModelMap map) {
        TestData data = new TestData();
        data.setUsername("username");
        data.setPassword("password");
        Integer id = 5;
        map.put("id", id);
        map.put("data", data);
        return "test";
    }

    @ResponseBody
    @RequestMapping("getJson.json")
    public List<TestData> getJson() {
        List<TestData> list = new ArrayList<>();
        TestData data = new TestData("今晚月色真美", "怦然心动");
        TestData data2 = new TestData("今晚月色真美2", "怦然心动");
        TestData data3 = new TestData("今晚月色真美3", "怦然心动");
        TestData data4 = new TestData("今晚月色真美4", "怦然心动");
        list.add(data);
        list.add(data2);
        list.add(data3);
        list.add(data4);

        return list;
    }

    @ResponseBody
    @RequestMapping("getObjectParameter.json")
    public TestData getObjectParameter(@RequestParam("data") TestData data) {
        System.out.println(data.getUsername());
        System.out.println(data.getPassword());
        return null;
    }
}

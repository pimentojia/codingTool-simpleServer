package com.simpleserver.web.controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController extends BaseController {


    @PostMapping(value = "/hello")
    @CrossOrigin(origins = "http://localhost:3000")
    public String sayHello(@RequestParam("classCode") String classCode,
                           @RequestBody String param) {
        JSONArray jsonArray = JSONArray.fromObject(param);
//        JSONObject jsonObject = JSONObject.fromObject(param);
//		if(userService != null)
//			test = testItem + ", dubbo is ok!";
        Map<String, Object> res = new HashMap<>();
        res.put("errCode", 200);
        res.put("message", "推送成功");
        JSONObject jsonObject = JSONObject.fromObject(res);
        return jsonObject.toString();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/test")
    public String test(@RequestParam("param") String param) {
//		if(userService != null)
//			test = testItem + ", dubbo is ok!";
        return "test, " + param + "! ";
    }

    /**
     * 获取时间组件时间
     * @return String
     */
    @GetMapping(path = "getTime")
    public String getTime() {
        long time = new Date().getTime();
        Map<String, Object> res = new HashMap<>();
        res.put("status", "success");
        res.put("time", String.valueOf(time) + "000000");
        return buildSuccessMessage(ResultModal.SUCCESS_MSG, res);
    }

}

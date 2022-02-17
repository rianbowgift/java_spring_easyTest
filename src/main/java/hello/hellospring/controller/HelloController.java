package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","helloㅋㅋㅋ!!");
        return "hello";

    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam( "name") String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello " + name; //html다없애고 바디만 보낸다
    }

    @GetMapping("hello-Api")   // json방식 = 키/벨류, 디폴트로 반환이 json
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;       //반환이 스트링인지 객체인지에따라 컨버터가 다름.

    }

    static class Hello{
        private String name;

        public String getName() {       //알트+인설트
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}

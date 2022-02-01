package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping(value="/hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!!!!");

        // 여기 return은 화면 이름을 뜻한다. 스프링 부트에서 제공하는 기능으로 thymleaf면 template 폴더 아래에서 화면 찾는다.
        return "hello";
    }

}

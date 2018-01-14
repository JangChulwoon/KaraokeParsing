package org.karaoke.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @Value("${ajax.url.path}")
    String path;

    @GetMapping("/")
    public String viewer(Model model){
        model.addAttribute("url",path);
        return "index";
    }
}

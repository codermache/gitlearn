package com.example.basic.controller;

import com.example.basic.utils.Utils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public ResponseEntity<String> hello(@RequestParam String name) {
        if (Utils.isNullOrEmpty(name)) {
            name = "Anonymus";
        }
        return ResponseEntity.ok("Hello " + name);
    }

    @GetMapping("/hello-multiple-name")
    public ResponseEntity<String> helloMultipleName(@RequestParam List<String> names) {
        String name = null;
        if (Utils.isNullOrEmpty(names)) {
            name = "Anonymus";
        } else {
            name = String.join(",", names);
        }
        return ResponseEntity.ok("Hello " + name);
    }
}

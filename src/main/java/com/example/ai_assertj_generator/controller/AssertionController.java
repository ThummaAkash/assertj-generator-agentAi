package com.example.ai_assertj_generator.controller;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ai_assertj_generator.service.AssertionService;

@RestController
@RequestMapping("/api/assertions")
public class AssertionController {

    private final AssertionService assertionService;

    public AssertionController(AssertionService assertionService) {
        this.assertionService = assertionService;
    }

    @PostMapping("/generate")
    public ResponseEntity<String> generate(@RequestBody String javaClassCode) {
        String result = assertionService.generateAssertions(javaClassCode);
        return ResponseEntity.ok(result);
    }
}


package com.example.ai_assertj_generator.service;


import org.springframework.stereotype.Service;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

@Service
public class AssertionService {

	public String generateAssertions(String classCode) {
	    StringBuilder sb = new StringBuilder();

	    JavaParser parser = new JavaParser();
	    ParseResult<CompilationUnit> result = parser.parse(classCode);

	    if (result.isSuccessful() && result.getResult().isPresent()) {
	        CompilationUnit cu = result.getResult().get();

	        cu.findFirst(ClassOrInterfaceDeclaration.class).ifPresent(clazz -> {
	            clazz.getFields().forEach(field -> {
	                String name = field.getVariable(0).getNameAsString();
	                sb.append("assertThat(actual.get")
	                        .append(capitalize(name))
	                        .append("()).isEqualTo(expected.get")
	                        .append(capitalize(name))
	                        .append("());\n");
	            });
	        });
	    }

	    return sb.toString();
	}
	
    private String capitalize(String str) {
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }
}





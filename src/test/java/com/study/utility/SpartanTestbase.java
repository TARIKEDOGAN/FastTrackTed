package com.study.utility;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public class SpartanTestbase {

    @BeforeAll
    public static void  init(){
        baseURI="http://34.192.175.227:8000";
        basePath="/api";
    }
    @AfterAll
    public static void destroy() {
        reset();

    }
}

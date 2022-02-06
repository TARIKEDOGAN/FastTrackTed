package com.study.day01;

import com.study.utility.SpartanTestbase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class P04_MethodChaining extends SpartanTestbase {
    //Create a SpartanTestbase instead of keep typing BeforeAll and AfterAll

    @Test
    public void MethodChaining() {

        String word = "apple";
        System.out.println(word.toUpperCase().toLowerCase().substring(2).replace("e", "t"));

    }
    /**
     *1- Given accept type is Json
     *2- Query Parameters values are
     *     - gender —> Female
     *     - nameContains —> e
     *3- When user sends GET request to /spartans/search
     *4- Print out Followings
     *     - Total Element Number
     *     - Get me first spartan name
     *     - Get me second spartan id
     *     - Get me last spartan name
     *     - Get me all Spartan Names

     * 5- Verify followings
     *     - Status code should be 200
     */
    @Test
    public void queryparam (){

        Map<String,String> queryMap=new HashMap<>();
        queryMap.put("nameContains","e");
        queryMap.put("gender","Female");


        Response response = given()
                .accept(ContentType.JSON)
                .log().uri()
                //.queryParam("nameContains", "e")
                //.queryParam("gender", "Female").
                .queryParams(queryMap).
                        when()
                .get("/spartans/search").prettyPeek();





        //  - Total Element Number

        System.out.println(response.path("totalElement").toString());


        //  - Get me first spartan name

        System.out.println(response.path("content.name").toString());

        System.out.println(response.path("content.name[0]").toString());
        System.out.println(response.path("content[0].name").toString());

        System.out.println(response.path("content.id[1]").toString());

        System.out.println(response.path("content.name[-1]").toString());


        System.out.println("PRINT OUT LAST 4");

        System.out.println(response.path("content.name[-4]").toString());

        //Get me all spartan names

        List<String> allnames =response.path("content.name");
        System.out.println("allnames = " + allnames);


    }
    /**
     *1- Given accept type is Json
     *2- Path Parameters values are
     *     - id —> 5
     *3- When user sends GET request to /spartans/{id}
     *5- Verify followings
     *     - Status code should be 200
     *     - Content Type is application/json
     *     - ID is 5
     *     - Name is "Blythe"
     *     - gender is "Female"
     *     - phone is 3677539542
     *
     */
    @Test
    public void pathParams(){

    }


}
package com.study.day02;

import com.study.utility.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class P03_JsonPath extends HrTestBase {

   /*
    Given
             accept type is application/json
     When
             user sends get request to /locaitons
     Then
             response status code must be 200
             content type equals to application/json
             get the second city with JsonPath
             get the last city with JsonPath
             get all country ids
             get all city where their country id is UK

  */
   @Test
    public  void  getLocations(){

       Response response = given().
               accept(ContentType.JSON)
               .log().uri().
                when()
                .get("/locations").prettyPeek();

       JsonPath jP = response.jsonPath();

      /* JsonPath jsonPath = given().accept(ContentType.JSON).
               log().uri().
               when()
              .get("/locations").prettyPeek().jsonPath();

        */

       Assertions.assertEquals(200,response.statusCode());
       Assertions.assertEquals("application/json",response.contentType());
       System.out.println(response.contentType());

       System.out.println("jP.getString(\"items[1].city\") = " + jP.getString("items[1].city"));
       System.out.println("jP.getString(\"items[-1].city\") = " + jP.getString("items[-1].city"));

       System.out.println("jP.getString(\"items.country_id\") = " + jP.getString("items.country_id"));

       List<String> CountryIds = jP.getList("items.country_id");

           System.out.println("countryIds = " + CountryIds);


     // get all city where their country id is UK

    List<String> allCityNamesUk = jP.getList("items.findAll{it.country_id=='UK'}.city" );
     System.out.println("allCityNamesUk = " + allCityNamesUk);




      }
          /*
    Given
             accept type is application/json
     When
             user sends get request to /employees
     Then
             response status code must be 200
            get me all employees first_name who is making salary more than 15000

  */


    @Test
    public void getAllEmployees(){

        Response response = given().accept(ContentType.JSON)
                .log().uri().
              when()
                .get("/employees").prettyPeek();

        JsonPath jp1 = response.jsonPath();



        Assertions.assertEquals(200,response.statusCode());

        System.out.println(response.statusCode());

        List<String> AllNames = jp1.getList("items.findAll {it.salary>15000}.first_name");


        System.out.println("AllNames = " + AllNames);



    }

   }



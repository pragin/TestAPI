package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.Request;

public class JsonPlaceHolder {
    //Base URL
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    private static Response response;
    RequestSpecification request;

    //This method gets the response for all the endpoints
    public Response getResponse(String endPoint) {
        RestAssured.baseURI = BASE_URL;
        request = RestAssured.given();
        response = request.get(endPoint);

       return response;
    }

    //To test the endpoint https://jsonplaceholder.typicode.com/todos/29
    @Test
    public void getTodo() {
        Response todo = getResponse("todos/29");
        //Verify status code is 200
        Assert.assertEquals(200, todo.getStatusCode());
        //Verify the title
        Assert.assertEquals("laborum aut in quam", todo.jsonPath().get("title"));
        //Verify completed=false
        Assert.assertEquals(false, todo.jsonPath().<Boolean>get("completed"));
    }

    //To test the endpoint https://jsonplaceholder.typicode.com/users/5
    @Test
    public void getUser(){
        Response user = getResponse("users/5");
        //Verify status code is 200
        Assert.assertEquals(200, user.getStatusCode());
        //Verify the name
        Assert.assertEquals("Chelsey Dietrich", user.jsonPath().get("name"));
        //verify the street
        Assert.assertEquals("Skiles Walks", user.jsonPath().get("address.street"));
        //Verify the lat
        Assert.assertEquals("-31.8129", user.jsonPath().get("address.geo.lat"));
        //Verify catchPhrase
        Assert.assertEquals("User-centric fault-tolerant solution",user.jsonPath().get("company.catchPhrase"));
    }

    //To test the end point t https://jsonplaceholder.typicode.com/posts/15 
    @Test
    public void getPost(){
        Response post = getResponse("posts/15");
        //Verify user id
        Assert.assertEquals(2,post.jsonPath().get("userId"));
        //Verify title
        Assert.assertEquals("eveniet quod temporibus", post.jsonPath().get("title"));
    }

}

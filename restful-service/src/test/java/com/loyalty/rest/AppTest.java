package com.loyalty.rest;
import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

 


/**
 * Unit test for simple App.
 */

public class AppTest extends JerseyTest
{
    @Override
    public Application configure(){
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
    return new ResourceConfig(App.class);
    }
    @Test
    public void testGetSubmission()
    {
         Response response = target("/usersubmissions").request().get();
         assertEquals("should return status 200", 200, response.getStatus());
    }
    @Test
    public void testPostSubmission()
    {    String jsonStr="{\"userName\":\"hello nayan\",\"submission\":test,\"submissionId\":null,\"submissionDate\":null}";
    MultivaluedMap<String, String> requestParams =
            new MultivaluedHashMap<>(); 
    requestParams.add("jsonUserPost", jsonStr);
    
    
    Response response = target("/usersubmissions").request().post(Entity.json(jsonStr) );
         assertEquals("should return status 200", 200, response.getStatus());
    }
}

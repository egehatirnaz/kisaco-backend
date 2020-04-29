package com.kisaco.application.requests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kisaco.application.urls.URLs;
import com.kisaco.application.urls.URLsRepository;
import com.kisaco.application.users.Users;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@BasePathAwareController // This means that this class is a Controller
@RestController
@RequestMapping(path = "/api/requests")
public class RequestsController {
    @Autowired
    private RequestsRepository requestsRepository;

    @PostMapping(path = "/add") // Map ONLY POST Requests
    public @ResponseBody
    String addNewRequest(
            @RequestParam Integer url_id, @RequestParam String request_ip,
            @RequestParam String request_referrer) {
        Requests new_request = new Requests();
        new_request.setUrlID(url_id);
        new_request.setRequestIP(request_ip);
        new_request.setRequestReferrer(request_referrer);
        new_request.setCreatedAt(System.currentTimeMillis() / 1000L);

        // Obtaining country code.
        HttpGet request = new HttpGet("http://api.ipstack.com/" + request_ip + "?access_key=ff5cc578f42088401b53727c61d7f066");
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // return it as a String
                String country_response = EntityUtils.toString(entity);
                country_response = StringUtils.substringBetween(country_response, "\"country_name\":\"", "\"");
                new_request.setCountryCode(Objects.requireNonNullElse(country_response, "N/A"));
            } else {
                new_request.setCountryCode("N/A");
            }
        } catch (Exception e) {
            new_request.setCountryCode("N/A");
        }

        //String referrer = request.getHeader("referer");
        try {
            new_request = requestsRepository.save(new_request);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Could not register this new request.");
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(new_request);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Encountered a problem.");
        }

    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Requests> getAllRequests() {
        return requestsRepository.findAll();
    }
}
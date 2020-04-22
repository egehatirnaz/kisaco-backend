package com.kisaco.application.analytics;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kisaco.application.requests.Requests;
import com.kisaco.application.requests.RequestsRepository;
import com.kisaco.application.urls.URLs;
import com.kisaco.application.urls.URLsRepository;
import com.kisaco.application.users.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@BasePathAwareController // This means that this class is a Controller
@RequestMapping(path = "/api/analytics")
public class AnalyticsController {

    @Autowired
    private URLsRepository urLsRepository;
    @Autowired
    private RequestsRepository requestsRepository;

    @GetMapping(path = "/{user_id}")
    public @ResponseBody
    List<Analytics> getUserURLs(@PathVariable("user_id") Integer user_id){
        List<Analytics> analyticsList = new ArrayList<>();
        Iterable<URLs> userURLs = urLsRepository.findAllByUserID(user_id);

        for(URLs url: userURLs){
            Iterable<Requests> urlRequests = requestsRepository.findAllByUrlID(url.getId());
            Analytics urlAnalytic = new Analytics(url, urlRequests);
            analyticsList.add(urlAnalytic);
        }

        return analyticsList;

    }
}

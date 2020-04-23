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
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

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

    @GetMapping(path = "/monthly/{url_id}")
    public @ResponseBody
    MonthlyAnalytic getMonthlyURL(@PathVariable("url_id") Integer url_id){
        MonthlyAnalytic monthly = new MonthlyAnalytic();

        URLs url = urLsRepository.findById(url_id).orElse(null);
        if(url == null)
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Could not find this URL.");

        Calendar creationDate = Calendar.getInstance();
        creationDate.setTimeInMillis(url.getCreatedAt()*1000);
        int creationYear = creationDate.get(Calendar.YEAR);

        Iterable<Requests> requests = requestsRepository.findAllByUrlID(url.getId());
        Calendar requestDate = Calendar.getInstance();
        for(Requests req: requests){
            requestDate.setTimeInMillis(req.getCreatedAt()*1000);
            int reqYear = requestDate.get(Calendar.YEAR);
            if ( creationYear == reqYear){
                monthly.addMonth(requestDate.get(Calendar.MONTH));
            }
        }
        return monthly;
    }

    @GetMapping(path = "/visitors/{url_id}")
    public @ResponseBody
    List<Object> getVisitorsURL(@PathVariable("url_id") Integer url_id) {
        URLs url = urLsRepository.findById(url_id).orElse(null);
        if (url == null)
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Could not find this URL.");
        List<Object> result = new ArrayList <Object>();
        Iterable<Requests> requests = requestsRepository.findAllByUrlID(url.getId());
        for(Requests req: requests){
            List<Object> detail = new ArrayList <Object>();
            detail.add(req.getRequestIP());
            detail.add(req.getCountryCode());
            result.add(detail);
        }
        return result;
    }
}

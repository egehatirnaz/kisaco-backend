package com.kisaco.application.requests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.web.bind.annotation.RequestMapping;

@BasePathAwareController // This means that this class is a Controller
@RequestMapping(path = "/api/requests")
public class RequestsController {
    @Autowired
    private RequestsRepository requestsRepository;

}
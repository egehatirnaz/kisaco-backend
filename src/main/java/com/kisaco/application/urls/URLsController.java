package com.kisaco.application.urls;

import com.kisaco.application.util.Randomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;

@BasePathAwareController // This means that this class is a Controller
@RequestMapping(path = "/api/urls")
public class URLsController {
    @Autowired
    private URLsRepository urLsRepository;

    @PostMapping(path = "/create") // Map ONLY POST Requests
    public @ResponseBody
    URLs addNewURL(
            @RequestParam Integer user_id, @RequestParam String orig_url, @RequestParam String short_url,
            @RequestParam long expires_at, @RequestParam int visitor_limit, @RequestParam int private_mode, HttpServletRequest request) {

        boolean unauthorized = true;
        if (user_id != null && user_id > 0)
            unauthorized = false;

        if (orig_url.equals("") || orig_url.trim().equals("")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Original URL cannot be empty.");
        }

        URLs new_url = new URLs();
        if(!unauthorized)
            new_url.setUserID(user_id);
        else
            new_url.setUserID(null);
        new_url.setOrigURL(orig_url);
        new_url.setCreatorIP(request.getRemoteAddr());
        boolean is_private = false;
        if(private_mode == 1 && !unauthorized)
            is_private = true;

        // If no custom URL is provided and mode is public, generate random short URL.
        if (is_private){
            if (!short_url.equals(""))
                throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "You cannot choose short url for private URLs.");
            else {
                short_url = generateURL(12);
                new_url.setShortURL(short_url);
            }
        } else {
            if (!short_url.equals("") && !unauthorized){
                if (short_url.trim().equals("")){
                    short_url = generateURL(5);
                    new_url.setShortURL(short_url);
                } else {
                    if (urLsRepository.findByShortURL(short_url) != null) { // If random is already taken...
                        throw new ResponseStatusException(
                                HttpStatus.BAD_REQUEST, "This URL is already taken.");
                    }
                    new_url.setShortURL(short_url);
                }
            } else {
                short_url = generateURL(5);
                new_url.setShortURL(short_url);
            }
        }

        new_url.setCreatedAt(System.currentTimeMillis() / 1000L);

        if (expires_at == 0 || unauthorized){
            new_url.setExpiresAt((System.currentTimeMillis()/ 1000L) + 86400); // 24 hours from now on.
        } else {
            new_url.setExpiresAt(expires_at);
        }

        new_url.setVisitorCount(0);
        if(!unauthorized)
            new_url.setVisitorLimit(visitor_limit);
        else
            new_url.setVisitorLimit(0);
        new_url.setPrivate(is_private);

        try {
            new_url = urLsRepository.save(new_url);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Could not register this URL.");
        }

        return new_url;
    }


    private String generateURL(int len) {
        String short_url = Randomizer.generateRandomString(len);
        if (urLsRepository.findByShortURL(short_url) != null) { // If random is already taken...
            int attempt = 0;
            while (urLsRepository.findByShortURL(short_url) != null) { // Well, do it until you find one.
                if (attempt == 9) { //Try 10 times, if you dont find one yet, increase the length.
                    len++;
                    attempt = 0;
                }
                short_url = Randomizer.generateRandomString(len);
                attempt++;
            }
        }
        return short_url;
    }
}
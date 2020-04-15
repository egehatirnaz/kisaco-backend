package com.kisaco.application;

import com.kisaco.application.urls.URLs;
import com.kisaco.application.urls.URLsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @Autowired
    private URLsRepository urLsRepository;

    @GetMapping("/{pathVariable}")
    public ResponseEntity<?> redirect(@PathVariable("pathVariable")String link) {
        if(link != null) {
            URLs result = urLsRepository.findByShortURL(link);
            if (result != null) {
                if (result.getExpiresAt() > System.currentTimeMillis() / 1000L) {
                    if (result.getVisitorLimit() == 0) {
                        String orig_url = result.getOrigURL();
                        if (!orig_url.startsWith("http://") && !orig_url.startsWith("https://"))
                            orig_url = "http://" + orig_url;
                        HttpHeaders headers = new HttpHeaders();
                        headers.add("Location", orig_url);
                        return new ResponseEntity<String>(headers,HttpStatus.PERMANENT_REDIRECT);
                    } else {
                        if (result.getVisitorLimit() > result.getVisitorCount()) {
                            String orig_url = result.getOrigURL();
                            if (!orig_url.startsWith("http://") || !orig_url.startsWith("https://"))
                                orig_url = "http://" + orig_url;
                            HttpHeaders headers = new HttpHeaders();
                            headers.add("Location", orig_url);
                            return new ResponseEntity<String>(headers,HttpStatus.PERMANENT_REDIRECT);
                        } else {
                            return new ResponseEntity<>("Link has reached the visitor limit.", HttpStatus.NOT_FOUND);
                        }
                    }
                } else {
                    return new ResponseEntity<>("Link has been expired.", HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>("Link does not exist.", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Hello.", HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> hello() {
        return new ResponseEntity<>("API is running.", HttpStatus.OK);
    }
}

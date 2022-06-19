package com.tedtalk.controller;

import com.tedtalk.domain.vo.TedTalkVO;
import com.tedtalk.exception.RequestConsistencyException;
import com.tedtalk.service.TedTalkManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.commons.lang.StringUtils;

@RestController
@RequestMapping("/v1/tedtalks")
public class TedTalksController {

    @Autowired
    private TedTalkManager tedTalkManager;

    @PostMapping
    public ResponseEntity storeTedTalk(@RequestBody TedTalkVO tedTalk) {
        try {
            TedTalkVO tedTalkVO = tedTalkManager.insertTedTalk(tedTalk);
            return ResponseEntity.ok(tedTalkVO);
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //handle exception and return meaning full response
        }
    }

    @PutMapping
    public ResponseEntity updateTedTalk(@RequestBody TedTalkVO tedTalk) {
        try {
            TedTalkVO tedTalkVO = tedTalkManager.updateTedTalk(tedTalk);
            return ResponseEntity.ok(tedTalk);
        } catch(RequestConsistencyException e) {
            return ResponseEntity.notFound().build();  //handle exception and return meaning full response
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //handle exception and return meaning full response
        }
    }

    @GetMapping("/all")
    public ResponseEntity getAllTedTalks(@RequestParam(value = "author", required = false) String author,
                                         @RequestParam(value = "title", required = false) String title,
                                         @RequestParam(value = "views", required = false) Long views,
                                         @RequestParam(value = "likes", required = false) Long likes) {
        try {
            List<TedTalkVO> tedTalks =
                    tedTalkManager.getALlTedTalks(getFilters(author, title, views, likes));
            return ResponseEntity.ok(tedTalks);
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //handle exception and return meaning full response
        }
    }


    @GetMapping("/{tedTalkId}")
    public ResponseEntity getTedTalk(@PathVariable long tedTalkId) {
        try {
            Optional<TedTalkVO> tedTalk = tedTalkManager.getTedTalk(tedTalkId);
            return tedTalk.isPresent()
                    ? ResponseEntity.ok(tedTalk.get())
                    : ResponseEntity.notFound().build();
        } catch(RequestConsistencyException e) {
            return ResponseEntity.notFound().build();  //handle exception and return meaning full response
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //handle exception and return meaning full response
        }
    }

    @DeleteMapping("/{tedTalkId}")
    public ResponseEntity deleteTedTalk(@PathVariable long tedTalkId) {
        try {
            boolean isDeleted = tedTalkManager.deleteTedTalk(tedTalkId);
            return isDeleted
                    ? ResponseEntity.ok().build()
                    : ResponseEntity.notFound().build();
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //handle exception and return meaning full response
        }
    }

    private Map<String, Object> getFilters(String author, String title,
                                           Long views, Long likes) {
        Map<String, Object> filters = new HashMap<>();
        if(!StringUtils.isBlank(author))
            filters.put("author", author);

        if(!StringUtils.isBlank(title))
            filters.put("title", title);

        if(views != null)
            filters.put("views", views);

        if(likes != null)
            filters.put("likes", likes);

        return filters;
    }
}

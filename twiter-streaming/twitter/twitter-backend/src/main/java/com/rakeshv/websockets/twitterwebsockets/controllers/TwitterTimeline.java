package com.rakeshv.websockets.twitterwebsockets.controllers;

import com.rakeshv.websockets.twitterwebsockets.service.TwitterService;
import com.rakeshv.websockets.twitterwebsockets.service.TwitterStreamConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import twitter4j.Status;
import twitter4j.TwitterException;

import java.util.List;

@RestController
@RequestMapping("/twitter")
@Slf4j
public class TwitterTimeline {
    @Autowired
    TwitterService twitterService;
    @Autowired
    TwitterStreamConfig twitterStreamConfig;

    @GetMapping("/timeline")
    public List<String> getTimeline() throws TwitterException {
        return twitterService.getTimeline();
    }

    @GetMapping("/search/{name}")
    public List<String> searchTweets(@PathVariable("name") String name) throws TwitterException {
        return twitterService.searchTweets(name);
    }

    @GetMapping("/stream/{keyword}")
    public void setupNewStream(@PathVariable("keyword") String keyword) {
        twitterStreamConfig.setupNewStream(keyword);
        log.info("Successfully created new stream");
    }
}

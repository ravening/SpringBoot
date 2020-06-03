package com.rakeshv.websockets.twitterwebsockets.service;

import com.rakeshv.websockets.twitterwebsockets.twitter.TwitterListener;
import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import twitter4j.FilterQuery;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

@Configuration
@Slf4j
public class TwitterStreamConfig {
    private TwitterStream twitterStream;
    @Autowired
    TwitterListener twitterListener;

    @PostConstruct
    public void setupTwitterStream() {
        twitterStream = new TwitterStreamFactory().getInstance();

        setupFilterQuery(new String[]{"java"});
    }

    private void setupFilterQuery(String[] keywords) {
        FilterQuery tweetFilterQuery = new FilterQuery();
        tweetFilterQuery.track(keywords);
        tweetFilterQuery.language(new String[]{"en"});
        twitterStream.addListener(twitterListener);
        twitterStream.filter(tweetFilterQuery);
    }
    private void shutdownStream() {
        twitterStream.shutdown();
    }

    public void setupNewStream(String keyword) {
        shutdownStream();
        log.info("Shutting down existing stream");
        twitterStream = new TwitterStreamFactory().getInstance();
        String[] keywords = new String[]{keyword};
        log.info("Creating new stream for keyword {}", keyword);
        setupFilterQuery(keywords);
    }
}

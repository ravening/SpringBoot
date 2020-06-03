package com.rakeshv.websockets.twitterwebsockets.service;

import org.springframework.stereotype.Service;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TwitterService {
    private Twitter twitter;

    @PostConstruct
    public void initTwitter() {
        this.twitter = TwitterFactory.getSingleton();
    }

    public Twitter getTwitterInstance() {
        return twitter;
    }

    public List<String> getTimeline() throws TwitterException {
        List<Status> statusList = this.twitter.getHomeTimeline();
        return statusList.stream()
                .map(Status::getText)
                .collect(Collectors.toList());
    }

    public List<String> getUserTimeline(String userName) throws TwitterException {
        List<Status> statusList = this.twitter.getUserTimeline(userName);
        return statusList.stream()
                .map(Status::getText).collect(Collectors.toList());
    }

    public List<String> searchTweets(String keyword) throws TwitterException {
        Query query = new Query("source: " + keyword);
        QueryResult queryResult = twitter.search(query);
        List<Status> statusList = queryResult.getTweets();
        return statusList.stream()
                .map(tweet -> tweet.getUser().getScreenName() + " : " + tweet.getText())
                .collect(Collectors.toList());
    }
}

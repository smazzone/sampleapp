package com.datadog.ste.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CountDownLatch;


@RestController
public class BasicController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasicController.class);
    private CountDownLatch latch = new CountDownLatch(1);

    @Autowired
    RestTemplate restTemplate;


    public CountDownLatch getLatch() {
        return latch;
    }

    @RequestMapping("/backend")
    public Quote backend() {

        String anime = restTemplate.getForObject("http://colormind.io/list/", String.class);
        LOGGER.info(anime);

        String google = restTemplate.getForObject("https://www.google.com", String.class);
        LOGGER.info(google);


        Value value = new Value();
        value.setId((long) (5 + (Math.random() * ((10 - 5) + 1))));
        //value.setQuote("Ad maiora semper");
        value.setQuote(getRandomQuote());
        Quote quote = new Quote();
        quote.setValue(value);
        quote.setType("success");

        getLatch().countDown();

        return quote;
    }


    private String getRandomQuote() {
        Random rand = new Random();
        String randomElement = "";
        List<String> quoteList = new ArrayList<>();
        quoteList.add("Ad maiora semper");
        quoteList.add("The greatest glory in living lies not in never falling, but in rising every time we fall. - Nelson Mandela");
        quoteList.add("The way to get started is to quit talking and begin doing. - Walt Disney");
        quoteList.add("Your time is limited, so don't waste it living someone else's life. Don't be trapped by dogma - which is living with the results of other people's thinking. - Steve Jobs");
        quoteList.add("The future belongs to those who believe in the beauty of their dreams. - Eleanor Roosevelt");
        quoteList.add("You must be the change you wish to see in the world. - Mahatma Gandhi");
        quoteList.add("Darkness cannot drive out darkness: only light can do that. Hate cannot drive out hate: only love can do that. - Martin Luther King Jr.");
        quoteList.add("Be yourself; everyone else is already taken. - Oscar Wilde");
        quoteList.add("It is during our darkest moments that we must focus to see the light. - Aristotle");
        quoteList.add("“Life is a succession of lessons which must be lived to be understood. - Ralph Waldo Emerson");
        quoteList.add("“In the end, it's not the years in your life that count. It's the life in your years. - Abraham Lincoln");
        quoteList.add("“The greatest glory in living lies not in never falling, but in rising every time we fall. - Nelson Mandela");

        int numberOfElements = quoteList.size();

        for (int i = 0; i < numberOfElements; i++) {
            int randomIndex = rand.nextInt(quoteList.size());
            randomElement = quoteList.get(randomIndex);
            quoteList.remove(randomIndex);
        }

        return randomElement;

    }
}
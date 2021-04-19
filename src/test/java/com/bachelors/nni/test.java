package com.bachelors.nni;

import com.bachelors.nni.business.producers.newsapi.models.NewsApiResponse;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.util.regex.Matcher;

@Log4j2
public class test {

    private static final String URL = "https://newsapi.org/v2/everything" +
            "?q=black AND lion&pageSize=100" +
            "&sortBy=publishedAt" +
            "&apiKey=d7185b744f324f46a9df10b87e556ee6";

    public String CONTENT = "One of the strictest crackdowns worldwide\r\nPhoto by Michele Doying / The Verge\r\nIndia is reportedly moving forward with a sweeping ban on cryptocurrencies. According to Reuters, the countrys legislat… [+15555 chars]";


    @Test
    void test2() {

        Gson g = new Gson();

        RestTemplate rt = new RestTemplate();

        String response = rt.getForObject(URL, String.class);

        NewsApiResponse r = g.fromJson(response, NewsApiResponse.class);

        r.getArticles().forEach((newsApiArticle -> {
            log.info(newsApiArticle.getSource().getId());
            log.info(newsApiArticle.getSource().getName());
            log.info(newsApiArticle.getPublishedAt());
            }));

    }

    @Test
    public void givenUsingApache_whenGeneratingRandomStringBounded_thenCorrect() {

        int numberOfCharacterToGenerate = Integer.parseInt(CONTENT.substring(CONTENT.indexOf("[+")+1, CONTENT.indexOf(" chars]")));

        log.info(CONTENT);
        log.info(numberOfCharacterToGenerate);

        String generatedString = RandomStringUtils.randomAscii(numberOfCharacterToGenerate);

        String generatedContent = CONTENT
                .replaceAll("\r\n", " ")
                .replaceFirst("... \\[\\+[0-9]+ (chars)\\]", Matcher.quoteReplacement(generatedString));

        log.info(generatedContent);
    }
}

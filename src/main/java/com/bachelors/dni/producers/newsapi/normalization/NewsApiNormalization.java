package com.bachelors.dni.producers.newsapi.normalization;

import com.bachelors.dni.producers.newsapi.models.NewsApiArticle;
import com.bachelors.dni.protobuf.NewsArticleProto.Article;
import com.google.protobuf.Timestamp;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;

@Log4j2
public class NewsApiNormalization {

    public static Set<Article> normalizeNewsApi(Set<NewsApiArticle> articles) {

        Set<Article> normalizedArticles = new HashSet<>();

        for (var article : articles) {
            normalizedArticles.add(Article.newBuilder()
                    .setSourceName(article.getSource().getName())
                    .setAuthor(article.getAuthor() == null ? "No Author Specified" : article.getAuthor())
                    .setTitle(article.getTitle())
                    .setDescription(article.getDescription())
                    .setUrl(article.getUrl())
                    .setImageUrl(article.getUrlToImage())
                    .setDatePublish(stringToTimestamp(article.getPublishedAt()) )
                    .setContent(article.getContent() == null ? "No Content Specified" : generateContent(article.getContent()))
                    .build());
        }

        log.info(normalizedArticles);

        return normalizedArticles;
    }

    static String generateContent(String rawContent) {
        try {
            int numberOfCharacterToGenerate = Integer.parseInt(rawContent.substring(rawContent.indexOf("[+") + 1, rawContent.indexOf(" chars]")));
            String generatedString = RandomStringUtils.randomAscii(numberOfCharacterToGenerate);
            return rawContent.replaceAll("\r\n", " ")
                    .replaceFirst("... \\[\\+[0-9]+ (chars)\\]", Matcher.quoteReplacement(generatedString));
        } catch (Exception e) {
            return rawContent;
        }
    }

    static Timestamp stringToTimestamp(String date) {
        return Timestamp.newBuilder()
                .setSeconds(LocalDateTime.parse(date.substring(0, date.length()-1)).getSecond())
                .build();
    }

}

package com.bachelors.nni.business.producers.rss.normalization;

import com.apptastic.rssreader.Item;
import com.apptastic.rssreader.RssReader;
import com.bachelors.nni.database.models.Client;
import com.bachelors.nni.database.models.RssFeed;
import com.bachelors.nni.protobuf.NewsArticleProto.Article;
import com.google.protobuf.Timestamp;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Log4j2
public class RssFeedNormalization {

    private static final RssReader reader = new RssReader();

    private static final String UNSPECIFIED_FIELD = "Field is not specified";

    public static Set<Article> normalizeRssFeeds(Client client) throws IOException {

        Set<Article> normalizedArticles = new HashSet<>();

        for (RssFeed feed: client.getRssFeeds()) {
            Stream<Item> rssFeed = reader.read(feed.getUrl());
            List<Item> articles = rssFeed.collect(Collectors.toList());

            for (Item article : articles) {
                normalizedArticles.add(Article.newBuilder()
                        .setSourceName(feed.getSource())
                        .setAuthor(article.getAuthor().isPresent() ? article.getAuthor().get() : UNSPECIFIED_FIELD)
                        .setTitle(article.getTitle().isPresent() ? article.getTitle().get() : UNSPECIFIED_FIELD)
                        .setDescription(article.getDescription().isPresent() ? article.getDescription().get() : UNSPECIFIED_FIELD)
                        .setUrl(article.getLink().isPresent() ? article.getLink().get() : UNSPECIFIED_FIELD)
                        .setImageUrl(UNSPECIFIED_FIELD)
                        .setDatePublish(article.getPubDateZonedDateTime().isPresent() ? stringToTimestamp(article.getPubDateZonedDateTime().get().toEpochSecond()) : Timestamp.getDefaultInstance())
                        .setContent(UNSPECIFIED_FIELD)
                        .build()
                );
            }
        }

        return normalizedArticles;
    }

    static Timestamp stringToTimestamp(long seconds) {
        return Timestamp.newBuilder()
                .setSeconds(seconds)
                .build();
    }

}

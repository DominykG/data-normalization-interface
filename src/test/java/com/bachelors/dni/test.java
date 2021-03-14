package com.bachelors.dni;

import com.bachelors.dni.protobuf.NewsArticleProto.Article;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Log4j2
public class test {

    public static final String stringThing = "{\n" +
            "    \"status\": \"ok\",\n" +
            "    \"totalResults\": 527,\n" +
            "    \"articles\": [\n" +
            "        {\n" +
            "            \"source\": {\n" +
            "                \"id\": null,\n" +
            "                \"name\": \"Vanguard\"\n" +
            "            },\n" +
            "            \"author\": \"Urowayino Jeremiah\",\n" +
            "            \"title\": \"N’Delta monarch, agitators differ on S’South govs’ demand\",\n" +
            "            \"description\": \"WARRI- PARAMOUNT ruler of Seimbiri Kingdom, Delta State, HM Charles Ayemi-Botu, and a collection of campaigners in Niger-Delta, 21st Century Youths of Niger Delta and Agitators with Conscience, 21ST CYNDAC, weekend, disagreed over South-South governors’ recen…\",\n" +
            "            \"url\": \"https://www.vanguardngr.com/2021/03/ndelta-monarch-agitators-differ-on-ssouth-govs-demand/\",\n" +
            "            \"urlToImage\": \"https://i2.wp.com/www.vanguardngr.com/wp-content/uploads/2012/03/Charles-Ayemi-Botu.jpg?fit=412%2C250&ssl=1\",\n" +
            "            \"publishedAt\": \"2021-03-14T15:17:50Z\",\n" +
            "            \"content\": \"His Majesty, Charles Ayemi-Botu, aka Lion of the Niger.\\r\\nBy Emma Amaize, Regional Editor, South-South\\r\\nWARRI- PARAMOUNT ruler of Seimbiri Kingdom, Delta State, HM Charles Ayemi-Botu, and a collection… [+4106 chars]\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"source\": {\n" +
            "                \"id\": null,\n" +
            "                \"name\": \"Designyoutrust.com\"\n" +
            "            },\n" +
            "            \"author\": \"Dmitry\",\n" +
            "            \"title\": \"Into the Wild with The World Nature Photography Award Winners\",\n" +
            "            \"description\": \"Grand Prize Winner, Gold – Animals in their Habitat. ‘The World Is Going Upside Down’. Bornean orangutan (Pongo pygmaeus), Tanjung Puting National Park, Borneo Thomas Vijayan The inaugural World Nature Photography Awards has delivered a strong collection of i…\",\n" +
            "            \"url\": \"https://designyoutrust.com/2021/03/into-the-wild-with-the-world-nature-photography-award-winners/\",\n" +
            "            \"urlToImage\": \"https://e4p7c9i3.stackpathcdn.com/wp-content/uploads/2021/03/2-30.jpg?iv=164\",\n" +
            "            \"publishedAt\": \"2021-03-14T14:11:26Z\",\n" +
            "            \"content\": \"Grand Prize Winner, Gold – Animals in their Habitat. ‘The World Is Going Upside Down’. Bornean orangutan (Pongo pygmaeus), Tanjung Puting National Park, BorneoThomas Vijayan\\r\\nThe inaugural World Natu… [+3221 chars]\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"source\": {\n" +
            "                \"id\": null,\n" +
            "                \"name\": \"Apkmania.live\"\n" +
            "            },\n" +
            "            \"author\": \"noreply@blogger.com (chathu_ac)\",\n" +
            "            \"title\": \"Creepy Killer Jeff Keyboard Theme v3.2.B\",\n" +
            "            \"description\": \"<!--Overview-->*Install our Creepy Killer Jeff Theme and enjoy fantastic typing!*<!--end--><!--Description-->Creepy Killer Jeff Theme KeyboardInstall our keyboard with Creepy Killer Jeff theme now for free!If you are looking for new keyboard themes …\",\n" +
            "            \"url\": \"https://www.apkmania.live/2020/04/creepy-killer-jeff-keyboard-theme.html\",\n" +
            "            \"urlToImage\": \"https://lh3.googleusercontent.com/WLev8Oz9iyLQmd6rsBGs09ujWVPw7Hl3e1DS33yZqNCgZaDmNSyPQ1Ero-x4ZIbqBNI=s72-c-h450\",\n" +
            "            \"publishedAt\": \"2021-03-14T08:24:00Z\",\n" +
            "            \"content\": null\n" +
            "        },\n" +
            "        {\n" +
            "            \"source\": {\n" +
            "                \"id\": null,\n" +
            "                \"name\": \"Rolling Stone\"\n" +
            "            },\n" +
            "            \"author\": \"Adisa Duke\",\n" +
            "            \"title\": \"‘The First Time’ With Nathalie Emmanuel\",\n" +
            "            \"description\": \"The Game of Thrones actress on her new Audible project The Coldest Case, learning Valyrian, and discovering yoga\",\n" +
            "            \"url\": \"https://www.rollingstone.com/culture/culture-news/the-first-time-nathalie-emmanuel-1141247/\",\n" +
            "            \"urlToImage\": \"https://www.rollingstone.com/wp-content/uploads/2021/03/TFT_NAT-EMMANUEL_BLANK-THUMB.jpg\",\n" +
            "            \"publishedAt\": \"2021-03-13T21:59:48Z\",\n" +
            "            \"content\": \"British actress Nathalie Emmanuel kicked off this latest episode of “The First Time” by sharing how she first got involved with her latest project, Audible’s The Coldest Case. Her agency first reache… [+3979 chars]\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"source\": {\n" +
            "                \"id\": null,\n" +
            "                \"name\": \"NPR\"\n" +
            "            },\n" +
            "            \"author\": \"Ifeanyi Nsofor\",\n" +
            "            \"title\": \"13 Things I Did (And Did Not) Love About 'Coming 2 America': An African Perspective\",\n" +
            "            \"description\": \"Our Nigerian critic wants you to know: We Africans do not see wild animals in our backyards! Women are allowed to own businesses! But I did admire the awesome head-ties — and powerful princesses!\",\n" +
            "            \"url\": \"https://www.npr.org/sections/goatsandsoda/2021/03/13/975208300/13-things-i-did-and-did-not-love-about-coming-2-america-an-african-perspective\",\n" +
            "            \"urlToImage\": \"https://media.npr.org/assets/img/2021/03/11/coming2america-01_wide-c8f688e56b5f7fe651ce403b6e5b7e8f0d273bf4.jpg?s=1400\",\n" +
            "            \"publishedAt\": \"2021-03-13T12:25:47Z\",\n" +
            "            \"content\": \"In the 1990s, I watched Coming to America as a young medical student in Nigeria. I loved Eddie Murphy's character Akeem (a common name in Nigeria) quick wit, rakish charm, carefree and the wealth of … [+4864 chars]\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    @Test
    void test() {
        StringBuilder response = new StringBuilder(stringThing);

        response.delete(0, response.indexOf(": [")+3);
        response.delete(response.indexOf("]\n}"), response.length());

        String[] articles = response.toString().split("},\\n\\s+\\{");

        List<Article> articlesProto = new ArrayList<>();

        for(String article : articles) {
            response.delete(0, response.length());
            response.append(article);
            articlesProto.add(
                    Article.newBuilder()
                            .setSourceName(response.substring(response.indexOf("\"name\": ")+9, response.indexOf("\"\n")))
                            .setAuthor(response.substring(response.indexOf("\"author\": \"")+11, response.indexOf("\",")))
                            .setTitle(response.substring(response.indexOf("\"title\": \"")+10, response.indexOf("\",", response.indexOf("\"title\": \""))))
                            .build());
            log.info("\n" + articlesProto);
            log.info("\n" + response);
        }

        log.info("\n" + Arrays.stream(articles).count());

    }
}

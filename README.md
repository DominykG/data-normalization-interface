[LT]
# Naujienų normalizacijos sąsaja

!Pirma karta leidžiant sąsajas turi būti paleista [Vartotojo užklausų apdorojimo sąsaja](https://github.com/DominykG/news-systematization-system) kuri sukuria visas vartotojo DB lenteles

Sąsajos veikimui reikalingi:
* [POstrgeSQL](https://www.postgresql.org/download/)
* [Java SE Development Kit 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
* [Apache Kafka](https://kafka.apache.org/downloads)
  * Kafka paleidimo instrukcija galima rasti [čia](https://kafka.apache.org/quickstart)


`NniApplication.java` faile aprašyta `scheduleKafkaJobForTopic()` funkcija kuri, [JobRunr](https://github.com/jobrunr/jobrunr) bibliotekos pagalba, sukuria pasikartojantį darbą nustatytu laiku.
Visus darbus galima pamatyti `http://localhost:8000/dashboard` paleidus sąsaja.

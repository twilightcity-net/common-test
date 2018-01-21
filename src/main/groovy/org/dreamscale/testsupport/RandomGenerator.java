/**
 * Copyright 2018 DreamScale, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.dreamscale.testsupport;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import lombok.extern.slf4j.Slf4j;
import org.fluttercode.datafactory.impl.DataFactory;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Originally sourced from https://github.com/BancVue/common-spring-boot/blob/master/src/mainTest/groovy/com/bancvue/boot/testsupport/RandomGenerator.groovy
 */
@Slf4j
public class RandomGenerator {

    static final List<String> STATES = Arrays.asList(
            "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA",
            "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK",
            "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"
    );

    private static Random seedRandom = new Random();
    private Random random;
    private DataFactory df;
    private Lorem lorem = LoremIpsum.getInstance();

    private static long getSeed() {
        return seedRandom.nextLong();
    }

    public RandomGenerator() {
        this(getSeed());
    }

    public RandomGenerator(long seed) {
        log.info("Creating RandomGenerator with seed value of " + seed);
        random = new Random(seed);
        df = new DataFactory();
        df.randomize(random.nextInt());
    }

    public UUID uuid() {
        return UUID.randomUUID();
    }

    public UUID optionalUuid() {
        return shouldBeNull() ? null : uuid();
    }

    public String uuidString() {
        return uuid().toString();
    }

    public String optionalUuidString() {
        return shouldBeNull() ? null : uuidString();
    }

    public long id() {
        return toId(random.nextLong());
    }

    public Long optionalId() {
        return shouldBeNull() ? null : id();
    }

    public int intId() {
        return toId(random.nextInt());
    }

    public Integer optionalIntId() {
        return shouldBeNull() ? null : intId();
    }

    private int toId(int number) {
        if (number < 0) {
            number *= -1;
        } else if (number == 0) {
            number = 1;
        }
        return number;
    }

    private long toId(long number) {
        if (number < 0) {
            number *= -1;
        } else if (number == 0) {
            number = 1;
        }
        return number;
    }

    public int tinyInt() {
        return intBetween(0, 50);
    }

    public Integer optionalTinyInt() {
        return shouldBeNull() ? null : tinyInt();
    }

    public int negativeInt() {
        return intBetween(Integer.MIN_VALUE, -1);
    }

    public Integer optionalNegativeInt() {
        return shouldBeNull() ? null : negativeInt();
    }

    public int positiveInt() {
        return intBetween(1,Integer.MAX_VALUE);
    }

    public Integer optionalPositiveInt() {
        return shouldBeNull() ? null : positiveInt();
    }

    public int intBetween(int min, int max) {
        return df.getNumberBetween(min, max);
    }

    public Integer optionalIntBetween(int min, int max) {
        return shouldBeNull() ? null : intBetween(min, max);
    }

    public int nextInt() {
        return random.nextInt();
    }

    public long nextLong() {
        return random.nextLong();
    }

    public double nextDouble() {
        return random.nextDouble();
    }

    public float nextFloat() {
        return random.nextFloat();
    }

    public float floatBetween(float min, float max) {
        return nextFloat() * (max - min) + min;
    }

    public LocalDate dateInPastDays(int numOfDays) {
        return LocalDate.now().minusDays(intBetween(1, numOfDays));
    }

    public LocalDate optionalDateInPastDays(int numOfDays) {
        return shouldBeNull() ? null : dateInPastDays(numOfDays);
    }

    public LocalDate localDateInFuture() {
        return LocalDate.now().plusDays(intBetween(1, 365));
    }

    public LocalDate optionalLocalDateInFuture() {
        return shouldBeNull() ? null : localDateInFuture();
    }

    public LocalDate localDate() {
        if (coinFlip()) {
            return LocalDate.now().minusDays(intBetween(1, 365));
        } else {
            return LocalDate.now().plusDays(intBetween(1, 365));
        }
    }

    public LocalDate optionalLocalDate() {
        return shouldBeNull() ? null : localDate();
    }

    public java.sql.Date sqlDateInFuture() {
        return java.sql.Date.valueOf(localDateInFuture());
    }

    public java.sql.Date optionalSqlDateInFuture() {
        return shouldBeNull() ? null : sqlDateInFuture();
    }

    public java.sql.Date sqlDate() {
        return java.sql.Date.valueOf(localDate());
    }

    public java.sql.Date optionalSqlDate() {
        return shouldBeNull() ? null : sqlDate();
    }

    public LocalDateTime localDateTimeInFuture() {
        return LocalDateTime.now().plusDays(intBetween(1, 365));
    }

    public LocalDateTime optionalLocalDateTimeInFuture() {
        return shouldBeNull() ? null : localDateTimeInFuture();
    }

    public LocalDateTime localDateTime() {
        if (coinFlip()) {
            return LocalDateTime.now().minusDays(intBetween(1, 365));
        } else {
            return LocalDateTime.now().plusDays(intBetween(1, 365));
        }
    }

    public LocalDateTime optionalLocalDateTime() {
        return shouldBeNull() ? null : localDateTime();
    }

    public Timestamp timeStampInFuture() {
        return Timestamp.valueOf(localDateTimeInFuture());
    }

    public Timestamp optionalTimeStampInFuture() {
        return shouldBeNull() ? null : timeStampInFuture();
    }

    public Timestamp timeStamp() {
        return Timestamp.valueOf(localDateTime());
    }

    public Timestamp optionalTimeStamp() {
        return shouldBeNull() ? null : timeStamp();
    }

    public ZonedDateTime zonedDateTimeInFuture() {
        return ZonedDateTime.now().plusDays(intBetween(1, 365));
    }

    public ZonedDateTime optionalZonedDateTimeInFuture() {
        return shouldBeNull() ? null : zonedDateTimeInFuture();
    }

    public ZonedDateTime zonedDateTime() {
        if (coinFlip()) {
            return ZonedDateTime.now().minusDays(intBetween(1, 365));
        } else {
            return ZonedDateTime.now().plusDays(intBetween(1, 365));
        }
    }

    public ZonedDateTime optionalZonedDateTime() {
        return shouldBeNull() ? null : zonedDateTime();
    }

    /**
     * Returns a string of random characters.
     */
    public String text(int maxLength) {
        // NOTE: we're using getRandomChars instead of getRandomText b/c the random text isn't so random.
        // they basically use a dictionary of words of specific lengths and the number of choices can be
        // very small (e.g. size 10 equates to 2 distinct words)
        int length = intBetween(1, maxLength);
        return df.getRandomChars(length);
    }

    public String optionalText(int length) {
        return shouldBeNull() ? null : text(length);
    }

    public String words(int length) {
        return lorem.getWords(length).substring(0, length).trim();
    }

    public String optionalWords(int length) {
        return shouldBeNull() ? null : words(length);
    }

    public String businessName() {
        return df.getBusinessName();
    }

    public String optionalBusinessName() {
        return shouldBeNull() ? null : businessName();
    }

    private boolean shouldBeNull() {
        return df.chance(5);
    }

    public String phoneNumber() {
        return df.getNumberText(3) + "-" + df.getNumberText(3) + "-" + df.getNumberText(4);
    }

    public String optionalPhoneNumber() {
        return shouldBeNull() ? null : phoneNumber();
    }

    public String numberText(int length) {
        return df.getNumberText(length);
    }

    public String optionalNumberText(int length) {
        return shouldBeNull() ? null : numberText(length);
    }

    public String state() {
        return item(STATES);
    }

    public String optionalState() {
        return shouldBeNull() ? null : state();
    }

    public String address() {
        return df.getAddress();
    }

    public String optionalAddress() {
        return shouldBeNull() ? null : address();
    }

    public String addressLine2() {
        return df.getAddressLine2();
    }

    public String optionalAddressLine2() {
        return shouldBeNull() ? null : addressLine2();
    }

    public String addressLine2(int probability) {
        return df.getAddressLine2(probability);
    }

    public String optionalAddressLine2(int probability) {
        return shouldBeNull() ? null : addressLine2();
    }

    public String city() {
        return df.getCity();
    }

    public String optionalCity() {
        return shouldBeNull() ? null : city();
    }

    public String email() {
        return df.getEmailAddress().replaceAll("\\s+", "_");
    }

    public String optionalEmail() {
        return shouldBeNull() ? null : email();
    }

    public <T> T item(List<T> items) {
        return df.getItem(items);
    }

    public <T> T optionalItem(List<T> items) {
        return shouldBeNull() ? null : item(items);
    }

    public <T> T item(T... items) {
        return df.getItem(Arrays.asList(items));
    }

    public <T> T optionalItem(T... items) {
        return shouldBeNull() ? null : item(items);
    }

    public String name() {
        return df.getName();
    }

    public String optionalName() {
        return shouldBeNull() ? null : name();
    }

    public String firstName() {
        return df.getFirstName();
    }

    public String optionalFirstName() {
        return shouldBeNull() ? null : firstName();
    }

    public String lastName() {
        return df.getLastName();
    }

    public String optionalLastName() {
        return shouldBeNull() ? null : lastName();
    }

    public boolean coinFlip() {
        return df.chance(50);
    }

    public boolean weightedCoinFlip(int probability) {
        return df.chance(probability);
    }

    public LocalDateTime dayOfYear() {
        Date beginningOfYear = new Date(LocalDateTime.now().getYear(), 0, 1);
        Date randomDayOfYear = df.getDate(beginningOfYear, 0, 365);
        Instant instant = Instant.ofEpochMilli(randomDayOfYear.getTime());
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    public Duration duration() {
        int hours = intBetween(0, 2);
        int minutes = intBetween(0, 59);
        int seconds = intBetween(1, 59);
        return Duration.ofHours(hours).plusMinutes(minutes).plusSeconds(seconds);
    }

    public Duration smallDuration() {
        return Duration.ofMinutes(intBetween(1, 30)).plusSeconds(intBetween(0, 59));
    }

    public String filePath() {
        return "/tmp/" + df.getRandomChars(3, 10);
    }

}

package com.harry.winser.api;

import com.harry.winser.api.services.ArticleDtoDateComparator;
import com.harry.winser.api.web.dto.ArticleDto;
import com.harry.winser.api.web.dto.ArticleDtoBuilder;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class ArticleDtoDateComparatorTest {

    private static final Long EARLIEST_UNIX_TIMESTAMP = 796108515L;
    private static final Long LATEST_UNIX_TIMESTAMP = 1126761315L;

    private ArticleDtoDateComparator articleDtoDateComparator = new ArticleDtoDateComparator();
    private ArticleDto earliest;
    private ArticleDto latest;
    private ArticleDto nullDateDto = new ArticleDto();


    @Before
    public void init() {

        Date earliestDate = new Date(EARLIEST_UNIX_TIMESTAMP);
        Date latestDate = new Date(LATEST_UNIX_TIMESTAMP);

        this.earliest = ArticleDtoBuilder.anArticleDto()
                .withDate(earliestDate)
                .build();

        this.latest = ArticleDtoBuilder.anArticleDto()
                .withDate(latestDate)
                .build();
    }

    @Test
    public void shouldReturnNegativeWhenEarlierSet(){

        int result = this.articleDtoDateComparator.compare(this.latest, this.earliest);

        assertThat(result).isNegative();
    }

    @Test
    public void shouldReturnPositiveWhenLatestSet(){

        int result = this.articleDtoDateComparator.compare(this.earliest, this.latest);

        assertThat(result).isPositive();
    }

    @Test
    public void shouldReturnZeroWhenDatesAreEqual(){

        int result = this.articleDtoDateComparator.compare(this.latest, this.latest);

        assertThat(result).isZero();
    }

    @Test
    public void shouldReturnZeroWhenLatestIsNull(){

        int result = this.articleDtoDateComparator.compare(this.earliest, null);

        assertThat(result).isZero();
    }

    @Test
    public void shouldReturnZeroWhenEarliestIsNull(){

        int result = this.articleDtoDateComparator.compare(null, this.latest);

        assertThat(result).isZero();
    }

    @Test
    public void shouldReturnZeroWhenBothNull(){

        int result = this.articleDtoDateComparator.compare(null, null);

        assertThat(result).isZero();
    }

    @Test
    public void shouldReturnZeroWhenEarliestDateNull(){

        int result = this.articleDtoDateComparator.compare(this.nullDateDto, this.latest);

        assertThat(result).isZero();
    }

    @Test
    public void shouldReturnZeroWhenLatestDateNull(){

        int result = this.articleDtoDateComparator.compare(this.earliest, this.nullDateDto);

        assertThat(result).isZero();
    }

    @Test
    public void shouldReturnZeroWhenLatestAndEarliestDatesNull(){

        int result = this.articleDtoDateComparator.compare(this.nullDateDto, this.nullDateDto);

        assertThat(result).isZero();
    }

    @Test
    public void shouldReturnZeroWhenNullLatestAndNullEarliestDate(){

        int result = this.articleDtoDateComparator.compare(this.nullDateDto, null);

        assertThat(result).isZero();
    }

    @Test
    public void shouldReturnZeroWhenNullEarliestAndNullLatestDate(){

        int result = this.articleDtoDateComparator.compare(null, this.nullDateDto);

        assertThat(result).isZero();
    }

}

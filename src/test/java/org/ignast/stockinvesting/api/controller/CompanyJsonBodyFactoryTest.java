package org.ignast.stockinvesting.api.controller;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CompanyJsonBodyFactoryTest {

    private CompanyJsonBodyFactory factory = new CompanyJsonBodyFactory();

    @Test
    public void shouldCreateValidJson() {
        assertThat(factory.createAmazon()).isEqualTo(
                "{\"name\":\"Amazon\",\"address\":{\"country\":\"United States\"},\"listings\":[{\"stockExchange\":\"New York Stock Exchange\"}]}");
    }

    @Test
    public void shouldCreateCompanyWithoutName() {
        assertThat(factory.createWithNameJsonPair("")).isEqualTo(
                "{\"address\":{\"country\":\"United States\"},\"listings\":[{\"stockExchange\":\"New York Stock Exchange\"}]}");
    }

    @Test
    public void shouldCreateCompanyWithCustomNameJsonPair() {
        assertThat(factory.createWithNameJsonPair("\"name\":null")).isEqualTo(
                "{\"name\":null,\"address\":{\"country\":\"United States\"},\"listings\":[{\"stockExchange\":\"New York Stock Exchange\"}]}");
    }

    @Test
    public void shouldCreateCompanyWithoutAddress() {
        assertThat(factory.createWithAddressJsonPair(""))
                .isEqualTo("{\"name\":\"Amazon\",\"listings\":[{\"stockExchange\":\"New York Stock Exchange\"}]}");
    }

    @Test
    public void shouldCreateCompanyWithCustomAddressJsonPair() {
        assertThat(factory.createWithAddressJsonPair("\"address\":null")).isEqualTo(
                "{\"name\":\"Amazon\",\"address\":null,\"listings\":[{\"stockExchange\":\"New York Stock Exchange\"}]}");
    }

    @Test
    public void shouldCreateCompanyWithoutCountry() {
        assertThat(factory.createWithCountryJsonPair("")).isEqualTo(
                "{\"name\":\"Amazon\",\"address\":{},\"listings\":[{\"stockExchange\":\"New York Stock Exchange\"}]}");
    }

    @Test
    public void shouldCreateCompanyWithCustomCountryJsonPair() {
        assertThat(factory.createWithCountryJsonPair("\"country\":null")).isEqualTo(
                "{\"name\":\"Amazon\",\"address\":{\"country\":null},\"listings\":[{\"stockExchange\":\"New York Stock Exchange\"}]}");
    }

    @Test
    public void shouldCreateCompanyWithoutListingsField() {
        assertThat(factory.createWithListingsJsonPair(""))
                .isEqualTo("{\"name\":\"Amazon\",\"address\":{\"country\":\"United States\"}}");
    }

    @Test
    public void shouldCreateCompanyWithCustomListingsJsonPair() {
        assertThat(factory.createWithListingsJsonPair("\"listings\":null"))
                .isEqualTo("{\"name\":\"Amazon\",\"address\":{\"country\":\"United States\"},\"listings\":null}");
    }

    @Test
    public void shouldCreateListedCompanyWithoutStockExchangeField() {
        assertThat(factory.createWithStockExchangeJsonPair(""))
                .isEqualTo("{\"name\":\"Amazon\",\"address\":{\"country\":\"United States\"},\"listings\":[{}]}");
    }

    @Test
    public void shouldCreateListedCompanyWithCustomStockExchangeField() {
        assertThat(factory.createWithStockExchangeJsonPair("\"stockExchange\":\"London Stock Exchange\"")).isEqualTo(
                "{\"name\":\"Amazon\",\"address\":{\"country\":\"United States\"},\"listings\":[{\"stockExchange\":\"London Stock Exchange\"}]}");
    }
}
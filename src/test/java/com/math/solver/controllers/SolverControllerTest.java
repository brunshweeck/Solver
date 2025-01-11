package com.math.solver.controllers;

import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Locale;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


class SolverControllerTest {

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:8080/";
    }

    @AfterEach
    void tearDown() {
        //
    }

    @Test
    public void divideFiniteStrictlyPositiveByZeroTest() {
        Assertions.assertThat(
                RestAssured.given().when().get("/solve?lhs=99.0&rhs=0.0").then().statusCode(200)
                        .extract().as(Double.class)
        ).isEqualTo(Double.POSITIVE_INFINITY);
    }

    @Test
    public void divideFiniteStrictlyNegativeByZeroTest() {
        Assertions.assertThat(
                RestAssured.given().when().get("/solve?lhs=-99.0&rhs=0.0").then().statusCode(200)
                        .extract().as(Double.class)
        ).isEqualTo(Double.NEGATIVE_INFINITY);
    }

    @Test
    public void dividePositiveInfinityByZeroTest() {
        Assertions.assertThat(
                RestAssured.given().when().get("/solve?lhs=Infinity&rhs=0.0").then().statusCode(200)
                        .extract().as(Double.class)
        ).isEqualTo(Double.POSITIVE_INFINITY);

    }

    @Test
    public void divideNegativeInfinityByZeroTest() {
        Assertions.assertThat(
                RestAssured.given().when().get("/solve?lhs=-Infinity&rhs=0.0").then().statusCode(200)
                        .extract().as(Double.class)
        ).isEqualTo(Double.NEGATIVE_INFINITY);
    }

    @Test
    public void divideNaNByZeroTest() {
        Assertions.assertThat(
                RestAssured.given().when().get("/solve?lhs=NaN&rhs=0.0").then().statusCode(200)
                        .extract().as(Double.class)
        ).isNaN();
    }

    @Test
    public void divideZeroByZeroTest() {
        Assertions.assertThat(
                RestAssured.given().when().get("/solve?lhs=0.0&rhs=0.0").then().statusCode(200)
                        .extract().as(Double.class)
        ).isNaN();
    }

    @Test
    public void divideByOneTest() {
        RestAssured.given().when().get("/solve?lhs=99.0&rhs=1.0").then().statusCode(200)
                .body(Matchers.equalTo("99.0"));
        RestAssured.given().when().get("/solve?lhs=-99.0&rhs=1.0").then().statusCode(200)
                .body(Matchers.equalTo("-99.0"));
    }

    @Test
    public void divideFinalNonZeroByItself() {
        RestAssured.given().when().get("/solve?lhs=99.0&rhs=99.0").then().statusCode(200)
                .body(Matchers.equalTo("1.0"));
    }

    @Test
    public void divideFinalNonZeroByNegationTest() {
        RestAssured.given().when().get("/solve?lhs=99.0&rhs=-99.0").then().statusCode(200)
                .body(Matchers.equalTo("-1.0"));

    }

    @Test
    public void divideOthersByOthersTest() {
        RestAssured.given().when().get("/solve?lhs=45.0&rhs=5.0").then().statusCode(200)
                .body(Matchers.equalTo("9.0"));
    }
}
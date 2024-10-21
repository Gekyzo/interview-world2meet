package com.excelia.spaceships.infrastructure.out.web;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApiResponseConfig {

    // 2xx Success
    public static final String RESPONSE_200_CODE = "200";
    public static final String RESPONSE_200_MESSAGE = "Successful request";
    public static final String RESPONSE_201_CODE = "201";
    public static final String RESPONSE_201_MESSAGE = "Fulfilled request and resource created";
    public static final String RESPONSE_202_CODE = "202";
    public static final String RESPONSE_202_MESSAGE = "Accepted request";
    public static final String RESPONSE_204_CODE = "204";
    public static final String RESPONSE_204_MESSAGE = "Fulfilled request with no additional content";

    // 4xx Client Errors
    public static final String RESPONSE_400_CODE = "400";
    public static final String RESPONSE_400_MESSAGE = "Server cannot process the request. Check for malformed syntax, invalid parameters, or missing required fields";
    public static final String RESPONSE_404_CODE = "404";
    public static final String RESPONSE_404_MESSAGE = "Requested resource could not be found";
    public static final String RESPONSE_409_CODE = "409";
    public static final String RESPONSE_409_MESSAGE = "Requested resource already exists";

}

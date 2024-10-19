package com.excelia.spaceships.shared;

import static com.excelia.spaceships.shared.MessageUtils.getMessageSource;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogNegativeSpaceshipIdAspect {

    private static final String MESSAGE_KEY = "errors.spaceship.negativeid";

    @Before("execution(* com.excelia.spaceships.shared.FirstFilter.doFilter(..))")
    public void aroundDoFilter(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        HttpServletRequest request = (HttpServletRequest) args[0];

        checkNegativeSpaceshipIdHasBeenRequested(request)
            .ifPresent(value -> log.info(getMessageSource(MESSAGE_KEY, new Object[]{value})));
    }

    private Optional<String> checkNegativeSpaceshipIdHasBeenRequested(HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        Pattern pattern = Pattern.compile("/spaceships/(.*)");
        Matcher matcher = pattern.matcher(requestUri);

        if (HttpMethod.GET.matches(request.getMethod()) && matcher.matches()) {
            return Optional.of(matcher.group(1));
        }

        return Optional.empty();
    }

}

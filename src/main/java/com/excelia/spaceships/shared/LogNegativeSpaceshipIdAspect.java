package com.excelia.spaceships.shared;

import static com.excelia.spaceships.shared.MessageUtils.getMessageSource;

import jakarta.servlet.http.HttpServletRequest;
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
    private static final Pattern NEGATIVE_SPACESHIP_ID_PATTERN = Pattern.compile("/spaceships/(-\\d+)");

    @Before("execution(* com.excelia.spaceships.shared.FirstFilter.doFilter(..))")
    public void aroundDoFilter(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        HttpServletRequest request = (HttpServletRequest) args[0];
        Matcher matcher = NEGATIVE_SPACESHIP_ID_PATTERN.matcher(request.getRequestURI());

        if (!HttpMethod.GET.matches(request.getMethod())) {
            return;
        }

        if (!matcher.matches()) {
            return;
        }

        int requestedSpaceshipId = Integer.parseInt(matcher.group(1));
        if (requestedSpaceshipId < 0) {
            log.info(getMessageSource(MESSAGE_KEY, new Object[]{requestedSpaceshipId}));
        }
    }

}

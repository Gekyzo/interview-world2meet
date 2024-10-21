package com.excelia.spaceships.infrastructure.out.web;

import static com.excelia.spaceships.utils.messaging.MessageUtils.getMessageSource;

import com.excelia.spaceships.application.exceptions.SpaceshipNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@ControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {

    private static final String PROBLEM_MESSAGE = "errors.problem.message";

    @ExceptionHandler(SpaceshipNotFoundException.class)
    public ProblemDetail spaceshipNotFoundExceptionHandler(HttpServletRequest request, Exception ex) {
        logProblem(request, ex);
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler({
        HttpMessageNotReadableException.class, // Wrong parameter type in request body
        MethodArgumentTypeMismatchException.class, // Wrong type in path variable
    })
    public ProblemDetail notReadableRequestContent(HttpServletRequest request, Exception ex) {
        logProblem(request, ex);
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail fallbackExceptionHandler(HttpServletRequest request, Exception ex) {
        logProblem(request, ex);
        return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    private static void logProblem(HttpServletRequest request, Exception ex) {
        Object[] values = new Object[]{ex.getMessage(), request.getMethod(), request.getRequestURI()};
        log.warn(getMessageSource(PROBLEM_MESSAGE, values));
    }

}
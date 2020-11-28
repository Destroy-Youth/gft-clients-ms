package dev.gft.example.clients.aspects;

import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import dev.gft.example.clients.dtos.ErrorDTO;
import dev.gft.example.clients.utils.constants.Messages;
import feign.FeignException;

@Aspect
@Configuration
public class RequestValidatorAspect {

    static final Logger LOG = LogManager.getLogger(RequestValidatorAspect.class);

    @Around(value = "execution(* dev.gft.example.clients.controllers.*.*(..))  && args(..)")
    public ResponseEntity execute(ProceedingJoinPoint joinPoint) {

        ResponseEntity result;

        try {

            LOG.info("Access");
            LOG.info(String.format("Execution: %s", joinPoint.getSignature()));
            result = (ResponseEntity) joinPoint.proceed();
            return result;

        } catch (NoSuchElementException | FeignException.NotFound e) {

            errorLog(e, joinPoint);
            ErrorDTO error = new ErrorDTO();
            error.setCode(HttpStatus.NOT_FOUND.value());
            error.setMessage(Messages.NOT_FOUND);
            result = ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            return result;

        } catch (IllegalArgumentException e) {

            errorLog(e, joinPoint);
            ErrorDTO error = new ErrorDTO();
            error.setCode(HttpStatus.BAD_REQUEST.value());
            error.setMessage(Messages.BAD_PARAM);
            result = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
            return result;

        } catch (Throwable e) {

            errorLog(e, joinPoint);

            ErrorDTO error = new ErrorDTO();
            error.setCode(HttpStatus.NOT_FOUND.value());
            error.setMessage(e.getMessage());
            result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
            return result;

        }

    }

    private void errorLog(Throwable e, ProceedingJoinPoint joinPoint) {
        LOG.info("Exception Ocurred");
        LOG.info("Execution: {}", joinPoint.getSignature());
        LOG.info("Exception: {}", e.getMessage());
    }
}

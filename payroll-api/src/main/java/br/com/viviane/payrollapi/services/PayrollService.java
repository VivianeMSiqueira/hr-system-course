package br.com.viviane.payrollapi.services;

import br.com.viviane.payrollapi.domain.Payroll;
import br.com.viviane.payrollapi.feignClients.UserFeign;
import br.com.viviane.payrollapi.services.exceptions.ObjectNotFoundException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Slf4j
@Service
public class PayrollService {

    private final Environment env;
    private final UserFeign feign;

    public Payroll getPayment(Long workerId, Payroll payment){
        log.info("PAYROL_SERVICE ::: Get request on " + env.getProperty("local.server.port") + " port");
        try {
            var user = feign.findById(workerId).getBody();
            if(Objects.nonNull(user)){
                return new Payroll(
                        user.getName(),
                        payment.getDescription(),
                        user.getHourlyPrice(),
                        payment.getWorkedHours(),
                        user.getHourlyPrice() + payment.getWorkedHours()
                );
            }
        }catch (FeignException.NotFound ex){
            throw new ObjectNotFoundException("Object not found");
        }catch (Exception ex){
            throw new IllegalArgumentException("Illegal argument exception");
        }
        return null;
    }
}

package br.com.viviane.payrollapi.resources;

import br.com.viviane.payrollapi.domain.Payroll;
import br.com.viviane.payrollapi.services.PayrollService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/payments")
public class PayrollResource {
    private final PayrollService service;

    @GetMapping(value = "/{workerId}")
    public ResponseEntity<Payroll> getPayment(@PathVariable Long workerId, @RequestBody Payroll payment){
        return ResponseEntity.ok().body(service.getPayment(workerId, payment));
    }
}

package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class ActiveRequestsController {

    private final RequestTrackingInterceptor requestTrackingInterceptor;

    @Autowired
    public ActiveRequestsController(RequestTrackingInterceptor requestTrackingInterceptor) {
        this.requestTrackingInterceptor = requestTrackingInterceptor;
    }

    @GetMapping("/active-requests")
    public Set<String> getActiveRequests() {
        return requestTrackingInterceptor.getActiveRequests();
    }

    @GetMapping("/ciis")
    public String ciis() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "vips";
    }

    @GetMapping("/vips")
    public String vips() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "ciis";
    }
}

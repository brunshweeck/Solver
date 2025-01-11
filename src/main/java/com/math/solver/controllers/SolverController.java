package com.math.solver.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SolverController {

    @GetMapping(path = "/solve")
    public double solve(@RequestParam(name = "lhs", defaultValue = "0.0") double a, @RequestParam(name = "rhs", defaultValue = "0.0") double b) {
        return a / b;
    }
}

package com.mh.ga.administrative.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
        value = "/administrators",
        produces = "application/vnd.ga-administrative+json;version=1.0"
)
public class AdministratorController {

}

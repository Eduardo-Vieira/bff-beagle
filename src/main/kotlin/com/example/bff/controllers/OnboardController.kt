package com.example.bff.controllers

import com.example.bff.builder.OnboardScreen
import com.example.bff.service.OnboardService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OnboardController(private val onboardService: OnboardService) {

    @GetMapping(path = ["/onboard-screen"])
    fun getOnboardScreen() :OnboardScreen = onboardService.getOnboardScreen()
}
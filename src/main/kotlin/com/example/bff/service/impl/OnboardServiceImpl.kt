package com.example.bff.service.impl

import com.example.bff.builder.OnboardScreen
import com.example.bff.service.OnboardService
import org.springframework.stereotype.Service

@Service
class OnboardServiceImpl: OnboardService {
    override fun getOnboardScreen(): OnboardScreen = OnboardScreen()
}
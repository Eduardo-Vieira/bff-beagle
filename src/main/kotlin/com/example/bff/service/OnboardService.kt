package com.example.bff.service

import com.example.bff.builder.OnboardScreen

interface OnboardService {
    fun getOnboardScreen():OnboardScreen
}
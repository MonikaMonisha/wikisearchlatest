package com.example.daytodayfinalapplication.utils

import io.reactivex.Scheduler

interface PostExecutionThread {
    val scheduler: Scheduler
}

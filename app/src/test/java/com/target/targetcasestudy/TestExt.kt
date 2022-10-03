package com.target.targetcasestudy

import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher


fun TestScope.newUnconfinedTestDispatcher(): TestDispatcher {
    return UnconfinedTestDispatcher(testScheduler)
}


fun TestScope.newStandardTestDispatcher(): TestDispatcher {
    return StandardTestDispatcher(testScheduler)
}
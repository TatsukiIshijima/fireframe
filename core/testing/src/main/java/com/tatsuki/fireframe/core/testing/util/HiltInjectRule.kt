package com.tatsuki.fireframe.core.testing.util

import dagger.hilt.android.testing.HiltAndroidRule
import org.junit.rules.TestWatcher

internal class HiltInjectRule(
    private val rule: HiltAndroidRule
) : TestWatcher() {
    override fun starting(description: org.junit.runner.Description?) {
        super.starting(description)
        rule.inject()
    }
}

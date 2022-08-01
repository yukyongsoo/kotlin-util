package com.yuk.common.stringtemplate

import com.yuk.common.stringtemplate.template.TemplateManager
import com.yuk.common.stringtemplate.template.provider.TemplateProvider
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class TemplateManagerTest {
    @Mock
    lateinit var templateProvider: TemplateProvider

    @InjectMocks
    lateinit var templateManager: TemplateManager

    @Test
    fun a() {
    }
}

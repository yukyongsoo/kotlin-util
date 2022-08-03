package com.yuk.common.stringtemplate

import com.yuk.common.stringtemplate.template.TemplateId
import com.yuk.common.stringtemplate.template.TemplateManager
import com.yuk.common.stringtemplate.template.provider.TemplateProvider
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.anyCollection
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.given

@ExtendWith(MockitoExtension::class)
class TemplateManagerTest {
    @Mock
    lateinit var templateProvider: TemplateProvider

    @InjectMocks
    lateinit var templateManager: TemplateManager

    @Test
    fun get() {
        given(templateProvider.getOrNull(TemplateId("test"))).willReturn(
            TestObjectFactory.getTemplate()
        )

        templateManager.get(TemplateId("test"))
    }

    @Test
    fun getRoots() {
        given(templateProvider.getRoots()).willReturn(
            listOf(TestObjectFactory.getTemplate())
        )

        templateManager.getRoots()
    }

    @Test
    fun getByIds() {
        given(templateProvider.getByIds(anyCollection())).willReturn(
            listOf(TestObjectFactory.getTemplate())
        )

        templateManager.getByIds(listOf(TemplateId("test")))
    }

    @Test
    fun getChild() {
        given(templateProvider.getChild(TemplateId("test"))).willReturn(
            listOf(TestObjectFactory.getTemplate())
        )

        templateManager.getChild(TemplateId("test"))
    }

    @Test
    fun create() {
        templateManager.create(TemplateId("test"), "asdf {{1}}")
    }

    @Test
    fun attachChild() {
        templateManager.attachChild(TemplateId("test"), TemplateId("test2"))
    }

    @Test
    fun detachChild() {
        templateManager.detachChild(TemplateId("test"), TemplateId("test2"))
    }

    @Test
    fun getResult() {
        given(templateProvider.getOrNull(TemplateId("test"))).willReturn(
            TestObjectFactory.getTemplate()
        )

        val result = templateManager.getResult(TemplateId("test"), listOf("asdf"))

        assert(result.toString() == "asdfsdf asdf")
    }
}

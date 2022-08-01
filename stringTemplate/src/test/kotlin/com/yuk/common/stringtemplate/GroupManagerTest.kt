package com.yuk.common.stringtemplate

import com.yuk.common.stringtemplate.group.GroupId
import com.yuk.common.stringtemplate.group.GroupManager
import com.yuk.common.stringtemplate.group.provider.GroupProvider
import com.yuk.common.stringtemplate.template.TemplateId
import com.yuk.common.stringtemplate.template.TemplateManager
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.BDDMockito.`when`
import org.mockito.BDDMockito.doNothing
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any

@ExtendWith(MockitoExtension::class)
class GroupManagerTest {
    @Mock
    lateinit var groupProvider: GroupProvider

    @Mock
    lateinit var templateManager: TemplateManager

    @InjectMocks
    lateinit var groupManager: GroupManager

    @Test
    fun create() {
        doNothing().`when`(groupProvider).create(any(), anyString())

        groupManager.create(GroupId("test"), "aaaa")
    }

    @Test
    fun get() {
        `when`(groupProvider.getOrNull(any())).thenReturn(TestObjectFactory.getGroup())

        groupManager.get(GroupId("test"))
    }

    @Test
    fun delete() {
        doNothing().`when`(groupProvider).delete(any())

        groupManager.delete(GroupId("test"))
    }

    @Test
    fun getAllExceptTemplateId() {
        `when`(groupProvider.getAllExceptTemplateId()).thenReturn(
            listOf(TestObjectFactory.getGroup())
        )

        groupManager.getAllExceptTemplateId()
    }

    @Test
    fun updateDescription() {
        doNothing().`when`(groupProvider).updateDescription(any(), anyString())

        groupManager.updateDescription(GroupId("test"), "asdfasdf")
    }

    @Test
    fun attachTemplate() {
        doNothing().`when`(groupProvider).attachTemplate(any(), any())

        groupManager.attachTemplate(GroupId("test"), TemplateId("test"))
    }

    @Test
    fun detachTemplate() {
        doNothing().`when`(groupProvider).detachTemplate(any(), any())

        groupManager.detachTemplate(GroupId("test"), TemplateId("test"))
    }
}

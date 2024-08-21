package com.yuk.common.stringtemplate.spring

import com.yuk.common.stringtemplate.group.GroupManager
import com.yuk.common.stringtemplate.group.provider.GroupProvider
import com.yuk.common.stringtemplate.group.provider.JPAGroupProvider
import com.yuk.common.stringtemplate.template.TemplateManager
import com.yuk.common.stringtemplate.template.provider.JPATemplateProvider
import com.yuk.common.stringtemplate.template.provider.TemplateProvider
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

@TestConfiguration
class TestConfiguration {
    @Bean
    fun getJPAGroupProvider(): GroupProvider = JPAGroupProvider()

    @Bean
    fun getJPATemplateProvider(): TemplateProvider = JPATemplateProvider()

    @Bean
    fun getTemplateManger(templateProvider: TemplateProvider): TemplateManager = TemplateManager(templateProvider)

    @Bean
    fun getGroupManager(
        groupProvider: GroupProvider,
        templateManager: TemplateManager,
    ): GroupManager = GroupManager(groupProvider, templateManager)
}

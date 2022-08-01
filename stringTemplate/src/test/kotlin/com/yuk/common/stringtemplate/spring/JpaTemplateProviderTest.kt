package com.yuk.common.stringtemplate.spring

import com.yuk.common.stringtemplate.template.TemplateId
import com.yuk.common.stringtemplate.template.provider.TemplateProvider
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.context.annotation.Import

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestConfiguration::class)
class JpaTemplateProviderTest {
    @Autowired
    private lateinit var jpaTemplateProvider: TemplateProvider

    @Autowired
    private lateinit var entityManager: TestEntityManager

    @Test
    fun create() {
        jpaTemplateProvider.create(TemplateId("test"), "test")

        entityManager.flush()
    }

    @Test
    fun getOrNull() {
        create()

        val entity = jpaTemplateProvider.getOrNull(TemplateId("test"))

        assert(entity != null)
    }

    @Test
    fun getByIds() {
        create()

        val list = jpaTemplateProvider.getByIds(listOf(TemplateId("test")))

        assert(list.isNotEmpty())
    }

    @Test
    fun getRoots() {
        create()

        val list = jpaTemplateProvider.getRoots()

        assert(list.isNotEmpty())
    }

    @Test
    fun getChild() {
        createAll()

        val list = jpaTemplateProvider.getChild(TemplateId("test"))

        assert(list.isNotEmpty())
    }

    @Test
    fun attachChild() {
        create()

        jpaTemplateProvider.create(TemplateId("test2"), "test")

        jpaTemplateProvider.attachChild(TemplateId("test"), TemplateId("test2"))

        entityManager.flush()
    }

    @Test
    fun detachChild() {
        createAll()

        jpaTemplateProvider.detachChild(TemplateId("test"), TemplateId("test2"))

        entityManager.flush()
    }

    private fun createAll() {
        jpaTemplateProvider.create(TemplateId("test"), "test")

        jpaTemplateProvider.create(TemplateId("test2"), "test", TemplateId("test"))

        entityManager.flush()
    }
}

package com.yuk.common.stringtemplate.spring

import com.yuk.common.stringtemplate.group.GroupId
import com.yuk.common.stringtemplate.group.provider.GroupProvider
import com.yuk.common.stringtemplate.template.TemplateId
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.context.annotation.Import

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestConfiguration::class)
class JpaGroupProviderTest {
    @Autowired
    private lateinit var jpaGroupProvider: GroupProvider

    @Autowired
    private lateinit var entityManager: TestEntityManager

    @Test
    fun createGroup() {
        jpaGroupProvider.create(GroupId("test"), "test")

        entityManager.flush()
    }

    @Test
    fun deleteGroup() {
        jpaGroupProvider.delete(GroupId("test"))
    }

    @Test
    fun getAllExceptTemplateId() {
        createGroup()

        val list = jpaGroupProvider.getAllExceptTemplateId()

        assert(list.isNotEmpty())
    }

    @Test
    fun getOrNull() {
        createGroup()

        val entity = jpaGroupProvider.getOrNull(GroupId("test"))

        assert(entity != null)
    }

    @Test
    fun updateDescription() {
        createGroup()

        jpaGroupProvider.updateDescription(GroupId("test"), "descr")

        entityManager.flush()
    }

    @Test
    fun attachTemplate() {
        createGroup()

        jpaGroupProvider.attachTemplate(GroupId("test"), TemplateId("test"))

        entityManager.flush()
    }

    @Test
    fun detachTemplate() {
        createGroup()

        jpaGroupProvider.detachTemplate(GroupId("test"), TemplateId("test"))

        entityManager.flush()
    }
}

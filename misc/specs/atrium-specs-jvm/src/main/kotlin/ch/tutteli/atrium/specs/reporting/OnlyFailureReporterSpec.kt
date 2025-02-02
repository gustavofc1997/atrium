package ch.tutteli.atrium.specs.reporting

import ch.tutteli.atrium.api.fluent.en_GB.isEmpty
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.assertions.RootAssertionGroupType
import ch.tutteli.atrium.assertions.builders.root
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.reporting.AssertionFormatterFacade
import ch.tutteli.atrium.reporting.AtriumErrorAdjuster
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator
import ch.tutteli.atrium.specs.AssertionVerb
import ch.tutteli.atrium.specs.describeFun
import ch.tutteli.atrium.translations.DescriptionAnyAssertion.TO_BE
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it

//TODO #116 migrate spek1 to spek2 - move to specs-common
abstract class OnlyFailureReporterSpec(
    testeeFactory: (AssertionFormatterFacade, AtriumErrorAdjuster) -> Reporter,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit) =
        describeFun(describePrefix, funName, body = body)

    val translator = UsingDefaultTranslator()
    val facade = coreFactory.newAssertionFormatterFacade(coreFactory.newAssertionFormatterController())
    facade.register {
        coreFactory.newTextFallbackAssertionFormatter(
            mapOf(RootAssertionGroupType::class to "[]"),
            it,
            coreFactory.newDetailedObjectFormatter(translator), translator
        )
    }
    val testee = testeeFactory(facade, coreFactory.newNoOpAtriumErrorAdjuster())

    describeFun(testee::format.name) {
        val sb = StringBuilder()
        val assertion = object : Assertion {
            override fun holds() = true
        }
        val basicAssertion = ExpectImpl.builder.descriptive.holding.withDescriptionAndRepresentation(TO_BE, 0).build()
        val basicAssertionAnonymous = object : DescriptiveAssertion {
            override val representation = 1
            override val description = AssertionVerb.VERB
            override fun holds() = true
        }

        val assertionGroupAnonymous = object : AssertionGroup {
            override val type = RootAssertionGroupType
            override val description = AssertionVerb.VERB
            override val representation = 0
            override val assertions = listOf(assertion, basicAssertion, basicAssertionAnonymous)
        }
        val assertionGroup = ExpectImpl.builder.root
            .withDescriptionAndRepresentation(AssertionVerb.VERB, 1)
            .withAssertions(listOf(assertion, basicAssertion, basicAssertionAnonymous, assertionGroupAnonymous))
            .build()

        mapOf(
            "object: ${Assertion::class.simpleName}" to assertion,
            "object: ${DescriptiveAssertion::class.simpleName}" to basicAssertionAnonymous,
            "${basicAssertion::class.simpleName}" to basicAssertion,
            "object: ${AssertionGroup::class.simpleName}" to assertionGroupAnonymous,
            "${assertionGroup::class.simpleName}" to assertionGroup
        ).forEach { (typeRepresentation, assertion) ->
            it("does not append anything if $typeRepresentation holds") {
                testee.format(assertion, sb)
                expect(sb).isEmpty()
            }
        }

        context("dependencies") {
            val assertionFormatterFacade = mock<AssertionFormatterFacade>()
            val testeeWithMockedFacade = testeeFactory(
                assertionFormatterFacade, coreFactory.newNoOpAtriumErrorAdjuster()
            )

            it("delegates to ${assertionFormatterFacade::class.java.simpleName}") {
                testeeWithMockedFacade.format(basicAssertion, sb)
                verify(assertionFormatterFacade).format(eq(basicAssertion), eq(sb), any())
            }
        }
    }
})

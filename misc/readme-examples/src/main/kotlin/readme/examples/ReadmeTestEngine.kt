package readme.examples

import ch.tutteli.atrium.reporting.ReporterFactory
import ch.tutteli.niok.absolutePathAsString
import ch.tutteli.niok.exists
import ch.tutteli.niok.readText
import ch.tutteli.niok.writeText
import org.junit.platform.engine.*
import org.junit.platform.engine.support.descriptor.AbstractTestDescriptor
import org.junit.platform.engine.support.hierarchical.EngineExecutionContext
import org.junit.platform.engine.support.hierarchical.Node
import org.spekframework.spek2.junit.*
import org.spekframework.spek2.runtime.SpekRuntime
import org.spekframework.spek2.runtime.execution.ExecutionRequest
import java.lang.reflect.InvocationTargetException
import java.nio.file.Paths
import org.junit.platform.engine.ExecutionRequest as JUnitExecutionRequest

class ReadmeTestEngine : TestEngine {
    private val spek = SpekTestEngine()

    private val outputs = mutableMapOf<String, String>()

    override fun getId(): String = "spek2-readme"

    override fun discover(discoveryRequest: EngineDiscoveryRequest, uniqueId: UniqueId): TestDescriptor {
        return try {
            val descriptor = spek.discover(discoveryRequest, uniqueId)
            require(descriptor.children.isNotEmpty()) {
                "Could not find any specification, check your runtime classpath"
            }
            descriptor
        } catch (t: Throwable) {
            //since the junit gradle platform does not treat an error during discovery as failure,
            // we have to return a fake descriptor which fails
            // TODO check if this changes with https://github.com/junit-team/junit5/issues/1298
            SpekEngineDescriptor(uniqueId, id).apply {
                addChild(DiscoveryFailed(uniqueId, t))
            }
        }
    }

    private class DiscoveryFailed(
        uniqueId: UniqueId,
        private val throwable: Throwable
    ) : AbstractTestDescriptor(uniqueId.append("discovery", "fail"), "discovering specifications"),
        Node<EngineExecutionContext> {

        override fun getType() = TestDescriptor.Type.TEST
        override fun execute(context: EngineExecutionContext?, dynamicTestExecutor: Node.DynamicTestExecutor?) =
            when (throwable) {
                is InvocationTargetException ->
                    throw AssertionError(
                        "InvocationTargetException occurred with targetException:" +
                            "\n ${throwable.targetException}",
                        throwable
                    )
                is ExceptionInInitializerError -> throw AssertionError(
                    "ExceptionInInitializerError occurred with exception:" +
                        "\n ${throwable.exception}",
                    throwable
                )

                else -> throw throwable
            }
    }

    override fun execute(request: JUnitExecutionRequest) {
        ReporterFactory.specifyFactory(ReadmeReporterFactory.ID)

        runSpekWithCustomListener(request)

        try {
            processExamples(request)
        } catch (t: Throwable) {
            request.fail(t)
        }
    }

    private fun processExamples(request: org.junit.platform.engine.ExecutionRequest) {
        val specContent = fileContent("src/main/kotlin/readme/examples/ReadmeSpec.kt", request)
        var readmeContent = fileContent(readmeStringPath, request)

        outputs.forEach { (exampleId, output) ->
            readmeContent = updateExampleInReadme(readmeContent, specContent, exampleId, output, request)
        }
        Paths.get(readmeStringPath).writeText(readmeContent)
    }

    private fun runSpekWithCustomListener(request: org.junit.platform.engine.ExecutionRequest) {
        val roots = request.rootTestDescriptor.children
            .filterIsInstance<SpekTestDescriptor>()
            .map(SpekTestDescriptor::scope)

        val executionListener = ReadmeExecutionListener(
            JUnitEngineExecutionListenerAdapter(request.engineExecutionListener, SpekTestDescriptorFactory()),
            outputs
        )
        val executionRequest = ExecutionRequest(roots, executionListener)
        SpekRuntime().execute(executionRequest)
    }

    private fun fileContent(path: String, request: JUnitExecutionRequest): String {
        val file = Paths.get(path)
        request.failIf(!file.exists) { "could not find ${file.absolutePathAsString}" }
        return file.readText()
    }

    private inline fun JUnitExecutionRequest.failIf(predicate: Boolean, errorMessage: () -> String) {
        if (predicate) fail(errorMessage())
    }

    private fun JUnitExecutionRequest.fail(errorMessage: String) = fail(IllegalStateException(errorMessage))
    private fun JUnitExecutionRequest.fail(throwable: Throwable) {
        engineExecutionListener.executionFinished(
            rootTestDescriptor,
            TestExecutionResult.failed(throwable)
        )
    }

    private fun updateExampleInReadme(
        readmeContent: String,
        specContent: String,
        exampleId: String,
        output: String,
        request: JUnitExecutionRequest
    ): String {
        val (lineNumber, sourceCode) = extractSourceCode(specContent, exampleId, request)
        val exampleTag = Regex("<$exampleId>[\\S\\s]*</$exampleId>")

        return if (!exampleTag.containsMatchIn(readmeContent)) {
            request.fail("test tag <$exampleId> not found in $readmeStringPath")
            readmeContent
        } else {
            exampleTag.replace(readmeContent) {
                """<$exampleId>
                |
                |```kotlin
                |$sourceCode
                |```
                |↑ <sub>[Example](https://github.com/robstoll/atrium/tree/readme/misc/readme-examples/src/main/kotlin/ch/tutteli/atrium/readme/ReadmeSpec.kt#L$lineNumber)</sub> ↓ <sub>Output</sub>
                |```text
                |$output
                |```
                |</$exampleId>
            """.trimMargin()
            }
        }
    }

    private fun extractSourceCode(
        specContent: String,
        exampleId: String,
        request: JUnitExecutionRequest
    ): Pair<Int, String> {
        var lineNumber: Int? = null
        val sb = StringBuilder()

        specContent.lineSequence().forEachIndexed { index, line ->
            if (lineNumber != null) {
                if (line.startsWith("    }")) return lineNumber!! to sb.toString().trimIndent()
                sb.append(line).append("\n")

            } else if (line.trim().startsWith("test(\"$exampleId\")")) {
                lineNumber = index + 1
            }

        }
        request.fail("cannot find source code for $exampleId")
        return -1 to ""
    }

    companion object {
        private const val readmeStringPath = "../../README.md"
    }
}


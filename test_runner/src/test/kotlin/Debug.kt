import ftl.Main
import ftl.util.withGlobalExceptionHandling
import picocli.CommandLine

fun main() {
    // GoogleApiLogger.logAllToStdout()

    // for debugging. Run test from IntelliJ IDEA

    // run "gradle check" to generate required fixtures
    val projectId = System.getenv("GOOGLE_CLOUD_PROJECT")
        ?: "YOUR PROJECT ID"
    val quantity = "single"
    val type = "errorFlaky"

    // Bugsnag keeps the process alive so we must call exitProcess
    // https://github.com/bugsnag/bugsnag-java/issues/151
    withGlobalExceptionHandling {
        CommandLine(Main()).execute(
//            "--debug",
            "firebase", "test",
            "android", "run",
//            "--dry",
            "--ci-junit-result",
            "--ci-junit-result-file=ciTest.xml",
            "-c=src/test/kotlin/ftl/fixtures/test_app_cases/flank-$quantity-$type.yml",
            "--project=$projectId"
//            "--client-details=key1=value1,key2=value2"
        )
    }
}

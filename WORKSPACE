workspace(name = "midityping")

rules_scala_version="74a91254f7e2335496813407e210ac00542cd44e"

rules_scala_version_sha256="ed022fbee43ab9bcafab76b4054bcc92267519f78d7d9e0521be95761e279aaa"

http_archive(
             name = "io_bazel_rules_scala",
             url = "https://github.com/wix/rules_scala/archive/%s.zip"%rules_scala_version,
             type = "zip",
             strip_prefix= "rules_scala-%s" % rules_scala_version,
             sha256 = rules_scala_version_sha256,
)

load("@io_bazel_rules_scala//scala:scala.bzl", "scala_repositories")
scala_repositories()

load("@io_bazel_rules_scala//scala:toolchains.bzl", "scala_register_toolchains")
scala_register_toolchains()

load("@io_bazel_rules_scala//specs2:specs2_junit.bzl","specs2_junit_repositories")
specs2_junit_repositories()

maven_jar(
    name = "org_slf4j__slf4j_api",
    artifact = "org.slf4j:slf4j-api:1.7.25",
)

maven_jar(
    name = "ch_qos_logback__logback_classic",
    artifact = "ch.qos.logback:logback-classic:1.2.3",
)

maven_jar(
    name = "ch_qos_logback__logback_core",
    artifact = "ch.qos.logback:logback-core:1.2.3",
)

maven_jar(
    name = "jetty_server",
    artifact = "org.eclipse.jetty:jetty-server:9.4.12.v20180830",
)

maven_jar(
    name = "jetty_util",
    artifact = "org.eclipse.jetty:jetty-util:9.4.12.v20180830",
)

maven_jar(
    name = "jetty_http",
    artifact = "org.eclipse.jetty:jetty-http:9.4.12.v20180830",
)

maven_jar(
    name = "jetty_io",
    artifact = "org.eclipse.jetty:jetty-io:9.4.12.v20180830",
)

maven_jar(
    name = "jetty_security",
    artifact = "org.eclipse.jetty:jetty-security:9.4.12.v20180830",
)

maven_jar(
    name = "jetty_servlet",
    artifact = "org.eclipse.jetty:jetty-servlet:9.4.12.v20180830",
)

maven_jar(
    name = "jetty_websocket_api",
    artifact = "org.eclipse.jetty.websocket:websocket-api:9.4.12.v20180830",
)

maven_jar(
    name = "jetty_websocket_server",
    artifact = "org.eclipse.jetty.websocket:websocket-server:9.4.12.v20180830",
)

maven_jar(
    name = "jetty_websocket_servlet",
    artifact = "org.eclipse.jetty.websocket:websocket-servlet:9.4.12.v20180830",
)

maven_jar(
    name = "jetty_websocket_common",
    artifact = "org.eclipse.jetty.websocket:websocket-common:9.4.12.v20180830",
)

maven_jar(
    name = "javax_servlet_api",
    artifact = "javax.servlet:javax.servlet-api:4.0.1",
)

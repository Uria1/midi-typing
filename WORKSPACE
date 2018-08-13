
workspace(name = "midityping")

rules_scala_version="74a91254f7e2335496813407e210ac00542cd44e" # update this as needed

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

# register default scala toolchain
load("@io_bazel_rules_scala//scala:toolchains.bzl", "scala_register_toolchains")
scala_register_toolchains()

#load("//3rdparty:workspace.bzl", "maven_dependencies")
#maven_dependencies()

load("@io_bazel_rules_scala//specs2:specs2_junit.bzl","specs2_junit_repositories")
specs2_junit_repositories()

maven_jar(
    name = "com_fasterxml_jackson_core__jackson_databind",
    artifact = "com.fasterxml.jackson.core:jackson-databind:2.9.6",
)
maven_jar(
    name = "com_fasterxml_jackson_core__jackson_annotations",
    artifact = "com.fasterxml.jackson.core:jackson-annotations:2.9.6",
)
maven_jar(
    name = "com_fasterxml_jackson_core__jackson_core",
    artifact = "com.fasterxml.jackson.core:jackson-core:2.9.6",
)
maven_jar(
    name = "com_fasterxml_jackson_module__jackson_module_scala",
    artifact = "com.fasterxml.jackson.module:jackson-module-scala_2.12:2.9.6",
)

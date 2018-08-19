scala_specs2_junit_test(
    name = "mapping",
    size = "small",
    srcs = glob(["*.scala"]),
    deps = [
        "//poc/src/main/scala/org/midityping/poc/mapping",
        "//poc/src/main/scala/org/midityping/poc/mapping/storage",
        "//poc/src/main/scala/org/midityping/poc/midi",
        "//poc/src/main/scala/org/midityping/poc/system",
        "//poc/src/main/scala/org/midityping/poc/common",
        "//poc/src/main/scala/org/midityping/poc/events",
        "//poc/src/main/scala/org/midityping/poc/actions",
        "//poc/src/test/scala/org/midityping/poc/testsupport",
    ],
    resources = ["//poc/src/test/resources:test-deps"],
    visibility = ["//visibility:public"],
    suffixes = ["Test"],
)

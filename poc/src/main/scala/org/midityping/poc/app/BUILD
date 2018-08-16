scala_binary(
    name = "app",
    srcs = glob(["*.scala"]),
    deps = [
        "//poc/src/main/scala/org/midityping/poc/actions",
        "//poc/src/main/scala/org/midityping/poc/mapping",
        "//poc/src/main/scala/org/midityping/poc/mapping/storage",
        "//poc/src/main/scala/org/midityping/poc/midi",
        "//poc/src/main/scala/org/midityping/poc/system",
        "//poc/src/main/scala/org/midityping/poc/common",
        "//poc/src/main/scala/org/midityping/poc/events",
        "//poc/src/main/scala/org/midityping/poc/logging",
        "//poc/src/main/resources:runtime-deps",
        "@org_slf4j__slf4j_api//jar",
        "@org_slf4j__slf4j_log4j12//jar",
        "@log4j__log4j//jar",
    ],
    main_class = "org.midityping.poc.app.MidiTypingApp"
)
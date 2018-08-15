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
    ],
    main_class = "org.midityping.poc.app.MidiTypingApp"
)
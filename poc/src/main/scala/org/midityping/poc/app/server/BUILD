scala_library(
    name = "server",
    srcs = glob(["*.scala"]),
    deps = [
        "@javax_servlet_api//jar",
        "@jetty_server//jar",
        "@jetty_util//jar",
        "@jetty_http//jar",
        "@jetty_io//jar",
        "@jetty_servlet//jar",
        "@jetty_websocket_api//jar",
        "@jetty_websocket_server//jar",
        "@jetty_websocket_servlet//jar",
        "//poc/src/main/scala/org/midityping/poc/app/websocket",
    ],
    visibility = ["//visibility:public"]
)

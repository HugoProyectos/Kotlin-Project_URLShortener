package es.unizar.urlshortener.infrastructure.delivery

/**
 * Get the information of a given User-Agent header
 *
 */
interface UserAgentInfo {
    fun getBrowser(userAgentHeader: String): String
    fun getOS(userAgentHeader: String): String
}

/**
 * Implementation of [UserAgentInfo]
 */
class UserAgentInfoImpl : UserAgentInfo {
    /**
     * Given a [userAgentHeader] returns the browser
     * Source: https://gist.github.com/c0rp-aubakirov/a4349cbd187b33138969
     *
     */
    override fun getBrowser(userAgentHeader: String): String {
        val uaLowCase = userAgentHeader.lowercase()
        var browser = ""
        if (uaLowCase.contains("msie")) {
            val substring = userAgentHeader.substring(userAgentHeader.indexOf("MSIE")).split(";")[0]
            browser = substring.split(" ")[0].replace("MSIE", "IE") + "-" + substring.split(" ")[1]
        } else if (uaLowCase.contains("safari") && uaLowCase.contains("version")) {
            browser = (userAgentHeader.substring(userAgentHeader.indexOf("Safari")).split(" ")[0]).split(
                "/"
            )[0] + "-" + (
                userAgentHeader.substring(
                    userAgentHeader.indexOf("Version")
                ).split(" ")[0]
                ).split("/")[1]
        } else if (uaLowCase.contains("opr") || uaLowCase.contains("opera")) {
            if (uaLowCase.contains("opera")) {
                browser = (userAgentHeader.substring(userAgentHeader.indexOf("Opera")).split(" ")[0]).split(
                    "/"
                )[0] + "-" + (
                    userAgentHeader.substring(
                        userAgentHeader.indexOf("Version")
                    ).split(" ")[0]
                    ).split("/")[1]
            } else if (uaLowCase.contains("opr")) {
                browser = (
                    (userAgentHeader.substring(userAgentHeader.indexOf("OPR")).split(" ")[0]).replace(
                        "/",
                        "-"
                    )
                    ).replace(
                    "OPR",
                    "Opera"
                )
            }
        } else if (uaLowCase.contains("chrome")) {
            browser = (userAgentHeader.substring(userAgentHeader.indexOf("Chrome")).split(" ")[0]).replace("/", "-")
        } else if ((uaLowCase.indexOf("mozilla/7.0") > -1) || (uaLowCase.indexOf("netscape6") != -1) || (
            uaLowCase.indexOf(
                    "mozilla/4.7"
                ) != -1
            ) || (uaLowCase.indexOf("mozilla/4.78") != -1) || (
                uaLowCase.indexOf(
                        "mozilla/4.08"
                    ) != -1
                ) || (uaLowCase.indexOf("mozilla/3") != -1)
        ) {
            // browser=(userAgent.substring(userAgent.indexOf("MSIE")).split(" ")[0]).replace("/", "-");
            browser = "Netscape-?"
        } else if (uaLowCase.contains("firefox")) {
            browser = (userAgentHeader.substring(userAgentHeader.indexOf("Firefox")).split(" ")[0]).replace(
                "/",
                "-"
            )
        } else if (uaLowCase.contains("rv")) {
            browser = "IE"
        } else {
            browser = "UnKnown, More-Info: $userAgentHeader"
        }
        return browser
    }

    /**
     * Given a [userAgentHeader] returns the platform
     *
     */
    override fun getOS(userAgentHeader: String): String {
        val uaLowCase = userAgentHeader.lowercase()
        val os: String = if (uaLowCase.contains("windows")) {
            "Windows"
        } else if (uaLowCase.contains("mac")) {
            "Mac"
        } else if (uaLowCase.contains("x11")) {
            "Unix"
        } else if (uaLowCase.contains("android")) {
            "Android"
        } else if (uaLowCase.contains("iphone")) {
            "IPhone"
        } else {
            "UnKnown, More-Info: $userAgentHeader"
        }
        return os
    }
}

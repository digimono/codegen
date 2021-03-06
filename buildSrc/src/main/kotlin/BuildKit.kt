/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.text.SimpleDateFormat
import java.util.Date

object Build {

    const val buildTimestampFormat = "yyyyMMddHHmmss"
    const val buildSourceEncoding = "UTF-8"
    const val buildResourcesEncoding = "UTF-8"
    const val buildJavadocEncoding = "UTF-8"
    const val reportingOutputEncoding = "UTF-8"
}

object Manifest {

    val buildTime: String
        get() {
            return Date().format()
        }

    val username: String
        get() {
            return System.getProperty("user.name")
        }

    val buildJdk: String
        get() {
            return System.getProperty("java.runtime.version") + " (" + System.getProperty("java.vendor") + ")"
        }

    val buildOS: String
        get() {
            return System.getProperty("os.name") + " " + System.getProperty("os.arch") + " " + System.getProperty("os.version")
        }

    private fun Date.format(format: String = ""): String {
        val sdf = if (format.isNotEmpty()) SimpleDateFormat(format) else SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
        return sdf.format(this)
    }
}

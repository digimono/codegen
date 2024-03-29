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

object Versions {

    // Java
    const val javaVersion = "1.8"

    // Plugins
    const val gradlePluginPublishVersion = "0.15.0"

    // Dependencies
    const val junitVersion = "4.13.2"
    const val junit5Version = "5.7.1"
    const val junitPlatformVersion = "1.7.1"

    const val mybatisGeneratorVersion = "1.4.2"
    const val antVersion = "1.10.10"
    const val h2Version = "1.4.200"
    const val mysqlVersion = "8.0.25"
    const val lombokVersion = "1.18.20"
}

object Libs {

    const val mybatisGeneratorCore = "org.mybatis.generator:mybatis-generator-core:${Versions.mybatisGeneratorVersion}"
    const val ant = "org.apache.ant:ant:${Versions.antVersion}"
    const val h2 = "com.h2database:h2:${Versions.h2Version}"
    const val lombok = "org.projectlombok:lombok:${Versions.lombokVersion}"
    const val mysql = "mysql:mysql-connector-java:${Versions.mysqlVersion}"
}

object TestLibs {

    const val junit = "junit:junit:${Versions.junitVersion}"

    const val junit5Bom = "org.junit:junit-bom:${Versions.junit5Version}"
    const val junitPlatformLauncher = "org.junit.platform:junit-platform-launcher:${Versions.junitPlatformVersion}"
    const val junitJupiterApi = "org.junit.jupiter:junit-jupiter-api:${Versions.junit5Version}"
    const val junitJupiterEngine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junit5Version}"
    const val junitVintageEngine = "org.junit.vintage:junit-vintage-engine:${Versions.junit5Version}"
}

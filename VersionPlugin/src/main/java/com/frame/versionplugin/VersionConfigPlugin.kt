package com.frame.versionplugin

import BuildConfig
import Google
import Testing
import Testing.androidTestImplementation
import Testing.testImplementation
import com.android.build.gradle.*
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.plugins.PluginContainer
import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.internal.AndroidExtensionsExtension
import org.jetbrains.kotlin.gradle.internal.KaptGenerateStubsTask
import org.jetbrains.kotlin.gradle.internal.KaptTask
import org.jetbrains.kotlin.gradle.internal.KaptWithKotlincTask
import org.jetbrains.kotlin.gradle.model.Kapt
import org.jetbrains.kotlin.gradle.plugin.KaptExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinAndroidPluginWrapper
import org.jetbrains.kotlin.gradle.plugin.getKotlinPluginVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import kotlin.jvm.kotlin

/**
 * Title :
 * Author: Lsy
 * Date: 2/3/21 4:34 PM
 * Version:
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
const val api = "api"
const val implementation = "implementation"
const val annotationProcessor = "annotationProcessor"

class VersionConfigPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        //配置项目内模块
        project.plugins.config(project)
        //打印各流程时间
        project.gradle.addListener(TimingsListener())
    }

    /**
     * 配置项目内的模块
     * 根据AppModel或Library,分别进行相应的配置信息
     */
    private fun PluginContainer.config(project: Project) {
        whenPluginAdded {
            when (this) {
                is AppPlugin -> {
                    //公共插件
                    project.configCommonPlugin()
                    //公共 android 配置项
                    project.extensions.getByType<AppExtension>().applyAppCommons(project)
                    //公共依赖
                    project.configAppDependencies()
                }
                is LibraryPlugin -> {
                    project.configCommonPlugin()
                    //公共 android 配置项
                    project.extensions.getByType<LibraryExtension>().applyLibraryCommons(project)
                    //公共依赖
                    project.configLibraryDependencies()
                }
                is KotlinAndroidPluginWrapper -> {
                    //根据 project build.gradle.kts 中的配置动态设置 kotlinVersion
                    project.getKotlinPluginVersion()?.also { kotlinVersion ->
                        Kotlin.kotlin_version = kotlinVersion
                    }
                }
            }
        }
    }

    /**
     * 公共plugin插件依赖
     */
    private fun Project.configCommonPlugin() {
        plugins.apply("kotlin-android")
        plugins.apply("kotlin-android-extensions")
    }

    /**
     * app Module 配置项，此处固定了 applicationId
     */
    private fun AppExtension.applyAppCommons(project: Project) {
        defaultConfig { applicationId = BuildConfig.applicationId }
        applyBaseCommons(project)
    }

    /**
     * library Module 配置项
     */
    private fun LibraryExtension.applyLibraryCommons(project: Project) {
        applyBaseCommons(project)
    }

    /**
     * 公共需要添加的设置，如sdk目标版本，jdk版本，jvm目标版本等
     */
    private fun BaseExtension.applyBaseCommons(project: Project) {
        compileSdkVersion(BuildConfig.compileSdkVersion)
        buildToolsVersion(BuildConfig.buildToolsVersion)

        defaultConfig {
            minSdkVersion(BuildConfig.minSdkVersion)
            targetSdkVersion(BuildConfig.targetSdkVersion)
            versionCode = BuildConfig.versionCode
            versionName = BuildConfig.versionName
            testInstrumentationRunner = BuildConfig.testInstrumentationRunner
            consumerProguardFiles(BuildConfig.consumerrules)
        }
        buildTypes {
            this["release"]?.apply {
                minifyEnabled(false)
                proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"))
            }
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
        project.tasks.withType<KotlinCompile> {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    /**
     * app Module 公共依赖
     */
    private fun Project.configAppDependencies() {
        dependencies.apply {
            add(implementation, fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
            add(implementation, Kotlin.kotlin_stdlib)
            add(implementation, AndroidX.coreKtx)
            add(implementation, AndroidX.appcompat)
            add(implementation, AndroidX.activityKtx)
            add(implementation, AndroidX.fragmentKtx)
            add(implementation, AndroidX.constraintlayout)
            add(implementation, AndroidX.livedataKtx)
            add(implementation, Google.material)
            configTestDependencies()
        }
    }

    /**
     * library Module 公共依赖
     */
    private fun Project.configLibraryDependencies() {
        dependencies.apply {
            add(implementation, fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
            add(implementation, Kotlin.kotlin_stdlib)
            add(implementation, AndroidX.coreKtx)
            add(implementation, AndroidX.appcompat)
            add(implementation, AndroidX.constraintlayout)
            add(implementation, Google.material)
            configTestDependencies()
        }
    }

    /**
     * test 依赖配置
     */
    private fun DependencyHandler.configTestDependencies() {
        testImplementation(Testing.testLibraries)
        androidTestImplementation(Testing.androidTestLibraries)
    }
}
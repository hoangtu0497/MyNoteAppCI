pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MyNoteAppCI"
include(":app")
include(":viewmodel")
include(":note-component")
include(":addnote-ui")
include(":designsystem")
include(":notedetail-ui")
include(":notes-ui")
include(":foundations")
include(":test-foundations")

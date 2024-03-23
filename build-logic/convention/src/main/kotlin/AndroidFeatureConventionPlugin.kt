
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidFeatureConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("fireframe.android.library")
                apply("fireframe.android.hilt")
            }
        }
    }
}
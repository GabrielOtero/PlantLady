package br.com.ladyplant

import android.app.Application
import br.com.ladyplant.di.plantLadyModule
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class PlantLadyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initFonts()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@PlantLadyApp)
            modules(plantLadyModule)
        }
    }

    private fun initFonts() {
        ViewPump.init(
            ViewPump.builder()
                .addInterceptor(
                    CalligraphyInterceptor(
                        CalligraphyConfig.Builder()
                            .setDefaultFontPath("fonts/halimunw7jn.ttf")
                            .setFontAttrId(R.attr.fontPath)
                            .build()
                    )
                )
                .build()
        )
    }
}
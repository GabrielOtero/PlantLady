package br.com.ladyplant

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PlantLadyApp : Application() {
    override fun onCreate() {
        super.onCreate()
//        initFonts()
    }

//    private fun initFonts() {
//        ViewPump.init(
//            ViewPump.builder()
//                .addInterceptor(
//                    CalligraphyInterceptor(
//                        CalligraphyConfig.Builder()
//                            .setDefaultFontPath("fonts/halimunw7jn.ttf")
//                            .setFontAttrId(R.attr.fontPath)
//                            .build()
//                    )
//                )
//                .build()
//        )
//    }
}

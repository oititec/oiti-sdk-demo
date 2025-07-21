package br.com.oiti.oitisdk.demo.strategy

import android.content.Context
import br.com.oiti.domain.callback.OitiResultCallback
import br.com.oiti.iproov.domain.model.IProovManagerOptions
import br.com.oiti.manager.exports.LivenessResult
import br.com.oiti.manager.main.OitiSDK
import br.com.oiti.oitisdk.demo.factories.IProovThemeFactory

class IProovStrategy : LivenessProviderStrategy {
    override fun start(
        context: Context,
        appKey: String,
        isCustom: Boolean,
        callback: OitiResultCallback<LivenessResult>
    ) {
        val theme = IProovThemeFactory.create(isCustom)
        val opts = IProovManagerOptions(appKey, theme)
        val manager = OitiSDK.createLivenessManager(OitiSDK.LivenessProvider.IPROOV)
        manager.start(opts, callback)
    }
}
package br.com.oiti.oitisdk.demo.strategy

import android.content.Context
import br.com.oiti.domain.callback.CertifaceResultCallback
import br.com.oiti.iproov.domain.model.IProovManagerOptions
import br.com.oiti.manager.exports.LivenessResult
import br.com.oiti.manager.main.CertifaceSDK
import br.com.oiti.oitisdk.demo.factories.IProovThemeFactory

class IProovStrategy : LivenessProviderStrategy {
    override fun start(
        context: Context,
        appKey: String,
        isCustom: Boolean,
        callback: CertifaceResultCallback<LivenessResult>
    ) {
        val theme = IProovThemeFactory.create(isCustom)
        val opts = IProovManagerOptions(appKey, theme)
        val manager = CertifaceSDK.createLivenessManager(CertifaceSDK.LivenessProvider.IPROOV)
        manager.start(opts, callback)
    }
}
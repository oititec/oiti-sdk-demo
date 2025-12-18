package br.com.oiti.oitisdk.demo.strategy

import android.content.Context
import br.com.oiti.domain.callback.CertifaceResultCallback
import br.com.oiti.facetecsdk.domain.model.FacetecManagerOptions
import br.com.oiti.manager.exports.LivenessResult
import br.com.oiti.manager.main.CertifaceSDK
import br.com.oiti.manager.main.LivenessProvider
import br.com.oiti.oitisdk.demo.factories.FacetecThemeFactory

class FacetecStrategy : LivenessProviderStrategy {
    override fun start(
        context: Context,
        appKey: String,
        isCustom: Boolean,
        callback: CertifaceResultCallback<LivenessResult>
    ) {
        val theme = FacetecThemeFactory.create(isCustom)
        val opts = FacetecManagerOptions(appKey, theme)
        val mgr = CertifaceSDK.createLivenessManager(LivenessProvider.FACETEC)
        mgr.start(opts, callback)
    }
}

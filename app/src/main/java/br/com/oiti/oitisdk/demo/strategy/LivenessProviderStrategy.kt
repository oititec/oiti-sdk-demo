package br.com.oiti.oitisdk.demo.strategy

import android.content.Context
import br.com.oiti.domain.callback.OitiResultCallback
import br.com.oiti.manager.exports.LivenessResult

interface LivenessProviderStrategy {
    fun start(
        context: Context,
        appKey: String,
        isCustom: Boolean,
        callback: OitiResultCallback<LivenessResult>
    )
}
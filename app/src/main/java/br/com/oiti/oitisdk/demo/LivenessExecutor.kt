package br.com.oiti.oitisdk.demo

import android.content.Context
import androidx.core.graphics.toColorInt
import br.com.oiti.domain.liveness.LivenessResponse
import br.com.oiti.domain.model.ErrorResponse
import br.com.oiti.domain.model.iproov.CameraSelection
import br.com.oiti.domain.model.iproov.OrientationGPA
import br.com.oiti.domain.model.iproov.OrientationLA
import br.com.oiti.manager.exports.Environment
import br.com.oiti.manager.exports.FilterTheme
import br.com.oiti.manager.exports.IProovFontsKey
import br.com.oiti.manager.exports.IProovManagerOptions
import br.com.oiti.manager.exports.IProovTheme
import br.com.oiti.manager.exports.LivenessResult
import br.com.oiti.manager.exports.NaturalStyle
import br.com.oiti.manager.exports.ResultCallback
import br.com.oiti.manager.exports.SDKConfig
import br.com.oiti.manager.main.OitiSDK

class LivenessExecutor(val appkey: String) {

    fun executeLiveness(
        context: Context,
        execOnSuccess: (LivenessResult?) -> Unit,
        execOnError: (ErrorResponse?) -> Unit,
        isCustomEnabled: Boolean = false
    ) {

        OitiSDK.initialize(
            context,
            SDKConfig(
                environment = Environment.HML,
                appKey = appkey
            )
        )

        val iProovManager =
            OitiSDK.createLivenessManager(provider = OitiSDK.LivenessProvider.IPROOV)

        val iProovFonts =
            mapOf(
                IProovFontsKey.INSTRUCTIONS_TITLE_FONT to R.font.ubuntu_regular,
                IProovFontsKey.INSTRUCTIONS_CAPTION_FONT to R.font.ubuntu_regular,
                IProovFontsKey.INSTRUCTIONS_DOCUMENT_TYPES_INSTRUCTIONS_FONT to R.font.ubuntu_regular,
                IProovFontsKey.INSTRUCTIONS_DOCUMENT_TIPS_INSTRUCTIONS_FONT to R.font.ubuntu_regular,
                IProovFontsKey.INSTRUCTIONS_BUTTON_FONT to R.font.ubuntu_regular,
                IProovFontsKey.PERMISSION_TITLE_FONT to R.font.ubuntu_regular,
                IProovFontsKey.PERMISSION_CAPTION_FONT to R.font.ubuntu_regular,
                IProovFontsKey.PERMISSION_BUTTON_FONT to R.font.ubuntu_regular,

                IProovFontsKey.RESULT_MESSAGE_FONT to R.font.ubuntu_regular,
                IProovFontsKey.RESULT_RETRY_BUTTON_FONT to R.font.ubuntu_regular,
            )

        val theme = IProovTheme.build {
            setTitle("Verificação Facial")
            setTitleColor("#FFFFFF")
            setHeaderBackgroundColor("#121212")
            setPromptTextColor("#FFFFFF")
            setPromptBackgroundColor("#1F1F1F")
            setSurroundColor("#00FF00")
            setFontResource(R.font.ubuntu_regular)
            setIsEnabledScreenShots(true)
            setDisableExteriorEffects(false)
//            setDisableExteriorEffects(true)
            setTimeoutSecs(60)
            setPromptRoundedCorners(true)
            setFontsKey(iProovFonts)
            setFilter(FilterTheme.Natural(NaturalStyle.CLEAR))

            setOrientation(
                gpa = OrientationGPA.PORTRAIT,
                la = OrientationLA.PORTRAIT
            )
            setCamera(CameraSelection.FRONT)

            setOvalColors(
                ready = "#00FF00".toColorInt(),
                notReady = "#FF0000".toColorInt(),
                stroke = "#FFFFFF".toColorInt(),
                completed = "#00FF00".toColorInt()
            )

            setInstructionsTheme {
                setTitleText("Teste title")
                setTitleColor("#FFFFFF")
                setCaptionText("teste caption.")
                setCaptionColor("#AAAAAA")
                setBackgroundColor("#1F1F1F")
                setBottomSheetColor("#333333")
                setDocumentTipsInstructionText("teste 1")
                setDocumentTypesInstructionText("teste 2")
                setBottomSheetCornerRadius(16f)
                setContinueButtonText("Startar")
                setContinueButtonColor("#00FF00")
                setContinueButtonTextColor("#000000")
            }

            setPermissionTheme {
                setTitle("Permissões Necessárias")
                setTitleColor("#FFFFFF")
                setBackgroundColor("#1F1F1F")
                setCheckPermissionButtonText("Permitir Acesso")
                setCheckPermissionButtonStyle("#00FF00")
            }

            setProcessingTheme {
                setBackgroundColor("#000000")
                setLoadingDialogColor("#FFFFFF")
                setLoadingIndicatorSize(100)
                setLoadingIndicatorWidth(10)
            }

            setResultTheme {
                setSuccessBackgroundColor("#DFFFD6")
                setSuccessIcon(br.com.oiti.designsystem.R.drawable.success_icon)
                setSuccessText("Verificação concluída com sucesso!")
                setSuccessTextColor("#0F9D58")

                setErrorBackgroundColor("#FFD6D6")
                setErrorIcon(br.com.oiti.designsystem.R.drawable.error_icon)
                setErrorText("Algo deu errado na verificação.")
                setErrorTextColor("#D93025")

                setRetryButtonColor("#0F9D58")
                setRetryButtonText("Tentar novamente")
                setRetryButtonTextColor("#FFFFFF")
            }
        }

        val defaultTheme = IProovTheme.build {
            setIsEnabledScreenShots(true)
        }

        val options = if (isCustomEnabled) {
            IProovManagerOptions(theme)
        } else {
            IProovManagerOptions(defaultTheme)
        }

        iProovManager.start(options, object : ResultCallback<LivenessResult> {
            override fun onSuccess(livenessResponse: LivenessResponse) {
                execOnSuccess(livenessResponse.livenessResult)
            }

            override fun onError(livenessResponse: LivenessResponse) {
                execOnError(livenessResponse.errorResponse)
            }
        })
    }
}
package br.com.oiti.oitisdk.demo.factories

import androidx.core.graphics.toColorInt
import br.com.oiti.designsystem.R
import br.com.oiti.domain.model.iproov.OrientationGPA
import br.com.oiti.domain.model.iproov.OrientationLA
import br.com.oiti.manager.exports.FilterTheme
import br.com.oiti.manager.exports.IProovFontsKey
import br.com.oiti.manager.exports.IProovTheme
import br.com.oiti.manager.exports.NaturalStyle

object IProovThemeFactory {
    fun create(isCustom: Boolean): IProovTheme =
        if (isCustom) buildCustom() else buildDefault()

    private fun buildDefault() = IProovTheme.build {
        setIsEnabledScreenShots(true)
    }

    private fun buildCustom() = IProovTheme.build {
        val iProovFonts = mapOf(
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

        setTitle("Verificação Facial")
        setTitleColor("#FFFFFF")
        setHeaderBackgroundColor("#121212")
        setPromptTextColor("#FFFFFF")
        setPromptBackgroundColor("#1F1F1F")
        setSurroundColor("#00FF00")
        setFontResource(R.font.ubuntu_regular)
        setIsEnabledScreenShots(true)
        setDisableExteriorEffects(false)
        setLogo(R.drawable.camera_alt)
        setCloseButton(R.drawable.close_icon)
        setTimeoutSecs(60)
        setPromptRoundedCorners(true)
        setFontsKey(iProovFonts)
        setFilter(FilterTheme.Natural(NaturalStyle.CLEAR))

        setIsScanningPromptsEnabled(false)
        setIsControlXPositionEnabled(false)
        setIsControlYPositionEnabled(false)

        setOrientation(
            gpa = OrientationGPA.PORTRAIT,
            la = OrientationLA.PORTRAIT
        )

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
            setStatusBarColor("#1F1F1F")
            setStatusBarIsDarkIcons(false)
            setBottomSheetColor("#333333")
            setFirstInstructionText("teste 1")
            setSecondInstructionText("teste 2")
            setBottomSheetCornerRadius(16f)
            setContinueButtonText("Startar")
            setContinueButtonColor("#00FF00")
            setContinueButtonTextColor("#000000")
        }

        setPermissionTheme {
            setTitle("Permissões Necessárias")
            setTitleColor("#FFFFFF")
            setSubTitle("Permita o acesso a camera")
            setSubTitleColor("#0a0a0a")
            setBackgroundColor("#1F1F1F")
            setStatusBarColor("#1F1F1F")
            setStatusBarIsDarkIcons(false)
            setPermissionButtonText("Permitir Acesso")
            setPermissionButtonColor("#00FF00")
        }

        setProcessingTheme {
            setBackgroundColor("#000000")
            setLoadingDialogColor("#FFFFFF")
            setStatusBarColor("#000000")
            setStatusBarIsDarkIcons(true)
            setLoadingIndicatorSize(100)
            setLoadingIndicatorWidth(10)
        }

        setResultTheme {
            setSuccessBackgroundColor("#DFFFD6")
            setSuccessIcon(R.drawable.success_icon)
            setSuccessText("Verificação concluída com sucesso!")
            setSuccessTextColor("#0F9D58")

            setStatusBarSuccessColor("#DFFFD6")
            setStatusBarErrorColor("#FFD6D6")
            setStatusBarSuccessIsDarkIcons(true)
            setStatusBarErrorIsDarkIcons(true)

            setErrorBackgroundColor("#FFD6D6")
            setErrorIcon(R.drawable.error_icon)
            setErrorText("Algo deu errado na verificação.")
            setErrorTextColor("#D93025")

            setRetryButtonColor("#0F9D58")
            setRetryButtonText("Tentar novamente")
            setRetryButtonTextColor("#FFFFFF")
        }
    }
}
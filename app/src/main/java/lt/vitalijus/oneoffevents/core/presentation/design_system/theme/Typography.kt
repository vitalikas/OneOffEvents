package lt.vitalijus.oneoffevents.core.presentation.design_system.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import lt.vitalijus.oneoffevents.R

val SpaceGrotesk
    @Composable get() = FontFamily(
        Font(
            resId = R.font.space_grotesk_bold,
            weight = FontWeight.Bold
        )
    )

val Inter
    @Composable get() = FontFamily(
        Font(
            resId = R.font.inter_regular,
            weight = FontWeight.Normal
        ),
        Font(
            resId = R.font.inter_medium,
            weight = FontWeight.Medium
        ),
    )

val Typography: Typography
    @Composable get() = Typography(
        bodyLarge = TextStyle(
            fontFamily = Inter,
            fontWeight = FontWeight.Normal,
            fontSize = 17.sp,
            lineHeight = 24.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = Inter,
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp,
            lineHeight = 20.sp
        ),
        bodySmall = TextStyle(
            fontFamily = Inter,
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp,
            lineHeight = 20.sp
        ),

        titleLarge = TextStyle(
            fontFamily = SpaceGrotesk,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            lineHeight = 36.sp
        ),
        titleMedium = TextStyle(
            fontFamily = SpaceGrotesk,
            fontWeight = FontWeight.Normal,
            fontSize = 17.sp,
            lineHeight = 24.sp
        ),
        titleSmall = TextStyle(
            fontFamily = SpaceGrotesk,
            fontWeight = FontWeight.Medium,
            fontSize = 17.sp,
            lineHeight = 24.sp
        ),
    )

package trade.dolcecosmetics.app.ui.composable.screen.onboarding

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import trade.dolcecosmetics.app.R
import trade.dolcecosmetics.app.ui.theme.DolceAccent
import trade.dolcecosmetics.app.ui.theme.DolcePrimary
import trade.dolcecosmetics.app.ui.theme.DolceTextSecondary
import trade.dolcecosmetics.app.ui.viewmodel.LCDSMOnboardingVM
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

data class OnboardingContent(
    @field:StringRes val titleRes: Int,
    @field:StringRes val descriptionRes: Int,
    @field:DrawableRes val imageRes: Int,
)

private val onboardingPagesContent = listOf(
    OnboardingContent(
        titleRes = R.string.page_1_title,
        descriptionRes = R.string.page_1_description,
        imageRes = R.drawable.img_onboarding_skincare,
    ),
    OnboardingContent(
        titleRes = R.string.page_2_title,
        descriptionRes = R.string.page_2_description,
        imageRes = R.drawable.img_onboarding_botanicals,
    ),
    OnboardingContent(
        titleRes = R.string.page_3_title,
        descriptionRes = R.string.page_3_description,
        imageRes = R.drawable.img_onboarding_ritual,
    ),
)

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    viewModel: LCDSMOnboardingVM = koinViewModel(),
    onNavigateToHomeScreen: () -> Unit,
) {
    val onboardingSetState by viewModel.onboardingSetState.collectAsState()

    LaunchedEffect(onboardingSetState) {
        if (onboardingSetState) {
            onNavigateToHomeScreen()
        }
    }

    OnboardingContent(
        pages = onboardingPagesContent,
        modifier = modifier,
        onFinish = { viewModel.setOnboarded() },
    )
}

@Composable
private fun OnboardingContent(
    pages: List<OnboardingContent>,
    modifier: Modifier = Modifier,
    onFinish: () -> Unit,
) {
    val pagerState = rememberPagerState(pageCount = { pages.size })
    val scope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        ) { page ->
            OnboardingPage(content = pages[page])
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                repeat(pages.size) { index ->
                    Box(
                        modifier = Modifier
                            .size(if (pagerState.currentPage == index) 24.dp else 8.dp, 8.dp)
                            .clip(CircleShape)
                            .background(
                                if (pagerState.currentPage == index) DolcePrimary
                                else Color(0xFFE0E0E0)
                            ),
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    if (pagerState.currentPage < pages.lastIndex) {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    } else {
                        onFinish()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = DolcePrimary,
                    contentColor = Color.White,
                ),
            ) {
                Text(
                    text = if (pagerState.currentPage < pages.lastIndex)
                        stringResource(R.string.next_button_title)
                    else
                        stringResource(R.string.start_button_title),
                    style = MaterialTheme.typography.labelLarge,
                    letterSpacing = 2.sp,
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            TextButton(
                onClick = { onFinish() },
            ) {
                Text(
                    text = stringResource(R.string.skip_button_title),
                    color = DolceTextSecondary,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}

@Composable
private fun OnboardingPage(
    content: OnboardingContent,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(content.imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(320.dp),
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = stringResource(content.titleRes),
            style = MaterialTheme.typography.headlineMedium,
            color = DolcePrimary,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 32.dp),
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(content.descriptionRes),
            style = MaterialTheme.typography.bodyLarge,
            color = DolceTextSecondary,
            textAlign = TextAlign.Center,
            lineHeight = 28.sp,
            modifier = Modifier.padding(horizontal = 40.dp),
        )
    }
}

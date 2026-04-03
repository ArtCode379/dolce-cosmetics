package trade.dolcecosmetics.app.ui.composable.screen.settings

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import trade.dolcecosmetics.app.R
import trade.dolcecosmetics.app.ui.theme.DolceAccent
import trade.dolcecosmetics.app.ui.theme.DolceBorder
import trade.dolcecosmetics.app.ui.theme.DolcePrimary
import trade.dolcecosmetics.app.ui.theme.DolceSurface
import trade.dolcecosmetics.app.ui.theme.DolceTextPrimary
import trade.dolcecosmetics.app.ui.theme.DolceTextSecondary

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFFAFAFA)),
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = DolceSurface,
        ) {
            Column {
                SettingsRow(
                    label = stringResource(R.string.settings_screen_company_label),
                    value = stringResource(R.string.company_name),
                )

                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    color = DolceBorder,
                    thickness = 1.dp,
                )

                SettingsRow(
                    label = stringResource(R.string.settings_screen_version_label),
                    value = stringResource(R.string.app_version),
                )

                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    color = DolceBorder,
                    thickness = 1.dp,
                )

                SettingsLinkRow(
                    label = stringResource(R.string.settings_screen_customer_support_label),
                    linkText = stringResource(R.string.customer_support_link),
                    onClick = {
                        val uri = Uri.parse(stringResource(R.string.customer_support_link))
                        val intent = Intent(Intent.ACTION_VIEW, uri)
                        context.startActivity(intent)
                    },
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "© 2024 DOLCE COSMETICS B. V. LIMITED",
            style = MaterialTheme.typography.labelSmall,
            color = DolceTextSecondary,
            letterSpacing = 0.5.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
        )
    }
}

@Composable
private fun SettingsRow(
    label: String,
    value: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = label.uppercase(),
                style = MaterialTheme.typography.labelSmall,
                color = DolceTextSecondary,
                letterSpacing = 1.5.sp,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                color = DolceTextPrimary,
            )
        }
    }
}

@Composable
private fun SettingsLinkRow(
    label: String,
    linkText: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = label.uppercase(),
                style = MaterialTheme.typography.labelSmall,
                color = DolceTextSecondary,
                letterSpacing = 1.5.sp,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = linkText,
                style = MaterialTheme.typography.bodyMedium,
                color = DolceAccent,
            )
        }
        Icon(
            painter = painterResource(R.drawable.link_svgrepo_com),
            contentDescription = null,
            tint = DolceAccent,
            modifier = Modifier.size(16.dp),
        )
    }
}

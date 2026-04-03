package trade.dolcecosmetics.app.ui.composable.screen.checkout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import trade.dolcecosmetics.app.R
import trade.dolcecosmetics.app.data.entity.OrderEntity
import trade.dolcecosmetics.app.ui.theme.DolceAccent
import trade.dolcecosmetics.app.ui.theme.DolcePrimary
import trade.dolcecosmetics.app.ui.theme.DolceTextSecondary

@Composable
fun CheckoutDialog(
    order: OrderEntity,
    onConfirm: () -> Unit,
) {
    Dialog(
        onDismissRequest = { onConfirm() },
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false),
    ) {
        Surface(
            shape = RoundedCornerShape(4.dp),
            color = Color.White,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Icon(
                    painter = painterResource(R.drawable.star_svgrepo_com),
                    contentDescription = null,
                    tint = DolceAccent,
                    modifier = Modifier.size(40.dp),
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = stringResource(R.string.checkout_dialog_title),
                    style = MaterialTheme.typography.headlineSmall,
                    color = DolcePrimary,
                    textAlign = TextAlign.Center,
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = stringResource(R.string.checkout_dialog_order_number, order.orderNumber),
                    style = MaterialTheme.typography.bodyMedium,
                    color = DolceTextSecondary,
                    textAlign = TextAlign.Center,
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = stringResource(R.string.checkout_dialog_processing_message),
                    style = MaterialTheme.typography.bodyMedium,
                    color = DolceTextSecondary,
                    textAlign = TextAlign.Center,
                    lineHeight = 22.sp,
                )

                Spacer(modifier = Modifier.height(28.dp))

                Button(
                    onClick = { onConfirm() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = DolcePrimary,
                        contentColor = Color.White,
                    ),
                ) {
                    Text(
                        text = stringResource(R.string.checkout_dialog_button_label).uppercase(),
                        style = MaterialTheme.typography.labelLarge,
                        letterSpacing = 2.sp,
                    )
                }
            }
        }
    }
}

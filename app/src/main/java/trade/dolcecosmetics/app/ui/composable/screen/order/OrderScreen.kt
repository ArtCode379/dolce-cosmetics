package trade.dolcecosmetics.app.ui.composable.screen.order

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import trade.dolcecosmetics.app.R
import trade.dolcecosmetics.app.data.entity.OrderEntity
import trade.dolcecosmetics.app.ui.composable.shared.LCDSMContentWrapper
import trade.dolcecosmetics.app.ui.composable.shared.LCDSMEmptyView
import trade.dolcecosmetics.app.ui.state.DataUiState
import trade.dolcecosmetics.app.ui.theme.DolceAccent
import trade.dolcecosmetics.app.ui.theme.DolceBorder
import trade.dolcecosmetics.app.ui.theme.DolcePrimary
import trade.dolcecosmetics.app.ui.theme.DolceSurface
import trade.dolcecosmetics.app.ui.theme.DolceTextPrimary
import trade.dolcecosmetics.app.ui.theme.DolceTextSecondary
import trade.dolcecosmetics.app.ui.viewmodel.OrderViewModel
import org.koin.androidx.compose.koinViewModel
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@Composable
fun OrdersScreen(
    modifier: Modifier = Modifier,
    viewModel: OrderViewModel = koinViewModel(),
) {
    val ordersState by viewModel.ordersState.collectAsState()

    OrdersContent(
        ordersState = ordersState,
        modifier = modifier,
    )
}

@Composable
private fun OrdersContent(
    ordersState: DataUiState<List<OrderEntity>>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFFAFAFA)),
    ) {
        LCDSMContentWrapper(
            dataState = ordersState,
            modifier = Modifier.fillMaxSize(),

            dataPopulated = {
                val orders = (ordersState as DataUiState.Populated).data

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp),
                ) {
                    items(orders, key = { it.orderNumber }) { order ->
                        OrderCard(order = order)
                    }
                }
            },

            dataEmpty = {
                LCDSMEmptyView(
                    primaryText = stringResource(R.string.orders_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}

@Composable
private fun OrderCard(order: OrderEntity) {
    val dateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(4.dp),
        color = DolceSurface,
        border = androidx.compose.foundation.BorderStroke(1.dp, DolceBorder),
        shadowElevation = 1.dp,
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(R.string.order_number, order.orderNumber.take(8)),
                    style = MaterialTheme.typography.titleSmall,
                    color = DolcePrimary,
                )

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(2.dp))
                        .background(DolceAccent.copy(alpha = 0.12f))
                        .border(1.dp, DolceAccent.copy(alpha = 0.3f), RoundedCornerShape(2.dp))
                        .padding(horizontal = 10.dp, vertical = 4.dp),
                ) {
                    Text(
                        text = stringResource(R.string.order_status_confirmed).uppercase(),
                        style = MaterialTheme.typography.labelSmall,
                        color = DolceAccent,
                        letterSpacing = 1.sp,
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = order.timestamp.format(dateFormatter),
                style = MaterialTheme.typography.bodySmall,
                color = DolceTextSecondary,
            )

            Spacer(modifier = Modifier.height(12.dp))

            HorizontalDivider(color = DolceBorder, thickness = 1.dp)

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = stringResource(R.string.order_customer, order.customerFirstName, order.customerLastName),
                style = MaterialTheme.typography.bodyMedium,
                color = DolceTextPrimary,
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = order.description,
                style = MaterialTheme.typography.bodySmall,
                color = DolceTextSecondary,
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(R.string.order_collect_message),
                    style = MaterialTheme.typography.labelSmall,
                    color = DolceTextSecondary,
                    modifier = Modifier.weight(1f),
                )

                Text(
                    text = "£%.2f".format(order.price),
                    style = MaterialTheme.typography.titleMedium,
                    color = DolceAccent,
                )
            }
        }
    }
}

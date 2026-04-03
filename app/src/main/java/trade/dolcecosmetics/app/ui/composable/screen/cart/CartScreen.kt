package trade.dolcecosmetics.app.ui.composable.screen.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import trade.dolcecosmetics.app.R
import trade.dolcecosmetics.app.ui.composable.shared.LCDSMContentWrapper
import trade.dolcecosmetics.app.ui.composable.shared.LCDSMEmptyView
import trade.dolcecosmetics.app.ui.state.CartItemUiState
import trade.dolcecosmetics.app.ui.state.DataUiState
import trade.dolcecosmetics.app.ui.theme.DolceAccent
import trade.dolcecosmetics.app.ui.theme.DolceBorder
import trade.dolcecosmetics.app.ui.theme.DolcePrimary
import trade.dolcecosmetics.app.ui.theme.DolceSurface
import trade.dolcecosmetics.app.ui.theme.DolceTextPrimary
import trade.dolcecosmetics.app.ui.theme.DolceTextSecondary
import trade.dolcecosmetics.app.ui.viewmodel.CartViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    viewModel: CartViewModel = koinViewModel(),
    onNavigateToCheckoutScreen: () -> Unit,
) {
    val cartItemsState by viewModel.cartItemsState.collectAsStateWithLifecycle()
    val totalPrice by viewModel.totalPrice.collectAsStateWithLifecycle()

    val onPlusItemClick = { itemId: Int ->
        viewModel.incrementProductInCart(itemId)
    }

    val onMinusItemClick = { itemId: Int ->
        viewModel.decrementItemInCart(itemId)
    }

    CartScreenContent(
        cartItemsState = cartItemsState,
        modifier = modifier,
        totalPrice = totalPrice,
        onPlusItemClick = onPlusItemClick,
        onMinusItemClick = onMinusItemClick,
        onCompleteOrderButtonClick = onNavigateToCheckoutScreen,
    )
}

@Composable
private fun CartScreenContent(
    cartItemsState: DataUiState<List<CartItemUiState>>,
    modifier: Modifier = Modifier,
    totalPrice: Double,
    onPlusItemClick: (Int) -> Unit,
    onMinusItemClick: (Int) -> Unit,
    onCompleteOrderButtonClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFFAFAFA)),
    ) {
        LCDSMContentWrapper(
            dataState = cartItemsState,
            modifier = Modifier.weight(1f),

            dataPopulated = {
                val items = (cartItemsState as DataUiState.Populated).data

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    items(items, key = { it.productId }) { item ->
                        CartItem(
                            item = item,
                            onPlusClick = { onPlusItemClick(item.productId) },
                            onMinusClick = { onMinusItemClick(item.productId) },
                        )
                        HorizontalDivider(color = DolceBorder, thickness = 1.dp)
                    }

                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            },

            dataEmpty = {
                LCDSMEmptyView(
                    primaryText = stringResource(R.string.cart_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )

        if (cartItemsState is DataUiState.Populated) {
            CartSummary(
                totalPrice = totalPrice,
                onCheckoutClick = onCompleteOrderButtonClick,
            )
        }
    }
}

@Composable
private fun CartItem(
    item: CartItemUiState,
    onPlusClick: () -> Unit,
    onMinusClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(DolceSurface)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (item.productImageRes != null) {
            Image(
                painter = painterResource(item.productImageRes),
                contentDescription = item.productTitle,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .border(1.dp, DolceBorder, RoundedCornerShape(4.dp)),
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = item.productTitle,
                style = MaterialTheme.typography.titleSmall,
                color = DolcePrimary,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "£%.2f".format(item.productPrice),
                style = MaterialTheme.typography.bodyMedium,
                color = DolceAccent,
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Icon(
                painter = painterResource(R.drawable.minus_svgrepo_com),
                contentDescription = stringResource(R.string.decrease_quantity_icon_description),
                tint = DolcePrimary,
                modifier = Modifier
                    .size(28.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .border(1.dp, DolceBorder, RoundedCornerShape(4.dp))
                    .clickable { onMinusClick() }
                    .padding(6.dp),
            )
            Text(
                text = "${item.quantity}",
                style = MaterialTheme.typography.titleSmall,
                color = DolcePrimary,
                modifier = Modifier.width(24.dp),
            )
            Icon(
                painter = painterResource(R.drawable.plus_svgrepo_com),
                contentDescription = stringResource(R.string.increase_quantity_icon_description),
                tint = DolcePrimary,
                modifier = Modifier
                    .size(28.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .border(1.dp, DolceBorder, RoundedCornerShape(4.dp))
                    .clickable { onPlusClick() }
                    .padding(6.dp),
            )
        }
    }
}

@Composable
private fun CartSummary(
    totalPrice: Double,
    onCheckoutClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(DolceSurface)
            .padding(20.dp),
    ) {
        HorizontalDivider(color = DolceBorder, thickness = 1.dp)
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(R.string.cart_total_label).uppercase(),
                style = MaterialTheme.typography.labelMedium,
                color = DolceTextSecondary,
                letterSpacing = 1.5.sp,
            )
            Text(
                text = "£%.2f".format(totalPrice),
                style = MaterialTheme.typography.titleLarge,
                color = DolcePrimary,
                fontWeight = FontWeight.Normal,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { onCheckoutClick() },
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
                text = stringResource(R.string.button_place_order_label, totalPrice).uppercase(),
                style = MaterialTheme.typography.labelLarge,
                letterSpacing = 1.sp,
            )
        }
    }
}

package trade.dolcecosmetics.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import trade.dolcecosmetics.app.ui.composable.approot.AppRoot
import trade.dolcecosmetics.app.ui.theme.ProductAppLCDSMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProductAppLCDSMTheme {
                AppRoot()
            }
        }
    }
}
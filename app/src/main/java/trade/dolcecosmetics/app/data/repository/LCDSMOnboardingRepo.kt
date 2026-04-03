package trade.dolcecosmetics.app.data.repository

import trade.dolcecosmetics.app.data.datastore.LCDSMOnboardingPrefs
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class LCDSMOnboardingRepo(
    private val lcdsmOnboardingStoreManager: LCDSMOnboardingPrefs,
    private val coroutineDispatcher: CoroutineDispatcher,
) {

    fun observeOnboardingState(): Flow<Boolean?> {
        return lcdsmOnboardingStoreManager.onboardedStateFlow
    }

    suspend fun setOnboardingState(state: Boolean) {
        withContext(coroutineDispatcher) {
            lcdsmOnboardingStoreManager.setOnboardedState(state)
        }
    }
}
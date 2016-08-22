package ly.generalassemb.drewmahrt.syncadapterexample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by drewmahrt on 3/2/16.
 */
public class MySyncService extends Service {

    private static final Object sSyncAdapterLock = new Object();
    private static SyncAdapter sSyncAdapter = null;

    @Override
    public void onCreate() {
        synchronized (sSyncAdapterLock) {
            if (sSyncAdapter == null)
                sSyncAdapter = new SyncAdapter(getApplicationContext(), true);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return sSyncAdapter.getSyncAdapterBinder();
    }
}

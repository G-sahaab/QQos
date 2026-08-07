package app.qqlauncher.compatlib.eleven;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import app.qqlauncher.compatlib.ActivityManagerCompat;
import app.qqlauncher.compatlib.ActivityOptionsCompat;
import app.qqlauncher.compatlib.ten.QuickstepCompatFactoryVQ;

@RequiresApi(30)
public class QuickstepCompatFactoryVR extends QuickstepCompatFactoryVQ {

    @NonNull
    @Override
    public ActivityManagerCompat getActivityManagerCompat() {
        return new ActivityManagerCompatVR();
    }

    @NonNull
    @Override
    public ActivityOptionsCompat getActivityOptionsCompat() {
        return new ActivityOptionsCompatVR();
    }
}

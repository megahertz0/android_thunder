package android.support.v4.app;

abstract class BaseFragmentActivityEclair extends BaseFragmentActivityDonut {
    BaseFragmentActivityEclair() {
    }

    void onBackPressedNotHandled() {
        super.onBackPressed();
    }
}

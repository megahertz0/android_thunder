package android.support.v4.speech.tts;

import android.os.Build.VERSION;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener;
import android.speech.tts.UtteranceProgressListener;
import java.util.Locale;
import java.util.Set;

class TextToSpeechICSMR1 {
    public static final String KEY_FEATURE_EMBEDDED_SYNTHESIS = "embeddedTts";
    public static final String KEY_FEATURE_NETWORK_SYNTHESIS = "networkTts";

    final class AnonymousClass_1 extends UtteranceProgressListener {
        final /* synthetic */ UtteranceProgressListenerICSMR1 val$listener;

        AnonymousClass_1(UtteranceProgressListenerICSMR1 utteranceProgressListenerICSMR1) {
            this.val$listener = utteranceProgressListenerICSMR1;
        }

        public final void onStart(String str) {
            this.val$listener.onStart(str);
        }

        public final void onError(String str) {
            this.val$listener.onError(str);
        }

        public final void onDone(String str) {
            this.val$listener.onDone(str);
        }
    }

    final class AnonymousClass_2 implements OnUtteranceCompletedListener {
        final /* synthetic */ UtteranceProgressListenerICSMR1 val$listener;

        AnonymousClass_2(UtteranceProgressListenerICSMR1 utteranceProgressListenerICSMR1) {
            this.val$listener = utteranceProgressListenerICSMR1;
        }

        public final void onUtteranceCompleted(String str) {
            this.val$listener.onStart(str);
            this.val$listener.onDone(str);
        }
    }

    static interface UtteranceProgressListenerICSMR1 {
        void onDone(String str);

        void onError(String str);

        void onStart(String str);
    }

    TextToSpeechICSMR1() {
    }

    static Set<String> getFeatures(TextToSpeech textToSpeech, Locale locale) {
        return VERSION.SDK_INT >= 15 ? textToSpeech.getFeatures(locale) : null;
    }

    static void setUtteranceProgressListener(TextToSpeech textToSpeech, UtteranceProgressListenerICSMR1 utteranceProgressListenerICSMR1) {
        if (VERSION.SDK_INT >= 15) {
            textToSpeech.setOnUtteranceProgressListener(new AnonymousClass_1(utteranceProgressListenerICSMR1));
        } else {
            textToSpeech.setOnUtteranceCompletedListener(new AnonymousClass_2(utteranceProgressListenerICSMR1));
        }
    }
}

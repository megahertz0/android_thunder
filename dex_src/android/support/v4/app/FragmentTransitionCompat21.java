package android.support.v4.app;

import android.graphics.Rect;
import android.support.v4.app.FragmentTransitionCompat21.EpicenterView;
import android.support.v4.app.FragmentTransitionCompat21.ViewRetriever;
import android.transition.Transition;
import android.transition.Transition.EpicenterCallback;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnPreDrawListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

class FragmentTransitionCompat21 {

    public static interface ViewRetriever {
        View getView();
    }

    final class AnonymousClass_1 extends EpicenterCallback {
        final /* synthetic */ Rect val$epicenter;

        AnonymousClass_1(Rect rect) {
            this.val$epicenter = rect;
        }

        public final Rect onGetEpicenter(Transition transition) {
            return this.val$epicenter;
        }
    }

    final class AnonymousClass_2 implements OnPreDrawListener {
        final /* synthetic */ View val$container;
        final /* synthetic */ Transition val$enterTransition;
        final /* synthetic */ ArrayList val$enteringViews;
        final /* synthetic */ ViewRetriever val$inFragment;
        final /* synthetic */ Map val$nameOverrides;
        final /* synthetic */ View val$nonExistentView;
        final /* synthetic */ Map val$renamedViews;

        AnonymousClass_2(View view, Transition transition, View view2, ViewRetriever viewRetriever, Map map, Map map2, ArrayList arrayList) {
            this.val$container = view;
            this.val$enterTransition = transition;
            this.val$nonExistentView = view2;
            this.val$inFragment = viewRetriever;
            this.val$nameOverrides = map;
            this.val$renamedViews = map2;
            this.val$enteringViews = arrayList;
        }

        public final boolean onPreDraw() {
            this.val$container.getViewTreeObserver().removeOnPreDrawListener(this);
            if (this.val$enterTransition != null) {
                this.val$enterTransition.removeTarget(this.val$nonExistentView);
            }
            View view = this.val$inFragment.getView();
            if (view != null) {
                if (!this.val$nameOverrides.isEmpty()) {
                    FragmentTransitionCompat21.findNamedViews(this.val$renamedViews, view);
                    this.val$renamedViews.keySet().retainAll(this.val$nameOverrides.values());
                    for (Entry entry : this.val$nameOverrides.entrySet()) {
                        View view2 = (View) this.val$renamedViews.get((String) entry.getValue());
                        if (view2 != null) {
                            view2.setTransitionName((String) entry.getKey());
                        }
                    }
                }
                if (this.val$enterTransition != null) {
                    FragmentTransitionCompat21.captureTransitioningViews(this.val$enteringViews, view);
                    this.val$enteringViews.removeAll(this.val$renamedViews.values());
                    this.val$enteringViews.add(this.val$nonExistentView);
                    FragmentTransitionCompat21.addTargets(this.val$enterTransition, this.val$enteringViews);
                }
            }
            return true;
        }
    }

    final class AnonymousClass_3 extends EpicenterCallback {
        private Rect mEpicenter;
        final /* synthetic */ EpicenterView val$epicenterView;

        AnonymousClass_3(EpicenterView epicenterView) {
            this.val$epicenterView = epicenterView;
        }

        public final Rect onGetEpicenter(Transition transition) {
            if (this.mEpicenter == null && this.val$epicenterView.epicenter != null) {
                this.mEpicenter = FragmentTransitionCompat21.getBoundsOnScreen(this.val$epicenterView.epicenter);
            }
            return this.mEpicenter;
        }
    }

    final class AnonymousClass_4 implements OnPreDrawListener {
        final /* synthetic */ Transition val$enterTransition;
        final /* synthetic */ ArrayList val$enteringViews;
        final /* synthetic */ Transition val$exitTransition;
        final /* synthetic */ ArrayList val$exitingViews;
        final /* synthetic */ ArrayList val$hiddenViews;
        final /* synthetic */ View val$nonExistentView;
        final /* synthetic */ Transition val$overallTransition;
        final /* synthetic */ Map val$renamedViews;
        final /* synthetic */ View val$sceneRoot;
        final /* synthetic */ ArrayList val$sharedElementTargets;
        final /* synthetic */ Transition val$sharedElementTransition;

        AnonymousClass_4(View view, Transition transition, ArrayList arrayList, Transition transition2, ArrayList arrayList2, Transition transition3, ArrayList arrayList3, Map map, ArrayList arrayList4, Transition transition4, View view2) {
            this.val$sceneRoot = view;
            this.val$enterTransition = transition;
            this.val$enteringViews = arrayList;
            this.val$exitTransition = transition2;
            this.val$exitingViews = arrayList2;
            this.val$sharedElementTransition = transition3;
            this.val$sharedElementTargets = arrayList3;
            this.val$renamedViews = map;
            this.val$hiddenViews = arrayList4;
            this.val$overallTransition = transition4;
            this.val$nonExistentView = view2;
        }

        public final boolean onPreDraw() {
            this.val$sceneRoot.getViewTreeObserver().removeOnPreDrawListener(this);
            if (this.val$enterTransition != null) {
                FragmentTransitionCompat21.removeTargets(this.val$enterTransition, this.val$enteringViews);
            }
            if (this.val$exitTransition != null) {
                FragmentTransitionCompat21.removeTargets(this.val$exitTransition, this.val$exitingViews);
            }
            if (this.val$sharedElementTransition != null) {
                FragmentTransitionCompat21.removeTargets(this.val$sharedElementTransition, this.val$sharedElementTargets);
            }
            for (Entry entry : this.val$renamedViews.entrySet()) {
                ((View) entry.getValue()).setTransitionName((String) entry.getKey());
            }
            int size = this.val$hiddenViews.size();
            for (int i = 0; i < size; i++) {
                this.val$overallTransition.excludeTarget((View) this.val$hiddenViews.get(i), false);
            }
            this.val$overallTransition.excludeTarget(this.val$nonExistentView, false);
            return true;
        }
    }

    public static class EpicenterView {
        public View epicenter;
    }

    FragmentTransitionCompat21() {
    }

    public static String getTransitionName(View view) {
        return view.getTransitionName();
    }

    public static Object cloneTransition(Object obj) {
        return obj != null ? ((Transition) obj).clone() : obj;
    }

    public static Object captureExitingViews(Object obj, View view, ArrayList<View> arrayList, Map<String, View> map, View view2) {
        if (obj == null) {
            return obj;
        }
        captureTransitioningViews(arrayList, view);
        if (map != null) {
            arrayList.removeAll(map.values());
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        arrayList.add(view2);
        addTargets((Transition) obj, arrayList);
        return obj;
    }

    public static void excludeTarget(Object obj, View view, boolean z) {
        ((Transition) obj).excludeTarget(view, z);
    }

    public static void beginDelayedTransition(ViewGroup viewGroup, Object obj) {
        TransitionManager.beginDelayedTransition(viewGroup, (Transition) obj);
    }

    public static void setEpicenter(Object obj, View view) {
        ((Transition) obj).setEpicenterCallback(new AnonymousClass_1(getBoundsOnScreen(view)));
    }

    public static Object wrapSharedElementTransition(Object obj) {
        if (obj == null) {
            return null;
        }
        Transition transition = (Transition) obj;
        if (transition == null) {
            return null;
        }
        Object transitionSet = new TransitionSet();
        transitionSet.addTransition(transition);
        return transitionSet;
    }

    public static void addTransitionTargets(Object obj, Object obj2, View view, ViewRetriever viewRetriever, View view2, EpicenterView epicenterView, Map<String, String> map, ArrayList<View> arrayList, Map<String, View> map2, Map<String, View> map3, ArrayList<View> arrayList2) {
        if (obj != null || obj2 != null) {
            Transition transition = (Transition) obj;
            if (transition != null) {
                transition.addTarget(view2);
            }
            if (obj2 != null) {
                setSharedElementTargets(obj2, view2, map2, arrayList2);
            }
            if (viewRetriever != null) {
                view.getViewTreeObserver().addOnPreDrawListener(new AnonymousClass_2(view, transition, view2, viewRetriever, map, map3, arrayList));
            }
            setSharedElementEpicenter(transition, epicenterView);
        }
    }

    public static Object mergeTransitions(Object obj, Object obj2, Object obj3, boolean z) {
        Transition transition = (Transition) obj;
        Transition transition2 = (Transition) obj2;
        Transition transition3 = (Transition) obj3;
        if (transition == null || transition2 == null) {
            z = true;
        }
        Object transitionSet;
        if (z) {
            transitionSet = new TransitionSet();
            if (transition != null) {
                transitionSet.addTransition(transition);
            }
            if (transition2 != null) {
                transitionSet.addTransition(transition2);
            }
            if (transition3 == null) {
                return transitionSet;
            }
            transitionSet.addTransition(transition3);
            return transitionSet;
        }
        Transition transition4 = null;
        if (transition2 != null && transition != null) {
            transition4 = new TransitionSet().addTransition(transition2).addTransition(transition).setOrdering(1);
        } else if (transition2 != null) {
            transition4 = transition2;
        } else if (transition != null) {
            transition4 = transition;
        }
        if (transition3 == null) {
            return transition4;
        }
        transitionSet = new TransitionSet();
        if (transition4 != null) {
            transitionSet.addTransition(transition4);
        }
        transitionSet.addTransition(transition3);
        return transitionSet;
    }

    public static void setSharedElementTargets(Object obj, View view, Map<String, View> map, ArrayList<View> arrayList) {
        TransitionSet transitionSet = (TransitionSet) obj;
        arrayList.clear();
        arrayList.addAll(map.values());
        List targets = transitionSet.getTargets();
        targets.clear();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            bfsAddViewChildren(targets, (View) arrayList.get(i));
        }
        arrayList.add(view);
        addTargets(transitionSet, arrayList);
    }

    private static void bfsAddViewChildren(List<View> list, View view) {
        int size = list.size();
        if (!containedBeforeIndex(list, view, size)) {
            list.add(view);
            for (int i = size; i < list.size(); i++) {
                View view2 = (View) list.get(i);
                if (view2 instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) view2;
                    int childCount = viewGroup.getChildCount();
                    for (int i2 = 0; i2 < childCount; i2++) {
                        View childAt = viewGroup.getChildAt(i2);
                        if (!containedBeforeIndex(list, childAt, size)) {
                            list.add(childAt);
                        }
                    }
                }
            }
        }
    }

    private static boolean containedBeforeIndex(List<View> list, View view, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (list.get(i2) == view) {
                return true;
            }
        }
        return false;
    }

    private static void setSharedElementEpicenter(Transition transition, EpicenterView epicenterView) {
        if (transition != null) {
            transition.setEpicenterCallback(new AnonymousClass_3(epicenterView));
        }
    }

    private static Rect getBoundsOnScreen(View view) {
        Rect rect = new Rect();
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        rect.set(iArr[0], iArr[1], iArr[0] + view.getWidth(), iArr[1] + view.getHeight());
        return rect;
    }

    private static void captureTransitioningViews(ArrayList<View> arrayList, View view) {
        if (view.getVisibility() != 0) {
            return;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (viewGroup.isTransitionGroup()) {
                arrayList.add(viewGroup);
                return;
            }
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                captureTransitioningViews(arrayList, viewGroup.getChildAt(i));
            }
            return;
        }
        arrayList.add(view);
    }

    public static void findNamedViews(Map<String, View> map, View view) {
        if (view.getVisibility() == 0) {
            String transitionName = view.getTransitionName();
            if (transitionName != null) {
                map.put(transitionName, view);
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    findNamedViews(map, viewGroup.getChildAt(i));
                }
            }
        }
    }

    public static void cleanupTransitions(View view, View view2, Object obj, ArrayList<View> arrayList, Object obj2, ArrayList<View> arrayList2, Object obj3, ArrayList<View> arrayList3, Object obj4, ArrayList<View> arrayList4, Map<String, View> map) {
        Transition transition = (Transition) obj;
        Transition transition2 = (Transition) obj2;
        Transition transition3 = (Transition) obj3;
        Transition transition4 = (Transition) obj4;
        if (transition4 != null) {
            view.getViewTreeObserver().addOnPreDrawListener(new AnonymousClass_4(view, transition, arrayList, transition2, arrayList2, transition3, arrayList3, map, arrayList4, transition4, view2));
        }
    }

    public static void removeTargets(Object obj, ArrayList<View> arrayList) {
        Transition transition = (Transition) obj;
        int transitionCount;
        if (transition instanceof TransitionSet) {
            TransitionSet transitionSet = (TransitionSet) transition;
            transitionCount = transitionSet.getTransitionCount();
            for (int i = 0; i < transitionCount; i++) {
                removeTargets(transitionSet.getTransitionAt(i), arrayList);
            }
        } else if (!hasSimpleTarget(transition)) {
            List targets = transition.getTargets();
            if (targets != null && targets.size() == arrayList.size() && targets.containsAll(arrayList)) {
                for (transitionCount = arrayList.size() - 1; transitionCount >= 0; transitionCount--) {
                    transition.removeTarget((View) arrayList.get(transitionCount));
                }
            }
        }
    }

    public static void addTargets(Object obj, ArrayList<View> arrayList) {
        int i = 0;
        Transition transition = (Transition) obj;
        int transitionCount;
        if (transition instanceof TransitionSet) {
            TransitionSet transitionSet = (TransitionSet) transition;
            transitionCount = transitionSet.getTransitionCount();
            while (i < transitionCount) {
                addTargets(transitionSet.getTransitionAt(i), arrayList);
                i++;
            }
        } else if (!hasSimpleTarget(transition) && isNullOrEmpty(transition.getTargets())) {
            int size = arrayList.size();
            for (transitionCount = 0; transitionCount < size; transitionCount++) {
                transition.addTarget((View) arrayList.get(transitionCount));
            }
        }
    }

    private static boolean hasSimpleTarget(Transition transition) {
        return (isNullOrEmpty(transition.getTargetIds()) && isNullOrEmpty(transition.getTargetNames()) && isNullOrEmpty(transition.getTargetTypes())) ? false : true;
    }

    private static boolean isNullOrEmpty(List list) {
        return list == null || list.isEmpty();
    }
}

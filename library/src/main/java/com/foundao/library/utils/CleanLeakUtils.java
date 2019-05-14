package com.foundao.library.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.lang.reflect.Field;

/**
 * 解决InputMethodManager导致的内存泄漏
 * 发生的场景是在HomeActivity使用了FragmentStatePagerAdapter来管理Fragment，
 * 而Fragment中又使用了RecyclerView ，在Activity中的TreeObserver 或者其他系统类依赖了InputMethodManager。
 * @see <a href=https://issuetracker.google.com/issues/37043700>这里讨论了这个问题</a>
 */
public class CleanLeakUtils {

    /**
     * 修复InputMethod导致的内存泄漏
     */
    public static void fixInputMethodManagerLeak(Context destContext) {
        if (destContext == null) {
            return;
        }

        InputMethodManager inputMethodManager = (InputMethodManager) destContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager == null) {
            return;
        }

        String[] viewArray = new String[]{"mCurRootView", "mServedView", "mNextServedView"};
        Field filed;
        Object filedObject;

        for (String view : viewArray) {
            try {
                filed = inputMethodManager.getClass().getDeclaredField(view);
                if (!filed.isAccessible()) {
                    filed.setAccessible(true);
                }
                filedObject = filed.get(inputMethodManager);
                if (filedObject != null && filedObject instanceof View) {
                    View fileView = (View) filedObject;
                    if (fileView.getContext() == destContext) { // 被InputMethodManager持有引用的context是想要目标销毁的
                        filed.set(inputMethodManager, null); // 置空，破坏掉path to gc节点
                    } else {
                        break;// 不是想要目标销毁的，即为又进了另一层界面了，不要处理，避免影响原逻辑,也就不用继续for循环了
                    }
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }
}
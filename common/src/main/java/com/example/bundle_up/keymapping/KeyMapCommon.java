package com.example.bundle_up.keymapping;

import org.lwjgl.glfw.GLFW;

public class KeyMapCommon {
    public static final String KEY_ID = "key.bundle-up-bundle-item";
    public static final String CATEGORY = "key.category.bundle-up.category-name";

    public static final CommonKey BUNDLE_UP = new CommonKey(
            KEY_ID,
            GLFW.GLFW_KEY_G,
            CATEGORY
    );

    public record CommonKey(String name, int key, String category){}
}
